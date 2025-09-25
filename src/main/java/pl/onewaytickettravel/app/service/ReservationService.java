package pl.onewaytickettravel.app.service;

import org.springframework.stereotype.Service;
import pl.onewaytickettravel.app.auth.entities.User;
import pl.onewaytickettravel.app.auth.repository.UserRepository;
import pl.onewaytickettravel.app.entities.Offer;
import pl.onewaytickettravel.app.entities.OfferStatus;
import pl.onewaytickettravel.app.entities.Reservation;
import pl.onewaytickettravel.app.repository.OfferRepository;
import pl.onewaytickettravel.app.repository.ReservationRepository;

import java.time.LocalDateTime;
import java.util.Optional;


/**
 * Serwis odpowiedzialny za logikę rezerwacji ofert turystycznych.
 * Obsługuje walidację dostępności, użytkownika oraz zapis rezerwacji.
 *
 * Service responsible for travel offer reservation logic.
 * Handles availability validation, user lookup, and reservation persistence.
 */
@Service
public class ReservationService {


    /**
     * Repozytorium ofert turystycznych.
     * Repository for travel offers.
     */
    private final OfferRepository offerRepository;

    /**
     * Repozytorium użytkowników.
     * Repository for users.
     */
    private final UserRepository userRepository;

    /**
     * Repozytorium rezerwacji.
     * Repository for reservations.
     */
    private final ReservationRepository reservationRepository;


    /**
     * Konstruktor inicjalizujący serwis wymaganymi repozytoriami.
     *
     * Constructor initializing the service with required repositories.
     *
     * @param offerRepository repozytorium ofert
     *                        offer repository
     * @param userRepository repozytorium użytkowników
     *                       user repository
     * @param reservationRepository repozytorium rezerwacji
     *                              reservation repository
     */
    public ReservationService(OfferRepository offerRepository, UserRepository userRepository, ReservationRepository reservationRepository) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }



    /**
     * Dokonuje rezerwacji oferty dla zalogowanego użytkownika.
     * Sprawdza dostępność oferty, istnienie użytkownika oraz unikalność rezerwacji.
     *
     * Reserves an offer for the logged-in user.
     * Validates offer availability, user existence, and uniqueness of reservation.
     *
     * @param offerId identyfikator oferty
     *                offer ID
     * @param email adres e-mail użytkownika
     *              user's email address
     * @throws IllegalArgumentException jeśli oferta lub użytkownik nie istnieje
     *                                  if offer or user does not exist
     * @throws IllegalStateException jeśli oferta jest niedostępna lub już zarezerwowana przez użytkownika
     *                               if offer is unavailable or already reserved by the user
     */
    public void reserveOffer(Long offerId, String email) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono oferty o ID: " + offerId));

        if (offer.getStatus() != OfferStatus.AVAILABLE) {
            throw new IllegalStateException("Oferta jest niedostępna do rezerwacji.");
        }

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("Nie znaleziono użytkownika: " + email);
        }

        Optional<Reservation> existing = reservationRepository.findByOfferAndUser(offer, user.orElse(null));
        if (existing.isPresent()) {
            throw new IllegalStateException("Użytkownik już zarezerwował tę ofertę.");
        }

        Reservation reservation = new Reservation();
        reservation.setUser(user.orElse(null));
        reservation.setOffer(offer);
        reservation.setReservationDate(LocalDateTime.now());

        reservationRepository.save(reservation);

        offer.setStatus(OfferStatus.RESERVED);
        offerRepository.save(offer);
    }
}
