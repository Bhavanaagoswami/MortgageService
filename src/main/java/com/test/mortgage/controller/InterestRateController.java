package com.test.mortgage.controller;

import com.test.mortgage.model.MortgageRate;
import com.test.mortgage.service.InterestRateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * InterestRateController class for Rest api to get interest Rates.
 */
@RestController
@RequestMapping("/api")
public class InterestRateController {
   private final InterestRateService interestRateService;

    public InterestRateController(InterestRateService interestRateService) {
        this.interestRateService = interestRateService;
    }

    /**
     * Get api request for interest rate.
     * @return list
     */
    @GetMapping("/interest-rate")
    public List<MortgageRate> getRates() {
        return interestRateService.findAll();
    }
}
