package pl.onewaytickettravel.app.model;

import java.time.LocalDate;

public class SearchFilter {

    private String destination;
    private LocalDate startDate;
    private Integer numberOfAdults;
    private String departureCity;
    private String continentName;
    private String countryName;
    private String cityName;



    public SearchFilter() {
    }

    public SearchFilter(String destination, LocalDate startDate, Integer numberOfAdults, String departureCity, String continentName, String countryName, String cityName) {
        this.destination = destination;
        this.startDate = startDate;
        this.numberOfAdults = numberOfAdults;
        this.departureCity = departureCity;
        this.continentName = continentName;
        this.countryName = countryName;
        this.cityName = cityName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getNumberOfAdults() {
        // Domyślna wartość w formularzu to 1
        return (numberOfAdults != null && numberOfAdults > 0) ? numberOfAdults : 1;
    }

    public void setNumberOfAdults(Integer numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
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

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }
}