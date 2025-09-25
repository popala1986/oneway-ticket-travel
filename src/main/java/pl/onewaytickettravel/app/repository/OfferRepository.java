package pl.onewaytickettravel.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pl.onewaytickettravel.app.entities.Offer;

import java.util.Optional;

/**
 * Repozytorium JPA dla encji Offer.
 * Umożliwia operacje CRUD, wyszukiwanie po nazwie oraz dynamiczne filtrowanie ofert.
 *
 * JPA repository for the Offer entity.
 * Provides CRUD operations, name-based lookup, and dynamic offer filtering.
 */
@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>, JpaSpecificationExecutor<Offer> {

    /**
     * Wyszukuje ofertę na podstawie jej nazwy.
     *
     * Finds an offer by its name.
     *
     * @param name nazwa oferty
     *             offer name
     * @return Optional zawierający ofertę, jeśli istnieje
     *         Optional containing the offer if found
     */
    Optional<Offer> findByName(String name);
}