package com.test.mortgage.service;

import com.test.mortgage.exception.MortgageRateNotFound;
import com.test.mortgage.exception.NotFeasible;
import com.test.mortgage.model.MortgageCheckRequest;
import com.test.mortgage.model.MortgageCheckResponse;
import com.test.mortgage.model.MortgageRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.CompletableFuture;

/**
 * Service class to calculate the Mortgage rate check.
 *
 */
@Service
@Slf4j
public class MortgageCheckService {
    private final InterestRateService interestRateService;
    public static final Integer SCALE_TEN = 10;
    public static final Integer SCALE_TWO = 2;
    public static final Integer TOTAL_MONTH = 10;
    public static final Long PERCENT = 100L;


    public MortgageCheckService(InterestRateService interestRateService) {
        this.interestRateService = interestRateService;
    }

    /**
     * Async method to handle number of requests.This method calculate mortgage rate.
     *
     * @param request MortgageCheckRequest
     * @return CompletableFuture<MortgageCheckResponse>
     */
    @Async
    public CompletableFuture<MortgageCheckResponse> checkMortgageRate(MortgageCheckRequest request) {
        boolean feasible = (request.getLoanValue().compareTo(request.getIncome().multiply(BigDecimal.valueOf(4))) <= 0)
                && (request.getLoanValue().compareTo(request.getHomeValue()) <= 0);
        BigDecimal monthlyCost;
        if (feasible) {
            MortgageRate mortgageRate = interestRateService
                    .findInterestRateByMaturityPeriod(request.getMaturityPeriod());
            if (mortgageRate != null && mortgageRate.getInterestRate() != null) {
                BigDecimal monthlyRate = mortgageRate.getInterestRate()
                        .divide(BigDecimal.valueOf(TOTAL_MONTH * PERCENT), SCALE_TEN,
                        RoundingMode.HALF_UP);
                int months = request.getMaturityPeriod() * TOTAL_MONTH;
                BigDecimal numerator = monthlyRate.multiply((BigDecimal.ONE.add(monthlyRate)).pow(months));
                BigDecimal denominator = (BigDecimal.ONE.add(monthlyRate)).pow(months).subtract(BigDecimal.ONE);
                monthlyCost = request.getLoanValue().multiply(numerator.divide(denominator,
                                SCALE_TEN, RoundingMode.HALF_UP))
                        .setScale(Math.toIntExact(SCALE_TWO), RoundingMode.HALF_UP);
            } else {
                log.info("MortgageRate for given maturity period not found in database.");
                throw new MortgageRateNotFound("MortgageRate for given maturity period not found in database.");
            }
        } else {
            log.info("Mortgage Rate not feasible, Either income is not 4 time then load value \" +\n" +
                    "                    \"or home value is greater then load value.");
            throw new NotFeasible("Mortgage Rate not feasible, Either income is not 4 time then load value " +
                    "or home value is greater then load value.");
        }

        return CompletableFuture.completedFuture(new MortgageCheckResponse(feasible, monthlyCost));
    }
}
