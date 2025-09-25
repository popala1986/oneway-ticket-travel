package pl.onewaytickettravel.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.onewaytickettravel.app.entities.Continent;

import java.util.Optional;

/**
 * Repozytorium JPA dla encji Continent.
 * Umożliwia operacje CRUD oraz wyszukiwanie kontynentów po nazwie bez uwzględniania wielkości liter.
 *
 * JPA repository for the Continent entity.
 * Provides CRUD operations and case-insensitive continent name lookup.
 */
@Repository
public interface ContinentRepository extends JpaRepository<Continent, Long> {

    /**
     * Wyszukuje kontynent na podstawie nazwy, ignorując wielkość liter.
     *
     * Finds a continent by its name, ignoring case sensitivity.
     *
     * @param name nazwa kontynentu
     *             continent name
     * @return Optional zawierający kontynent, jeśli istnieje
     *         Optional containing the continent if found
     */
    Optional<Continent> findByNameIgnoreCase(String name);
}