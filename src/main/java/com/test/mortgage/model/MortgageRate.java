package com.test.mortgage.model;

import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * MortgageRate class to map the entity class.
 */
@Data
public class MortgageRate {
    private Long id;
    private BigDecimal interestRate;
    private Integer maturityPeriod;
    private Timestamp lastUpdated;

    public MortgageRate() {
    }
    public MortgageRate(Long id, BigDecimal interestRate, Integer maturityPeriod, Timestamp lastUpdated) {
        this.id = id;
        this.interestRate = interestRate;
        this.maturityPeriod = maturityPeriod;
        this.lastUpdated = lastUpdated;
    }

    public String toString() {
        return "InterestRate(id=" + this.getId() + ", interestRate=" + this.getInterestRate() + ", maturityPeriod=" + this.getMaturityPeriod() + ", lastUpdated=" + this.getLastUpdated() + ")";
    }
}
