package pl.onewaytickettravel.app.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Model reprezentujący kryteria wyszukiwania ofert turystycznych.
 * Umożliwia filtrowanie po lokalizacji oraz zakresie cenowym.
 *
 * Model representing search criteria for travel offers.
 * Allows filtering by location and price range.
 */
public class SearchFilter {

    /**
     * Nazwa kontynentu użyta w filtrze.
     * Name of the continent used in the filter.
     */
    private String continentName;

    /**
     * Nazwa kraju użyta w filtrze.
     * Name of the country used in the filter.
     */
    private String countryName;

    /**
     * Nazwa miasta użyta w filtrze.
     * Name of the city used in the filter.
     */
    private String cityName;

    /**
     * Minimalna cena oferty.
     * Minimum offer price.
     */
    private BigDecimal minPrice;

    /**
     * Maksymalna cena oferty.
     * Maximum offer price.
     */
    private BigDecimal maxPrice;


    /**
     * Konstruktor domyślny.
     * Default constructor.
     */
    public SearchFilter() {
    }

    /**
     * Konstruktor inicjalizujący wszystkie pola filtra.
     *
     * Constructor initializing all filter fields.
     *
     * @param continentName nazwa kontynentu
     *                      continent name
     * @param countryName nazwa kraju
     *                    country name
     * @param cityName nazwa miasta
     *                 city name
     * @param minPrice minimalna cena
     *                 minimum price
     * @param maxPrice maksymalna cena
     *                 maximum price
     */
    public SearchFilter(String continentName, String countryName, String cityName, BigDecimal minPrice, BigDecimal maxPrice) {
        this.continentName = continentName;
        this.countryName = countryName;
        this.cityName = cityName;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
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

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }
}