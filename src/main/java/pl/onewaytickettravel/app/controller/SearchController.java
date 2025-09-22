package pl.onewaytickettravel.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.onewaytickettravel.app.dto.OfferDto;
import pl.onewaytickettravel.app.model.SearchFilter;
import pl.onewaytickettravel.app.service.OfferService;

import java.util.List;

@Controller
public class SearchController {

    private final OfferService offerService;

    public SearchController(OfferService offerService) {
        this.offerService = offerService;
    }

    /**
     * Wyświetlenie strony głównej z formularzem wyszukiwania.
     */

    /**
     * Displays the homepage with the search form.
     */

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("searchFilter", new SearchFilter());
        return "index";
    }

    /**
     * Obsługa wysłanego formularza wyszukiwania.
     */

    /**
     * Handles the submitted search form.
     */

    @PostMapping("/search")
    public String handleSearch(@ModelAttribute SearchFilter searchFilter, Model model) {
        System.out.println("🔍 Miasto z formularza: " + searchFilter.getCityName());
        List<OfferDto> results = offerService.searchOffers(searchFilter);
        model.addAttribute("results", results);
        model.addAttribute("filterUsed", searchFilter);
        return "results";
    }
}