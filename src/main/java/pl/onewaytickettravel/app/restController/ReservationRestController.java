package pl.onewaytickettravel.app.restController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.onewaytickettravel.app.service.ReservationService;

import java.security.Principal;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reserve/{offerId}")
    public ResponseEntity<String> reserveOffer(@PathVariable Long offerId, Principal principal) {
        reservationService.reserveOffer(offerId, principal.getName());
        return ResponseEntity.ok("Offer reserved successfully.");
    }
}
