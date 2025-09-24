package pl.onewaytickettravel.app.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SearchFilter {

    private String continentName;
    private String countryName;
    private String cityName;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;



    public SearchFilter() {
    }

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