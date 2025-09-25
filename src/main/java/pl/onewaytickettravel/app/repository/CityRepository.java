package pl.onewaytickettravel.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.onewaytickettravel.app.entities.City;

import java.util.Optional;

/**
 * Repozytorium JPA dla encji City.
 * Umożliwia operacje CRUD oraz wyszukiwanie miast po nazwie bez uwzględniania wielkości liter.
 *
 * JPA repository for the City entity.
 * Provides CRUD operations and case-insensitive city name lookup.
 */
public interface CityRepository extends JpaRepository<City, Long> {

    /**
     * Wyszukuje miasto na podstawie nazwy, ignorując wielkość liter.
     *
     * Finds a city by its name, ignoring case sensitivity.
     *
     * @param name nazwa miasta
     *             city name
     * @return Optional zawierający miasto, jeśli istnieje
     *         Optional containing the city if found
     */
    Optional<City> findByNameIgnoreCase(String name);
}