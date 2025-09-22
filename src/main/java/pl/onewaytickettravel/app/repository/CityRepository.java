package pl.onewaytickettravel.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.onewaytickettravel.app.entities.City;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByNameIgnoreCase(String name);
}
