package pl.onewaytickettravel.app.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import pl.onewaytickettravel.app.entities.Offer;
import pl.onewaytickettravel.app.model.SearchFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Specyfikacja JPA umożliwiająca dynamiczne filtrowanie ofert turystycznych.
 * Buduje zapytanie na podstawie kryteriów przekazanych w obiekcie SearchFilter.
 *
 * JPA specification enabling dynamic filtering of travel offers.
 * Constructs a query based on criteria provided in the SearchFilter object.
 */
public class OfferSpecification implements Specification<Offer> {


    /**
     * Obiekt zawierający kryteria wyszukiwania.
     * Object containing search criteria.
     */
    private final SearchFilter filter;


    /**
     * Konstruktor inicjalizujący specyfikację z filtrem wyszukiwania.
     *
     * Constructor initializing the specification with a search filter.
     *
     * @param filter obiekt SearchFilter z kryteriami
     *               SearchFilter object with criteria
     */
    public OfferSpecification(SearchFilter filter) {
        this.filter = filter;
    }


    /**
     * Buduje predykaty (warunki WHERE) na podstawie przekazanych kryteriów.
     * Uwzględnia nazwę kontynentu, kraju, miasta oraz zakres cenowy.
     *
     * Builds predicates (WHERE conditions) based on provided criteria.
     * Includes continent, country, city names and price range.
     *
     * @param root encja główna (Offer)
     *             root entity (Offer)
     * @param query zapytanie kryterialne
     *              criteria query
     * @param builder konstruktor predykatów
     *                predicate builder
     * @return połączony predykat z wszystkimi warunkami
     *         combined predicate with all conditions
     */
    @Override
    public Predicate toPredicate(Root<Offer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();


        if (filter.getContinentName() != null && !filter.getContinentName().isBlank()) {
            String continentName = filter.getContinentName().trim().toLowerCase();
            predicates.add(builder.like(
                    builder.lower(root.get("continent").get("name")),
                    "%" + continentName + "%"
            ));
        }


        if (filter.getCountryName() != null && !filter.getCountryName().isBlank()) {
            String countryName = filter.getCountryName().trim().toLowerCase();
            predicates.add(builder.like(
                    builder.lower(root.get("country").get("name")),
                    "%" + countryName + "%"
            ));
        }

        if (filter.getCityName() != null && !filter.getCityName().isBlank()) {
            String cityName = filter.getCityName().trim().toLowerCase();
            predicates.add(builder.like(
                    builder.lower(root.get("city").get("name")),
                    "%" + cityName + "%"
            ));
        }

        if (filter.getMinPrice() != null && filter.getMaxPrice() != null) {
            predicates.add(builder.between(root.get("price"), filter.getMinPrice(), filter.getMaxPrice()));
        } else if (filter.getMinPrice() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("price"), filter.getMinPrice()));
        } else if (filter.getMaxPrice() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("price"), filter.getMaxPrice()));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}