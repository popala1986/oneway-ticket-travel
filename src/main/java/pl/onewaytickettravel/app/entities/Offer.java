package pl.onewaytickettravel.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;


/**
 * Encja reprezentująca ofertę turystyczną dostępną w systemie.
 * Oferta zawiera informacje o nazwie, cenie, lokalizacji oraz statusie dostępności.
 *
 * Entity representing a travel offer available in the system.
 * The offer includes details such as name, price, location, and availability status.
 */
@Entity
@Table(name = "offers")
public class Offer implements Serializable {


    /**
     * Identyfikator oferty (klucz główny).
     * Offer identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * Nazwa oferty (wymagana, maksymalnie 150 znaków).
     * Offer name (required, max 150 characters).
     */
    @Column(nullable = false, length = 150)
    @NotBlank(message = "Offer name must not be blank")
    @Size(max = 150, message = "Offer name can have a maximum of 150 characters")
    private String name;


    /**
     * Cena oferty (większa niż 0, maksymalnie 10 cyfr przed przecinkiem i 2 po).
     * Offer price (greater than 0, up to 10 digits before decimal and 2 after).
     */
    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price can have up to 10 digits before the decimal and 2 after")
    private BigDecimal price;

    /**
     * Kontynent przypisany do oferty (wymagany).
     * Continent associated with the offer (required).
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "continent_id", nullable = false)
    private Continent continent;


    /**
     * Kraj przypisany do oferty (opcjonalny).
     * Country associated with the offer (optional).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;


    /**
     * Miasto docelowe przypisane do oferty (wymagane).
     * Destination city associated with the offer (required).
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;


    /**
     * Status dostępności oferty (np. AVAILABLE, RESERVED, CANCELLED).
     * Offer availability status (e.g., AVAILABLE, RESERVED, CANCELLED).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OfferStatus status = OfferStatus.AVAILABLE;


    /**
     * Konstruktor domyślny wymagany przez JPA.
     * Default constructor required by JPA.
     */
    public Offer() {
    }

    /**
     * Konstruktor inicjalizujący podstawowe pola oferty.
     *
     * Constructor initializing basic offer fields.
     *
     * @param name nazwa oferty
     *             offer name
     * @param price cena oferty
     *              offer price
     * @param continent kontynent
     *                  continent
     * @param country kraj
     *                country
     * @param city miasto
     *             city
     */
    public Offer(String name, BigDecimal price, Continent continent, Country country, City city) {
        this.name = name;
        this.price = price;
        this.continent = continent;
        this.country = country;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }


    /**
     * Porównuje oferty na podstawie ich identyfikatora.
     * Compares offers based on their identifier.
     *
     * @param o obiekt do porównania
     *          object to compare
     * @return true jeśli identyfikatory są równe
     *         true if identifiers are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;
        Offer offer = (Offer) o;
        return Objects.equals(id, offer.id);
    }

    /**
     * Zwraca wartość hash na podstawie identyfikatora.
     * Returns hash value based on the identifier.
     *
     * @return wartość hash
     *         hash value
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    /**
     * Zwraca tekstową reprezentację oferty.
     * Returns textual representation of the offer.
     *
     * @return string z informacjami o ofercie
     *         string with offer details
     */
    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", continent=" + (continent != null ? continent.getName() : "null") +
                ", country=" + (country != null ? country.getName() : "null") +
                ", city=" + (city != null ? city.getName() : "null") +
                '}';
    }
}
