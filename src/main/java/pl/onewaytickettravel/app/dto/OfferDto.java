package pl.onewaytickettravel.app.dto;


import java.math.BigDecimal;

public class OfferDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private String continentName;

    public OfferDto() {
    }

    public OfferDto(String name, Long id, BigDecimal price, String continentName) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.continentName = continentName;
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
}