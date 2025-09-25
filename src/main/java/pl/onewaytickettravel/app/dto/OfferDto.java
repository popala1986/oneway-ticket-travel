package pl.onewaytickettravel.app.dto;


import java.math.BigDecimal;


/**
 * Obiekt DTO reprezentujący ofertę turystyczną.
 * Używany do przesyłania danych między warstwami aplikacji bez ujawniania encji.
 *
 * Data Transfer Object representing a travel offer.
 * Used to transfer data between application layers without exposing the entity.
 */
public class OfferDto {


    /**
     * Identyfikator oferty.
     * Offer identifier.
     */
    private Long id;

    /**
     * Nazwa oferty.
     * Offer name.
     */
    private String name;

    /**
     * Cena oferty.
     * Offer price.
     */
    private BigDecimal price;

    /**
     * Nazwa kontynentu, do którego należy oferta.
     * Name of the continent associated with the offer.
     */
    private String continentName;

    /**
     * Nazwa kraju, do którego należy oferta.
     * Name of the country associated with the offer.
     */
    private String countryName;

    /**
     * Nazwa miasta docelowego oferty.
     * Name of the destination city for the offer.
     */
    private String cityName;

    /**
     * Konstruktor domyślny.
     * Default constructor.
     */
    private String status;


    /**
     * Konstruktor inicjalizujący wszystkie pola DTO.
     *
     * Constructor initializing all DTO fields.
     *
     * @param id identyfikator oferty
     *           offer ID
     * @param name nazwa oferty
     *             offer name
     * @param price cena oferty
     *              offer price
     * @param continentName nazwa kontynentu
     *                      continent name
     * @param countryName nazwa kraju
     *                    country name
     * @param cityName nazwa miasta
     *                 city name
     * @param status status oferty
     *               offer status
     */
    public OfferDto() {
    }

    public OfferDto(Long id, String name, BigDecimal price, String continentName, String countryName, String cityName, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.continentName = continentName;
        this.countryName = countryName;
        this.cityName = cityName;
        this.status = status;
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

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}