package pl.onewaytickettravel.app.entities;

import jakarta.persistence.*;
import pl.onewaytickettravel.app.auth.entities.User;
import java.time.LocalDateTime;


/**
 * Encja reprezentująca rezerwację oferty turystycznej przez użytkownika.
 * Zawiera informacje o użytkowniku, ofercie oraz dacie rezerwacji.
 *
 * Entity representing a reservation of a travel offer by a user.
 * Contains information about the user, the offer, and the reservation date.
 */
@Entity
@Table(name = "reservations")
public class Reservation {

    /**
     * Identyfikator rezerwacji (klucz główny).
     * Reservation identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * Użytkownik, który dokonał rezerwacji.
     * User who made the reservation.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    /**
     * Oferta, która została zarezerwowana.
     * Offer that has been reserved.
     */
    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;


    /**
     * Data i godzina dokonania rezerwacji.
     * Date and time when the reservation was made.
     */
    private LocalDateTime reservationDate;


    /**
     * Konstruktor inicjalizujący wszystkie pola rezerwacji.
     *
     * Constructor initializing all reservation fields.
     *
     * @param id identyfikator rezerwacji
     *           reservation ID
     * @param user użytkownik dokonujący rezerwacji
     *             user making the reservation
     * @param offer zarezerwowana oferta
     *              reserved offer
     * @param reservationDate data rezerwacji
     *                        reservation date
     */
    public Reservation(Long id, User user, Offer offer, LocalDateTime reservationDate) {
        this.id = id;
        this.user = user;
        this.offer = offer;
        this.reservationDate = reservationDate;
    }

    /**
     * Konstruktor domyślny wymagany przez JPA.
     * Default constructor required by JPA.
     */
    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }
}