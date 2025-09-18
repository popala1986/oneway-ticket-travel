package pl.onewaytickettravel.app.dto;


import java.math.BigDecimal;

public class OfferDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private String userName;
    private Long continentId;
    private String continentName;

    public OfferDto() {
    }

    public OfferDto(Long id, String name, BigDecimal price, String userName,
                    Long continentId, String continentName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.userName = userName;
        this.continentId = continentId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getContinentId() {
        return continentId;
    }

    public void setContinentId(Long continentId) {
        this.continentId = continentId;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }
}