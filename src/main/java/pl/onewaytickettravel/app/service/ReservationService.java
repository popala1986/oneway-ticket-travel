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

@Service
public class ReservationService {



    private final OfferRepository offerRepository;

    private final UserRepository userRepository;

    private final ReservationRepository reservationRepository;

    public ReservationService(OfferRepository offerRepository, UserRepository userRepository, ReservationRepository reservationRepository) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }


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
