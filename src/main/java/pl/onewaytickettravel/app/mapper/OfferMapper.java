package pl.onewaytickettravel.app.mapper;

import org.springframework.stereotype.Component;
import pl.onewaytickettravel.app.dto.OfferDto;
import pl.onewaytickettravel.app.entities.Continent;
import pl.onewaytickettravel.app.entities.Offer;

@Component
public class OfferMapper {

    /** Mapping DTO -> Entity */

    public Offer offerDtoToOffer(OfferDto offerDto, Continent continent) {
        Offer offer = new Offer();
        offer.setId(offerDto.getId());
        offer.setName(offerDto.getName());
        offer.setPrice(offerDto.getPrice());
        offer.setUserName(offerDto.getUserName());
        offer.setContinent(continent);
        return offer;
    }

    /** Mapping Entity -> DTO */

    public OfferDto offerToOfferDto(Offer offer) {
        OfferDto dto = new OfferDto();
        dto.setId(offer.getId());
        dto.setName(offer.getName());
        dto.setPrice(offer.getPrice());
        dto.setUserName(offer.getUserName());

        if (offer.getContinent() != null) {
            dto.setContinentId(offer.getContinent().getId());
            dto.setContinentName(offer.getContinent().getName());
        }

        return dto;
    }
}