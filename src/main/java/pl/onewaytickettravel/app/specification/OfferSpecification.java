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

public class OfferSpecification implements Specification<Offer> {

    private final SearchFilter filter;

    public OfferSpecification(SearchFilter filter) {
        this.filter = filter;
    }

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