package com.test.mortgage.onstartup;

import com.test.mortgage.exception.ApplicationPreCheckFailedException;
import com.test.mortgage.service.InterestRateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Component to check the cache.
 */
@Component
@Slf4j
public class ApplicationStartupCheckService {
    private final InterestRateService interestRateService;

    public ApplicationStartupCheckService(InterestRateService interestRateService) {
        this.interestRateService = interestRateService;
    }

    /**
     * validate cache method.
     */
    public void validateCache() {
        if (interestRateService.isCached() == null) {
            throw new ApplicationPreCheckFailedException("Cache not initialized");
        }
    }
}
