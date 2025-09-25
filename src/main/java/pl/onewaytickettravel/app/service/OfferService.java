package pl.onewaytickettravel.app.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.onewaytickettravel.app.dto.OfferDto;
import pl.onewaytickettravel.app.entities.City;
import pl.onewaytickettravel.app.entities.Continent;
import pl.onewaytickettravel.app.entities.Country;
import pl.onewaytickettravel.app.entities.Offer;
import pl.onewaytickettravel.app.exception.OfferNotFoundException;
import pl.onewaytickettravel.app.mapper.OfferMapper;
import pl.onewaytickettravel.app.model.SearchFilter;
import pl.onewaytickettravel.app.repository.CityRepository;
import pl.onewaytickettravel.app.repository.ContinentRepository;
import pl.onewaytickettravel.app.repository.CountryRepository;
import pl.onewaytickettravel.app.repository.OfferRepository;
import pl.onewaytickettravel.app.specification.OfferSpecification;
import java.util.List;
import java.util.NoSuchElementException;
import org.slf4j.Logger;

/**
 * Serwis odpowiedzialny za logikę biznesową związaną z ofertami turystycznymi.
 * Obsługuje wyszukiwanie, zapis, pobieranie i mapowanie danych ofert.
 *
 * Service responsible for business logic related to travel offers.
 * Handles searching, saving, retrieving, and mapping offer data.
 */
@Service
@Transactional
public class OfferService {

    /**
     * Logger do rejestrowania informacji o przetwarzanych ofertach.
     * Logger for recording information about processed offers.
     */
    private static final Logger logger = LoggerFactory.getLogger(OfferService.class);


    /**
     * Komunikat używany przy braku encji w bazie danych.
     * Message used when an entity is not found in the database.
     */
    private static final String NOT_FOUND_MESSAGE = " not found.";


    /**
     * Repozytorium ofert turystycznych.
     * Umożliwia operacje CRUD oraz filtrowanie ofert.
     *
     * Repository for travel offers.
     * Enables CRUD operations and offer filtering.
     */
    private final OfferRepository offerRepository;

    /**
     * Repozytorium kontynentów.
     * Służy do pobierania danych lokalizacyjnych dla ofert.
     *
     * Repository for continents.
     * Used to retrieve location data for offers.
     */
    private final ContinentRepository continentRepository;

    /**
     * Repozytorium krajów.
     * Wspiera mapowanie lokalizacji oferty.
     *
     * Repository for countries.
     * Supports offer location mapping.
     */
    private final CountryRepository countryRepository;

    /**
     * Mapper DTO ↔ encja dla obiektów ofert.
     * Umożliwia konwersję między warstwami aplikacji.
     *
     * DTO ↔ entity mapper for offer objects.
     * Enables conversion between application layers.
     */
    private final OfferMapper offerMapper;

    /**
     * Repozytorium miast.
     * Wykorzystywane przy przypisywaniu lokalizacji do oferty.
     *
     * Repository for cities.
     * Used when assigning location to an offer.
     */
    private final CityRepository cityRepository;


    /**
     * Konstruktor inicjalizujący serwis wymaganymi zależnościami.
     *
     * Constructor initializing the service with required dependencies.
     *
     * @param offerRepository repozytorium ofert
     *                        offer repository
     * @param continentRepository repozytorium kontynentów
     *                            continent repository
     * @param countryRepository repozytorium krajów
     *                          country repository
     * @param offerMapper mapper DTO ↔ encja
     *                    DTO ↔ entity mapper
     * @param cityRepository repozytorium miast
     *                       city repository
     */
    public OfferService(OfferRepository offerRepository,
                        ContinentRepository continentRepository,
                        CountryRepository countryRepository,
                        OfferMapper offerMapper, CityRepository cityRepository) {
        this.offerRepository = offerRepository;
        this.continentRepository = continentRepository;
        this.countryRepository = countryRepository;
        this.offerMapper = offerMapper;
        this.cityRepository = cityRepository;
    }

    /**
     * Wyszukuje oferty na podstawie dynamicznego filtra.
     * Używa specyfikacji JPA do budowania zapytania.
     *
     * Searches for offers based on dynamic filter criteria.
     * Uses JPA specification to build the query.
     *
     * @param filter obiekt z kryteriami wyszukiwania
     *               object containing search parameters
     * @return lista pasujących ofert w formacie DTO
     *         list of matching offers in DTO format
     */
    @Transactional(readOnly = true)
    public List<OfferDto> searchOffers(SearchFilter filter) {
        OfferSpecification spec = new OfferSpecification(filter);
        List<Offer> rawOffers = offerRepository.findAll(spec);
        rawOffers.forEach(o -> {
            String city = (o.getCity() != null) ? o.getCity().getName() : "brak miasta";
            logger.info("→ {} | Miasto: {}", o.getName(), city);
        });

        return rawOffers.stream()
                .map(offerMapper::offerToOfferDto)
                .toList();
    }

    /**
     * Zapisuje nową ofertę lub aktualizuje istniejącą na podstawie danych DTO.
     * Wyszukuje powiązane encje lokalizacyjne i mapuje DTO na encję.
     *
     * Saves a new offer or updates an existing one based on DTO data.
     * Looks up related location entities and maps DTO to entity.
     *
     * @param offerDto dane oferty
     *                 offer data
     * @return zapisana oferta w formacie DTO
     *         saved offer in DTO format
     */
    public OfferDto saveOffer(OfferDto offerDto) {
        Continent continent = continentRepository.findByNameIgnoreCase(offerDto.getContinentName())
                .orElseThrow(() -> new NoSuchElementException("Continent with name " + offerDto.getContinentName() + NOT_FOUND_MESSAGE));

        Country country = null;
        if (offerDto.getCountryName() != null && !offerDto.getCountryName().isBlank()) {
            country = countryRepository.findByNameIgnoreCase(offerDto.getCountryName())
                    .orElseThrow(() -> new NoSuchElementException("Country with name " + offerDto.getCountryName() + NOT_FOUND_MESSAGE));
        }

        City city = null;
        if (offerDto.getCityName() != null && !offerDto.getCityName().isBlank()) {
            city = cityRepository.findByNameIgnoreCase(offerDto.getCityName())
                    .orElseThrow(() -> new NoSuchElementException("City with name " + offerDto.getCityName() + NOT_FOUND_MESSAGE));
        }

        Offer offerToSave = offerMapper.offerDtoToOffer(offerDto, continent, country,city);
        Offer savedOffer = offerRepository.save(offerToSave);

        return offerMapper.offerToOfferDto(savedOffer);
    }

    /**
     * Pobiera wszystkie oferty z bazy danych.
     *
     * Retrieves all offers from the database.
     *
     * @return lista wszystkich ofert w formacie DTO
     *         list of all offers in DTO format
     */
    public List<OfferDto> getAllOffers() {
        List<Offer> offers = offerRepository.findAll();
        return offers.stream()
                .map(offerMapper::offerToOfferDto)
                .toList();
    }

    /**
     * Pobiera ofertę na podstawie jej nazwy.
     * Rzuca wyjątek, jeśli oferta nie istnieje.
     *
     * Retrieves an offer by its name.
     * Throws an exception if the offer does not exist.
     *
     * @param offerName nazwa oferty
     *                  offer name
     * @return obiekt DTO odpowiadający nazwie
     *         DTO object matching the name
     */
    public OfferDto getOfferByName(String offerName) {
        Offer offer = offerRepository.findByName(offerName)
                .orElseThrow(() -> new OfferNotFoundException("No such offer with name '" + offerName + "' exists."));

        return offerMapper.offerToOfferDto(offer);
    }
}