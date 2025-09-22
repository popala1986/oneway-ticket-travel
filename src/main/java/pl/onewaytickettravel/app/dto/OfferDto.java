package pl.onewaytickettravel.app.dto;


import java.math.BigDecimal;

public class OfferDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private String continentName;
    private String countryName;
    private String cityName;


    public OfferDto() {
    }

    public OfferDto(Long id, String name, BigDecimal price, String continentName, String countryName, String cityName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.continentName = continentName;
        this.countryName = countryName;
        this.cityName = cityName;
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
}