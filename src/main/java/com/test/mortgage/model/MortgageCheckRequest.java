package com.test.mortgage.model;

import lombok.Data;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;

/**
 * Request object from controller.
 */
@Data
public class MortgageCheckRequest {

    @NotNull
    private BigDecimal income;
    @NotNull
    private Integer maturityPeriod;
    @NotNull
    private BigDecimal homeValue;
    @NotNull
    private BigDecimal loanValue;

    public MortgageCheckRequest(BigDecimal income, Integer maturityPeriod, BigDecimal homeValue, BigDecimal loanValue) {
        this.income = income;
        this.maturityPeriod = maturityPeriod;
        this.homeValue = homeValue;
        this.loanValue = loanValue;
    }


    public String toString() {
        return "MortgageCheckRequest(income=" + this.getIncome() + ", maturityPeriod=" + this.getMaturityPeriod() + ", homeValue=" + this.getHomeValue() + ", loanValue=" + this.getLoanValue() + ")";
    }
}
