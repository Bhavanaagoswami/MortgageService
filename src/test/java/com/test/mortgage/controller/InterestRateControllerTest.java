package com.test.mortgage.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.test.mortgage.model.MortgageRate;
import com.test.mortgage.service.InterestRateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(InterestRateController.class)
class InterestRateControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private InterestRateService interestRateService;
    MortgageRate request = null;

    @BeforeEach
    void setUp() {
        request = new MortgageRate(1L, BigDecimal.valueOf(10.1), 10,
                        Timestamp.valueOf("2025-08-14 06:59:17"));
    }

    @Test
    public void getRatesTest() throws Exception {
        List<MortgageRate> interestRates = new ArrayList<>();
        interestRates.add(request);
        when(interestRateService.findAll()).thenReturn(interestRates);
        mockMvc.perform(get("/api/interest-rate"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"interestRate\":10.1,\"maturityPeriod\":10,\"lastUpdated\":\"2025-08-14T01:29:17.000+00:00\"}]"));
    }

    @Test
    public void getRatesTestIfServerFailed() throws Exception {
        mockMvc.perform(get("/api/interest-rat"))
                .andExpect(status().is5xxServerError());
    }



}