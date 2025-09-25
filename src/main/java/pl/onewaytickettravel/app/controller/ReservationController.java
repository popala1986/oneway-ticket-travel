package pl.onewaytickettravel.app.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.onewaytickettravel.app.entities.Offer;
import pl.onewaytickettravel.app.repository.OfferRepository;
import pl.onewaytickettravel.app.service.OfferService;
import pl.onewaytickettravel.app.service.ReservationService;
import java.security.Principal;


/**
 * Kontroler obsługujący proces rezerwacji ofert turystycznych.
 * Odpowiada za zapis rezerwacji i przekazanie danych do widoku potwierdzenia.
 *
 * Controller handling the reservation process for travel offers.
 * Responsible for saving reservations and passing data to the confirmation view.
 */
@Controller
@RequestMapping("/reservations")
public class ReservationController {


    /**
     * Serwis odpowiedzialny za logikę rezerwacji.
     * Service responsible for reservation logic.
     */
    private final ReservationService reservationService;

    /**
     * Serwis ofert turystycznych.
     * Service for travel offers.
     */
    private final OfferService offerService;

    /**
     * Repozytorium ofert turystycznych.
     * Repository for travel offers.
     */
    private final OfferRepository offerRepository;


    /**
     * Konstruktor inicjalizujący kontroler z wymaganymi serwisami.
     *
     * Constructor initializing the controller with required services.
     *
     * @param reservationService serwis rezerwacji
     *                           reservation service
     * @param offerService serwis ofert
     *                     offer service
     * @param offerRepository repozytorium ofert
     *                        offer repository
     */
    public ReservationController(ReservationService reservationService, OfferService offerService, OfferRepository offerRepository) {
        this.reservationService = reservationService;
        this.offerService = offerService;
        this.offerRepository = offerRepository;
    }


    /**
     * Obsługuje żądanie rezerwacji oferty na podstawie jej ID.
     * Po zapisaniu rezerwacji przekazuje dane do widoku potwierdzenia.
     *
     * Handles reservation request for an offer by its ID.
     * After saving the reservation, passes data to the confirmation view.
     *
     * @param offerId identyfikator oferty
     *                offer identifier
     * @param principal obiekt reprezentujący zalogowanego użytkownika
     *                  principal representing the logged-in user
     * @param model model widoku do przekazania danych
     *              view model for passing data
     * @return nazwa widoku potwierdzenia rezerwacji
     *         name of the reservation confirmation view
     */
    @PostMapping("/reserve/{id}")
    public String reserveOffer(@PathVariable("id") Long offerId, Principal principal, Model model) {
        reservationService.reserveOffer(offerId, principal.getName());

        Offer offer = offerRepository.findById(offerId).orElseThrow();

        model.addAttribute("reservationSuccess", true);
        model.addAttribute("reservedOfferName", offer.getName());
        model.addAttribute("reservedOfferCity", offer.getCity().getName());
        model.addAttribute("reservedOfferPrice", offer.getPrice());

        return "reservation-confirmation";
    }
}