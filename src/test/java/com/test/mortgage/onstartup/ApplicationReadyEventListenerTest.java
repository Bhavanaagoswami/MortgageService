package com.test.mortgage.onstartup;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@ExtendWith({MockitoExtension.class, OutputCaptureExtension.class})
class ApplicationReadyEventListenerTest {

    @MockitoBean
    private ApplicationReadyEventListener eventListener;
    @Mock
    private ApplicationReadyEvent applicationReadyEvent;
    @Mock
    private ApplicationStartupCheckService applicationStartupCheckService;
    @Captor
    private ArgumentCaptor<String> captor;

    @BeforeEach
    void setUp() {
        eventListener = new ApplicationReadyEventListener(applicationStartupCheckService);
    }

    @Test
    void onApplicationEvent(CapturedOutput capturedOutput) {
        eventListener.onApplicationEvent(applicationReadyEvent);
        Mockito.verify(applicationStartupCheckService, Mockito.times(1)).validateCache();
        assertThat(capturedOutput).contains("Application has been started");

    }
}