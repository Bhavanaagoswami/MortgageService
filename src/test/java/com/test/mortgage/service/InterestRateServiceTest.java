package com.test.mortgage.service;

import com.test.mortgage.mapper.InterestRateMapper;
import com.test.mortgage.model.MortgageRate;
import com.test.mortgage.repository.InterestRateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.List;

@SpringBootTest
class InterestRateServiceTest {
    private InterestRateService interestRateService;
    @Autowired
    private InterestRateRepository interestRateRepository;
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private InterestRateMapper interestRateMapper;


    @BeforeEach
    void setUp() {
        interestRateService = new InterestRateService(interestRateRepository, cacheManager, interestRateMapper);
    }
    @Test
    void findInterestRateByMaturityPeriod() {
        MortgageRate rate = interestRateService.findInterestRateByMaturityPeriod(1);
        Assertions.assertNotNull(rate);
        Assertions.assertEquals("10.00", rate.getInterestRate().toString());
        Assertions.assertEquals(1, rate.getMaturityPeriod());
        Assertions.assertNotNull(rate.getLastUpdated());
    }

    @Test
    void findAll() {
        List<MortgageRate> rateList = interestRateService.findAll();
        Assertions.assertEquals(8, rateList.size());
    }

    @Test
    void isCached() {
       Cache cache = interestRateService.isCached();
       Assertions.assertNotNull(cache);
    }
}