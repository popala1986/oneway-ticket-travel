package pl.onewaytickettravel.app.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.onewaytickettravel.app.entities.Offer;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles("test")
class OfferRepositoryIntegrationTest {

    @Autowired
    private OfferRepository offerRepository;

    @Test
    void shouldFindOfferByName() {
        Offer offer = offerRepository.findByName("Maroko All Inclusive")
                .orElseThrow(() -> new AssertionError("Oferta nie została znaleziona"));

        assertEquals(BigDecimal.valueOf(4999.99), offer.getPrice());
        assertEquals("Maroko All Inclusive", offer.getName());
        assertEquals("AVAILABLE", offer.getStatus());
        assertEquals("Afryka", offer.getContinent().getName());
    }
}