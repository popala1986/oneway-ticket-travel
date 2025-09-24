package pl.onewaytickettravel.app.restController;

import org.springframework.web.bind.annotation.*;
import pl.onewaytickettravel.app.dto.OfferDto;
import pl.onewaytickettravel.app.model.SearchFilter;
import pl.onewaytickettravel.app.service.OfferService;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
public class OfferRestController {

    private final OfferService offerService;

    public OfferRestController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/search")
    public List<OfferDto> searchOffers(@RequestBody SearchFilter filter) {
        return offerService.searchOffers(filter);
    }

    @GetMapping
    public List<OfferDto> getAllOffers() {
        return offerService.getAllOffers();
    }

    @GetMapping("/offers")
    public OfferDto getOfferByName(@RequestParam String offerName) {
        return offerService.getOfferByName(offerName);
    }
}
