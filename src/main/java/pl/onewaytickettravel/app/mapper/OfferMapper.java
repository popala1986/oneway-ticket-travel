package pl.onewaytickettravel.app.mapper;

import org.springframework.stereotype.Component;
import pl.onewaytickettravel.app.dto.OfferDto;
import pl.onewaytickettravel.app.entities.Continent;
import pl.onewaytickettravel.app.entities.Country;
import pl.onewaytickettravel.app.entities.City;
import pl.onewaytickettravel.app.entities.Offer;

@Component
public class OfferMapper {

    /** Mapping DTO -> Entity */
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

    /** Mapping Entity -> DTO */
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