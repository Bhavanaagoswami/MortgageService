package com.test.mortgage.service;

import com.test.mortgage.mapper.InterestRateMapper;
import com.test.mortgage.model.MortgageRate;
import com.test.mortgage.repository.InterestRateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

class MortgageRateServiceTest {

    @MockitoBean
    private InterestRateService interestRateService;
    @Mock
    private InterestRateRepository interestRateRepository;
    @Mock
    private CacheManager cacheManager;
    @Mock
    private InterestRateMapper interestRateMapper;


    @BeforeEach
    void setUp() {
        interestRateRepository = Mockito.mock(InterestRateRepository.class);
        interestRateService = new InterestRateService(interestRateRepository, cacheManager, interestRateMapper);
    }
    @Test
    void findInterestRateByMaturityPeriod() {
        MortgageRate rate = interestRateService.findInterestRateByMaturityPeriod(1);
        Assertions.assertNotNull(rate);

    }

    @Test
    void findAll() {
    }

    @Test
    void isCached() {
    }
}