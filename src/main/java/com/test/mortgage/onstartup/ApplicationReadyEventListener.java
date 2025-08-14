package com.test.mortgage.onstartup;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Component to run at application start to check the cache.
 */
@Slf4j
@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    Logger logger = LoggerFactory.getLogger(ApplicationReadyEventListener.class);
    private final ApplicationStartupCheckService checker;

    public ApplicationReadyEventListener(ApplicationStartupCheckService checker) {
        this.checker = checker;
    }

    /**
     * OnApplicationEvent method implementation to validate cache.
     * @param applicationReadyEvent applicationReadyEvent
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        checker.validateCache();
        logger.info("----------------------------- Application has been started -----------------------------");
    }
}