package pl.onewaytickettravel.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/**
 * Encja reprezentująca kontynent, do którego przypisane są kraje.
 * Nazwa kontynentu musi być unikalna i niepusta.
 *
 * Entity representing a continent to which countries are assigned.
 * The continent name must be unique and non-blank.
 */
@Entity
@Table(name = "continents")
public class Continent implements Serializable {


    /**
     * Identyfikator kontynentu (klucz główny).
     * Continent identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * Nazwa kontynentu (unikalna, wymagana, maksymalnie 20 znaków).
     * Continent name (unique, required, max 20 characters).
     */
    @Column(name = "name", nullable = false, unique = true, length = 20)
    @NotBlank(message = "Continent name must not be blank")
    @Size(max = 20, message = "Continent name can have a maximum of 20 characters")
    private String name;


    /**
     * Lista krajów przypisanych do kontynentu.
     * List of countries assigned to the continent.
     */
    @OneToMany(mappedBy = "continent", fetch = FetchType.LAZY)
    private List<Country> countryList;

    /**
     * Konstruktor inicjalizujący wszystkie pola kontynentu.
     *
     * Constructor initializing all continent fields.
     *
     * @param id identyfikator kontynentu
     *           continent ID
     * @param name nazwa kontynentu
     *             continent name
     * @param countryList lista krajów przypisanych do kontynentu
     *                    list of countries assigned to the continent
     */
    public Continent(Long id, String name, List<Country> countryList) {
        this.id = id;
        this.name = name;
        this.countryList = countryList;
    }

    /**
     * Konstruktor domyślny wymagany przez JPA.
     * Default constructor required by JPA.
     */
    public Continent() {
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

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }


    /**
     * Porównuje kontynenty na podstawie ich identyfikatora.
     * Compares continents based on their identifier.
     *
     * @param o obiekt do porównania
     *          object to compare
     * @return true jeśli identyfikatory są równe
     *         true if identifiers are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Continent continent = (Continent) o;
        return Objects.equals(id, continent.id);
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
     * Zwraca tekstową reprezentację kontynentu.
     * Returns textual representation of the continent.
     *
     * @return string z informacjami o kontynencie
     *         string with continent details
     */
    @Override
    public String toString() {
        return "Continent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryList=" + countryList +
                '}';
    }
}