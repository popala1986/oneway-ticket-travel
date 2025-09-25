package pl.onewaytickettravel.app.restController;

import org.springframework.web.bind.annotation.*;
import pl.onewaytickettravel.app.dto.OfferDto;
import pl.onewaytickettravel.app.model.SearchFilter;
import pl.onewaytickettravel.app.service.OfferService;

import java.util.List;

/**
 * Kontroler REST obsługujący operacje związane z ofertami turystycznymi.
 * Umożliwia wyszukiwanie, pobieranie wszystkich ofert oraz pobieranie oferty po nazwie.
 *
 * REST controller handling operations related to travel offers.
 * Enables searching, retrieving all offers, and fetching an offer by name.
 */
@RestController
@RequestMapping("/api/offers")
public class OfferRestController {

    /**
     * Serwis odpowiedzialny za logikę biznesową ofert.
     * Service responsible for business logic related to offers.
     */
    private final OfferService offerService;

    /**
     * Konstruktor inicjalizujący kontroler z serwisem ofert.
     *
     * Constructor initializing the controller with the offer service.
     *
     * @param offerService serwis ofert
     *                     offer service
     */
    public OfferRestController(OfferService offerService) {
        this.offerService = offerService;
    }

    /**
     * Przeszukuje oferty na podstawie przekazanych filtrów.
     *
     * Searches offers based on provided filter criteria.
     *
     * @param filter obiekt zawierający kryteria wyszukiwania
     *               object containing search criteria
     * @return lista pasujących ofert DTO
     *         list of matching offer DTOs
     */
    @PostMapping("/search")
    public List<OfferDto> searchOffers(@RequestBody SearchFilter filter) {
        return offerService.searchOffers(filter);
    }

    /**
     * Zwraca wszystkie dostępne oferty.
     *
     * Returns all available offers.
     *
     * @return lista wszystkich ofert DTO
     *         list of all offer DTOs
     */
    @GetMapping
    public List<OfferDto> getAllOffers() {
        return offerService.getAllOffers();
    }

    /**
     * Zwraca ofertę na podstawie jej nazwy.
     *
     * Returns an offer based on its name.
     *
     * @param offerName nazwa oferty
     *                  offer name
     * @return obiekt DTO odpowiadający nazwie oferty
     *         DTO object matching the offer name
     */
    @GetMapping("/offers")
    public OfferDto getAllOfferByName(@RequestParam String offerName) {
        return offerService.getOfferByName(offerName);
    }
}
