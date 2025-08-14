package com.test.mortgage.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.test.mortgage.mapper.InterestRateMapper;
import com.test.mortgage.model.InterestRateEntity;
import com.test.mortgage.model.MortgageCheckRequest;
import com.test.mortgage.model.MortgageCheckResponse;
import com.test.mortgage.model.MortgageRate;
import com.test.mortgage.repository.InterestRateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.cache.CacheManager;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MortgageCheckServiceTest {

    private MortgageCheckService mortgageCheckService;
    private InterestRateService interestRateService;
    private InterestRateRepository interestRateRepository;
    private InterestRateMapper interestRateMapper;
    MortgageCheckRequest request = null;
    MortgageRate mortgageRate = null;
    InterestRateEntity entityRequest = null;
    @BeforeEach
    void setUp() {
        CacheManager cacheManager = Mockito.mock(CacheManager.class);
        interestRateMapper = Mockito.mock(InterestRateMapper.class);
        interestRateRepository = Mockito.mock(InterestRateRepository.class);
        interestRateService = new InterestRateService(interestRateRepository, cacheManager, interestRateMapper);
        mortgageCheckService = new MortgageCheckService(interestRateService);
        request = new MortgageCheckRequest(BigDecimal.valueOf(10000),10,
                BigDecimal.valueOf(100.0),BigDecimal.valueOf(100.0));
        mortgageRate =
                new MortgageRate(BigDecimal.valueOf(10.1), 10, Timestamp.from(Instant.now()));
        entityRequest = new InterestRateEntity();
        entityRequest.setId(1L);
        entityRequest.setInterestRate(BigDecimal.valueOf(10.1));
        entityRequest.setMaturityPeriod(2);
        entityRequest.setLastUpdated(Timestamp.from(Instant.now()));

    }
    @Test
    public void testMortgageCheck() throws ExecutionException, InterruptedException {
        when(interestRateRepository.findByMaturityPeriod(request.getMaturityPeriod())).
                thenReturn(Optional.of(entityRequest));
        when(interestRateMapper.toInterestRate(any())).thenReturn(mortgageRate);
        when(interestRateService
                .findInterestRateByMaturityPeriod(request.getMaturityPeriod()))
                .thenReturn(mortgageRate);

        CompletableFuture<MortgageCheckResponse> future = mortgageCheckService.checkMortgageRate(request);
        MortgageCheckResponse response = future.get();
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isMortgage());
        Assertions.assertEquals(BigDecimal.valueOf(1.59), response.getMortgageRate());

    }

    @Test
    public void testMortgageCheckIfNoInterestRateFound() throws ExecutionException, InterruptedException {
        when(interestRateRepository.findByMaturityPeriod(request.getMaturityPeriod())).
                thenReturn(Optional.empty());
        when(interestRateService
                .findInterestRateByMaturityPeriod(request.getMaturityPeriod()))
                .thenReturn(any());

        CompletableFuture<MortgageCheckResponse> future = mortgageCheckService.checkMortgageRate(request);
        MortgageCheckResponse response = future.get();
        Assertions.assertNotNull(response);
    }
}
