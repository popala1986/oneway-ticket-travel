package pl.onewaytickettravel.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.onewaytickettravel.app.dto.OfferDto;
import pl.onewaytickettravel.app.entities.Continent;
import pl.onewaytickettravel.app.entities.Country;
import pl.onewaytickettravel.app.entities.Offer;
import pl.onewaytickettravel.app.mapper.OfferMapper;
import pl.onewaytickettravel.app.model.SearchFilter;
import pl.onewaytickettravel.app.repository.ContinentRepository;
import pl.onewaytickettravel.app.repository.CountryRepository;
import pl.onewaytickettravel.app.repository.OfferRepository;
import pl.onewaytickettravel.app.specification.OfferSpecification;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class OfferService {

    private final OfferRepository offerRepository;
    private final ContinentRepository continentRepository;
    private final CountryRepository countryRepository;
    private final OfferMapper offerMapper;

    public OfferService(OfferRepository offerRepository,
                        ContinentRepository continentRepository,
                        CountryRepository countryRepository,
                        OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.continentRepository = continentRepository;
        this.countryRepository = countryRepository;
        this.offerMapper = offerMapper;
    }

    /**
     * Wyszukuje oferty na podstawie dynamicznego filtra.
     * @param filter Obiekt z kryteriami wyszukiwania.
     * @return Lista znalezionych ofert w formacie DTO.
     */

    /**
     * Searches for offers based on dynamic filter criteria.
     * @param filter Object containing search parameters.
     * @return List of matching offers in DTO format.
     */


    @Transactional(readOnly = true)
    public List<OfferDto> searchOffers(SearchFilter filter) {
        OfferSpecification spec = new OfferSpecification(filter);

        return offerRepository.findAll(spec).stream()
                .map(offerMapper::offerToOfferDto)
                .collect(Collectors.toList());
    }

    /**
     * Zapisuje nową ofertę lub aktualizuje istniejącą na podstawie DTO.
     * @param offerDto Dane oferty.
     * @return Zapisana oferta w formacie DTO.
     */

    /**
     * Saves a new offer or updates an existing one based on the provided DTO.
     * @param offerDto Offer data.
     * @return The saved offer in DTO format.
     */

    public OfferDto saveOffer(OfferDto offerDto) {
        Continent continent = continentRepository.findByNameIgnoreCase(offerDto.getContinentName())
                .orElseThrow(() -> new NoSuchElementException("Continent with name " + offerDto.getContinentName() + " not found."));

        Country country = null;
        if (offerDto.getCountryName() != null && !offerDto.getCountryName().isBlank()) {
            country = countryRepository.findByNameIgnoreCase(offerDto.getCountryName())
                    .orElseThrow(() -> new NoSuchElementException("Country with name " + offerDto.getCountryName() + " not found."));
        }

        Offer offerToSave = offerMapper.offerDtoToOffer(offerDto, continent, country);
        Offer savedOffer = offerRepository.save(offerToSave);

        return offerMapper.offerToOfferDto(savedOffer);
    }
}