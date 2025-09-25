package pl.onewaytickettravel.app.entities;


/**
 * Enum reprezentujący status dostępności oferty turystycznej.
 * Może przyjmować wartości: AVAILABLE, RESERVED, CANCELLED.
 *
 * Enum representing the availability status of a travel offer.
 * Possible values: AVAILABLE, RESERVED, CANCELLED.
 */
public enum OfferStatus {

    /**
     * Oferta jest dostępna do rezerwacji.
     * Offer is available for booking.
     */
    AVAILABLE,

    /**
     * Oferta została zarezerwowana przez użytkownika.
     * Offer has been reserved by a user.
     */
    RESERVED,

    /**
     * Oferta została anulowana i nie jest dostępna.
     * Offer has been cancelled and is no longer available.
     */
    CANCELLED
}