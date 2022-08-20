package com.kapelczakservices.customer.controller;

import com.kapelczakservices.customer.dto.CustomerRegistrationRequest;
import com.kapelczakservices.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("api/customers")
public class CustomerController{

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest){
        log.info("New customer registration {}", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
        return ResponseEntity.ok("Created");
    }
}
