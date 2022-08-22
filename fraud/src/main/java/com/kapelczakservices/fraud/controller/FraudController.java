package com.kapelczakservices.fraud.controller;

import com.kapelczakservices.clients.fraud.FraudCheckResponse;
import com.kapelczakservices.fraud.service.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {
    private final FraudCheckService fraudCheckService;

    @GetMapping ("{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable Integer customerId) {
        log.info("Checking if customer is a fraudster...");
        boolean fraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(fraudulentCustomer);
    }
}
