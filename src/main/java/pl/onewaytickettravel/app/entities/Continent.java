package pl.onewaytickettravel.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "continents")
public class Continent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 20)
    @NotBlank(message = "Continent name must not be blank")
    @Size(max = 20, message = "Continent name can have a maximum of 20 characters")
    private String name;

    @OneToMany(mappedBy = "continent", fetch = FetchType.LAZY)
    private List<Country> countryList;

    public Continent(Long id, String name, List<Country> countryList) {
        this.id = id;
        this.name = name;
        this.countryList = countryList;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Continent continent = (Continent) o;
        return Objects.equals(id, continent.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Continent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryList=" + countryList +
                '}';
    }
}