package pl.onewaytickettravel.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/**
 * Encja reprezentująca kraj przypisany do kontynentu.
 * Kraj może zawierać wiele miast i musi mieć unikalną nazwę.
 *
 * Entity representing a country assigned to a continent.
 * A country can contain multiple cities and must have a unique name.
 */
@Entity
@Table(name = "countries")
public class Country implements Serializable {


    /**
     * Identyfikator kraju (klucz główny).
     * Country identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * Nazwa kraju (unikalna, wymagana, maksymalnie 20 znaków).
     * Country name (unique, required, max 20 characters).
     */
    @Column(name = "name", nullable = false, unique = true, length = 20)
    @NotBlank(message = "Country name must not be blank")
    @Size(max = 20, message = "Country name can have a maximum of 20 characters")
    private String name;


    /**
     * Kontynent, do którego należy kraj.
     * Continent to which the country belongs.
     */
    @ManyToOne
    @JoinColumn(name = "continent_id")
    private Continent continent;


    /**
     * Lista miast przypisanych do kraju.
     * List of cities assigned to the country.
     */
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<City> cities;


    /**
     * Konstruktor inicjalizujący pola kraju.
     *
     * Constructor initializing country fields.
     *
     * @param id identyfikator kraju
     *           country ID
     * @param name nazwa kraju
     *             country name
     * @param continent kontynent przypisany do kraju
     *                  continent assigned to the country
     */
    public Country(Long id, String name, Continent continent) {
        this.id = id;
        this.name = name;
        this.continent = continent;
    }

    /**
     * Konstruktor domyślny wymagany przez JPA.
     * Default constructor required by JPA.
     */
    public Country() {
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

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }




    /**
     * Porównuje kraje na podstawie ich identyfikatora.
     * Compares countries based on their identifier.
     *
     * @param o obiekt do porównania
     *          object to compare
     * @return true jeśli identyfikatory są równe
     *         true if identifiers are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        Country country = (Country) o;
        return Objects.equals(id, country.id);
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
     * Zwraca tekstową reprezentację kraju.
     * Returns textual representation of the country.
     *
     * @return string z informacjami o kraju
     *         string with country details
     */
    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", continent=" + (continent != null ? continent.getName() : "null") +
                '}';
    }
}
