package pl.onewaytickettravel.app.restController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.onewaytickettravel.app.service.ReservationService;

import java.security.Principal;

/**
 * Kontroler REST obsługujący rezerwacje ofert turystycznych.
 * Umożliwia dokonywanie rezerwacji przez zalogowanych użytkowników.
 *
 * REST controller handling travel offer reservations.
 * Allows logged-in users to make reservations.
 */
@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

    /**
     * Serwis odpowiedzialny za logikę rezerwacji.
     * Service responsible for reservation logic.
     */
    private final ReservationService reservationService;

    /**
     * Konstruktor inicjalizujący kontroler z serwisem rezerwacji.
     *
     * Constructor initializing the controller with the reservation service.
     *
     * @param reservationService serwis rezerwacji
     *                           reservation service
     */
    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Endpoint umożliwiający rezerwację oferty przez zalogowanego użytkownika.
     *
     * Endpoint allowing a logged-in user to reserve an offer.
     *
     * @param offerId identyfikator oferty do rezerwacji
     *                ID of the offer to be reserved
     * @param principal obiekt reprezentujący zalogowanego użytkownika
     *                  principal representing the authenticated user
     * @return odpowiedź HTTP z komunikatem o powodzeniu
     *         HTTP response with success message
     */
    @PostMapping("/reserve/{offerId}")
    public ResponseEntity<String> reserveOffer(@PathVariable Long offerId, Principal principal) {
        reservationService.reserveOffer(offerId, principal.getName());
        return ResponseEntity.ok("Offer reserved successfully.");
    }
}
