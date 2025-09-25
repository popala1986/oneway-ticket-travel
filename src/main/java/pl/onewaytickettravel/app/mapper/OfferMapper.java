package pl.onewaytickettravel.app.mapper;

import org.springframework.stereotype.Component;
import pl.onewaytickettravel.app.dto.OfferDto;
import pl.onewaytickettravel.app.entities.Continent;
import pl.onewaytickettravel.app.entities.Country;
import pl.onewaytickettravel.app.entities.City;
import pl.onewaytickettravel.app.entities.Offer;


/**
 * Komponent odpowiedzialny za mapowanie między encją Offer a obiektem DTO OfferDto.
 * Umożliwia konwersję danych w obu kierunkach: DTO → Entity oraz Entity → DTO.
 *
 * Component responsible for mapping between the Offer entity and the OfferDto object.
 * Enables bidirectional conversion: DTO → Entity and Entity → DTO.
 */
@Component
public class OfferMapper {

    /**
     * Mapuje obiekt OfferDto na encję Offer.
     * Wymaga przekazania powiązanych encji: Continent, Country i City.
     *
     * Maps an OfferDto object to an Offer entity.
     * Requires associated entities: Continent, Country, and City.
     *
     * @param offerDto obiekt DTO zawierający dane oferty
     *                 DTO object containing offer data
     * @param continent kontynent przypisany do oferty
     *                  continent assigned to the offer
     * @param country kraj przypisany do oferty
     *                country assigned to the offer
     * @param city miasto przypisane do oferty
     *             city assigned to the offer
     * @return encja Offer utworzona na podstawie danych DTO
     *         Offer entity created from DTO data
     */
    public Offer offerDtoToOffer(OfferDto offerDto, Continent continent, Country country, City city) {
        Offer offer = new Offer();
        offer.setId(offerDto.getId());
        offer.setName(offerDto.getName());
        offer.setPrice(offerDto.getPrice());
        offer.setContinent(continent);
        offer.setCountry(country);
        offer.setCity(city);
        return offer;
    }

    /**
     * Mapuje encję Offer na obiekt DTO OfferDto.
     * Uwzględnia dane lokalizacyjne oraz status oferty.
     *
     * Maps an Offer entity to an OfferDto object.
     * Includes location data and offer status.
     *
     * @param offer encja zawierająca dane oferty
     *              entity containing offer data
     * @return obiekt DTO utworzony na podstawie encji
     *         DTO object created from the entity
     */
    public OfferDto offerToOfferDto(Offer offer) {
        OfferDto dto = new OfferDto();
        dto.setId(offer.getId());
        dto.setName(offer.getName());
        dto.setPrice(offer.getPrice());

        if (offer.getContinent() != null) {
            dto.setContinentName(offer.getContinent().getName());
        }

        if (offer.getCountry() != null) {
            dto.setCountryName(offer.getCountry().getName());
        }

        if (offer.getCity() != null) {
            dto.setCityName(offer.getCity().getName());
        }

        dto.setStatus(offer.getStatus().name());

        return dto;
    }
}