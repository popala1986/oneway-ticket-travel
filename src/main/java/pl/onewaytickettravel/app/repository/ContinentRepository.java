package pl.onewaytickettravel.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.onewaytickettravel.app.entities.Continent;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Long> {

}