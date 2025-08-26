package com.test.mortgage.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.test.mortgage.model.MortgageRate;
import com.test.mortgage.service.InterestRateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(InterestRateController.class)
class InterestRateControllerTest {

    @Autowired
    public MockMvc mockMvc;
    @MockitoBean
    public InterestRateService interestRateService;
    MortgageRate request = null;

    @BeforeEach
    void setUp() {
        request = new MortgageRate(1L, BigDecimal.valueOf(10.1), 10,
                        Timestamp.from(Instant.now()));
    }

    @Test
    public void getRatesTest() throws Exception {
        List<MortgageRate> interestRates = new ArrayList<>();
        interestRates.add(request);
        when(interestRateService.findAll()).thenReturn(interestRates);
       MvcResult result  = mockMvc.perform(get("/api/interest-rate"))
                .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
                .andReturn();
        Assertions.assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    public void getRatesTestIfServerFailed() throws Exception {
        mockMvc.perform(get("/api/interest-rat"))
                .andExpect(status().is5xxServerError());
    }



}