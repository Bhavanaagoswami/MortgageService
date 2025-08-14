package com.test.mortgage.onstartup;

import static org.mockito.Mockito.when;

import com.test.mortgage.model.InterestRateEntity;
import com.test.mortgage.service.InterestRateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@ExtendWith({MockitoExtension.class, OutputCaptureExtension.class})
class ApplicationStartupCheckServiceTest {

    ApplicationStartupCheckService applicationStartupCheckService;
    InterestRateEntity interestRateEntity;
    @Mock
    InterestRateService interestRateService;
    @Mock
    CacheManager cacheManager;
    @Mock
    Cache mockCache;


    @BeforeEach
    void setUp() {
        cacheManager = Mockito.mock(CacheManager.class);
        interestRateEntity = new InterestRateEntity();
        interestRateEntity.setId(1L);
        interestRateEntity.setInterestRate(BigDecimal.valueOf(10.1));
        interestRateEntity.setMaturityPeriod(2);
        interestRateEntity.setLastUpdated(Timestamp.from(Instant.now()));
        applicationStartupCheckService = new ApplicationStartupCheckService(interestRateService);
    }

    @Test
    void validateCache() {

        when(interestRateService.isCached()).thenReturn(mockCache);
        applicationStartupCheckService.validateCache();

        // test that there was a call
        Mockito.verify(interestRateService, Mockito.times(1)).isCached();
    }
}