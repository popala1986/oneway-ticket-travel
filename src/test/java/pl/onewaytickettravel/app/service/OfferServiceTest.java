package pl.onewaytickettravel.app.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.onewaytickettravel.app.dto.OfferDto;
import pl.onewaytickettravel.app.entities.Offer;
import pl.onewaytickettravel.app.mapper.OfferMapper;
import pl.onewaytickettravel.app.repository.OfferRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class OfferServiceTest {

    @Mock
    private OfferRepository offerRepository;

    @Mock
    private OfferMapper offerMapper;

    @InjectMocks
    private OfferService offerService;

    public OfferServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnMappedOfferDtos() {
        // given
        Offer offer = new Offer();
        offer.setId(1L);
        offer.setName("Test Offer");
        offer.setPrice(BigDecimal.valueOf(1999.99));

        OfferDto dto = new OfferDto();
        dto.setId(1L);
        dto.setName("Test Offer");
        dto.setPrice(BigDecimal.valueOf(1999.99));

        when(offerRepository.findAll()).thenReturn(List.of(offer));
        when(offerMapper.offerToOfferDto(offer)).thenReturn(dto);

        // when
        List<OfferDto> result = offerService.getAllOffers();

        // then
        assertEquals(1, result.size());
        assertEquals("Test Offer", result.get(0).getName());
        assertEquals(BigDecimal.valueOf(1999.99), result.get(0).getPrice());
    }
}
