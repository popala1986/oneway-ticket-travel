package pl.onewaytickettravel.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.onewaytickettravel.app.auth.entities.User;
import pl.onewaytickettravel.app.entities.Offer;
import pl.onewaytickettravel.app.entities.Reservation;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findBy(Reservation reservation);

    Optional<Reservation> findByOfferAndUser(Offer offer, User user);


}
