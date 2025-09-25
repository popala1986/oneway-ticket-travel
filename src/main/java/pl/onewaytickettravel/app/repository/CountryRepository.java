package pl.onewaytickettravel.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.onewaytickettravel.app.entities.Country;

import java.util.Optional;

/**
 * Repozytorium JPA dla encji Country.
 * Umożliwia operacje CRUD oraz wyszukiwanie krajów po nazwie bez uwzględniania wielkości liter.
 *
 * JPA repository for the Country entity.
 * Provides CRUD operations and case-insensitive country name lookup.
 */
public interface CountryRepository extends JpaRepository<Country, Long> {

    /**
     * Wyszukuje kraj na podstawie nazwy, ignorując wielkość liter.
     *
     * Finds a country by its name, ignoring case sensitivity.
     *
     * @param name nazwa kraju
     *             country name
     * @return Optional zawierający kraj, jeśli istnieje
     *         Optional containing the country if found
     */
    Optional<Country> findByNameIgnoreCase(String name);
}