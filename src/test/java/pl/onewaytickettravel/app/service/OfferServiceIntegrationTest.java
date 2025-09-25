package pl.onewaytickettravel.app.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.onewaytickettravel.app.dto.OfferDto;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class OfferServiceIntegrationTest {

    @Autowired
    private OfferService offerService;

    @Test
    void shouldLoadOffersFromDatabase() {
        List<OfferDto> offers = offerService.getAllOffers();
        assertEquals(5, offers.size());
    }
}
