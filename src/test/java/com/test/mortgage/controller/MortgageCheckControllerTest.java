package com.test.mortgage.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.mortgage.model.MortgageCheckRequest;
import com.test.mortgage.model.MortgageCheckResponse;
import com.test.mortgage.service.MortgageCheckService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@WebMvcTest(MortgageCheckController.class)
class MortgageCheckControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private MortgageCheckService mortgageCheckService;
    MortgageCheckRequest request = null;
    MortgageCheckResponse response = null;

    @BeforeEach
    void setUp() {
        request = new MortgageCheckRequest(BigDecimal.valueOf(1000.1),10,BigDecimal.valueOf(500.1),
                BigDecimal.valueOf(500));
        response = new MortgageCheckResponse(true, BigDecimal.valueOf(1.33));
    }

    @Test
    public void mortgageCheckTest() throws Exception {
        when(mortgageCheckService.checkMortgageRate(request)).thenReturn(CompletableFuture.completedFuture(response));
        // Send request as body to /api/mortgage-check
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/mortgage-check")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .accept(String.valueOf(MediaType.APPLICATION_JSON))
                .content(objectMapper.writeValueAsBytes(request));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void mortgageCheckTestIfRequestIsNull() throws Exception {
        request.setLoanValue(null);
        when(mortgageCheckService.checkMortgageRate(request)).thenReturn(CompletableFuture.completedFuture(response));
        // Send request as body to /api/mortgage-check
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/mortgage-check")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .accept(String.valueOf(MediaType.APPLICATION_JSON))
                .content(objectMapper.writeValueAsBytes(request));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }
}