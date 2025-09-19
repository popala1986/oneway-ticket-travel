package pl.onewaytickettravel.app.model;

import java.time.LocalDate;

public class SearchFilter {

    private String destination;
    private LocalDate startDate;
    private Integer numberOfAdults;
    private String departureCity;
    private String countryName;



    public SearchFilter() {
    }

    public SearchFilter(String destination, String countryName, String departureCity, Integer numberOfAdults, LocalDate startDate) {
        this.destination = destination;
        this.countryName = countryName;
        this.departureCity = departureCity;
        this.numberOfAdults = numberOfAdults;
        this.startDate = startDate;
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


}