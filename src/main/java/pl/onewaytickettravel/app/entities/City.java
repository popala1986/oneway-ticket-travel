package pl.onewaytickettravel.app.entities;

import jakarta.persistence.*;


/**
 * Encja reprezentująca miasto docelowe powiązane z ofertą turystyczną.
 * Miasto należy do konkretnego kraju i posiada unikalną nazwę.
 *
 * Entity representing a destination city associated with a travel offer.
 * Each city belongs to a specific country and has a unique name.
 */
@Entity
@Table(name = "cities")
public class City {

    /**
     * Identyfikator miasta (klucz główny).
     * City identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * Nazwa miasta (unikalna, wymagana, maksymalnie 20 znaków).
     * City name (unique, required, max 20 characters).
     */
    @Column(name = "name", nullable = false, unique = true, length = 20)
    private String name;

    /**
     * Kraj, do którego należy miasto.
     * Country to which the city belongs.
     */
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    /**
     * Konstruktor inicjalizujący wszystkie pola miasta.
     *
     * Constructor initializing all city fields.
     *
     * @param id identyfikator miasta
     *           city ID
     * @param name nazwa miasta
     *             city name
     * @param country kraj powiązany z miastem
     *                country associated with the city
     */
    public City(Long id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    /**
     * Konstruktor domyślny wymagany przez JPA.
     * Default constructor required by JPA.
     */
    public City() {
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
