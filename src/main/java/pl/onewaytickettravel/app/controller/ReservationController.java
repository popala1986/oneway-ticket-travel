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


@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final OfferService offerService;
    private final OfferRepository offerRepository;

    public ReservationController(ReservationService reservationService, OfferService offerService, OfferRepository offerRepository) {
        this.reservationService = reservationService;
        this.offerService = offerService;
        this.offerRepository = offerRepository;
    }

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