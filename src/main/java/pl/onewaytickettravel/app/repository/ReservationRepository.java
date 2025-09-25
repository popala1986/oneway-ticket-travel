package pl.onewaytickettravel.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.onewaytickettravel.app.auth.entities.User;
import pl.onewaytickettravel.app.entities.Offer;
import pl.onewaytickettravel.app.entities.Reservation;

import java.util.Optional;

/**
 * Repozytorium JPA dla encji Reservation.
 * Umożliwia operacje CRUD oraz wyszukiwanie rezerwacji na podstawie użytkownika i oferty.
 *
 * JPA repository for the Reservation entity.
 * Provides CRUD operations and lookup of reservations by user and offer.
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Wyszukuje rezerwację na podstawie użytkownika i oferty.
     *
     * Finds a reservation based on the user and the offer.
     *
     * @param offer zarezerwowana oferta
     *              reserved offer
     * @param user użytkownik dokonujący rezerwacji
     *             user who made the reservation
     * @return Optional zawierający rezerwację, jeśli istnieje
     *         Optional containing the reservation if found
     */
    Optional<Reservation> findByOfferAndUser(Offer offer, User user);
}