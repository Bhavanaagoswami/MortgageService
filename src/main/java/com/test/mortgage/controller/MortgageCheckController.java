package com.test.mortgage.controller;

import com.test.mortgage.model.MortgageCheckRequest;
import com.test.mortgage.model.MortgageCheckResponse;
import com.test.mortgage.service.MortgageCheckService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.CompletableFuture;

/**
 * Mortgage check api controller.
 */
@RestController
@RequestMapping("/api")
public class MortgageCheckController {

    private final MortgageCheckService mortgageCheckService;

    public MortgageCheckController(MortgageCheckService mortgageCheckService) {
        this.mortgageCheckService = mortgageCheckService;
    }

    /**
     * Post API method to calculate mortgage validity.
     *
     * @param request MortgageCheckRequest
     * @return MortgageCheckResponse
     */
    @PostMapping("/mortgage-check")
    public CompletableFuture<ResponseEntity<MortgageCheckResponse>> check(@Valid @RequestBody MortgageCheckRequest request) {
        return mortgageCheckService.checkMortgageRate(request).thenApply(ResponseEntity::ok);
    }
}
