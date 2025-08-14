package com.test.mortgage.model;

import java.math.BigDecimal;
import lombok.Data;

/**
 * Response object for controller.
 */
@Data
public class MortgageCheckResponse {
    private boolean isMortgage;
    private BigDecimal mortgageRate;

    public MortgageCheckResponse(boolean feasible, BigDecimal monthlyCost) {
        this.isMortgage = feasible;
        this.mortgageRate = monthlyCost;
    }
}
