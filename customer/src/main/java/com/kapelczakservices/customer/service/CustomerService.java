package com.kapelczakservices.customer.service;

import com.kapelczakservices.customer.dto.CustomerRegistrationRequest;
import com.kapelczakservices.customer.dto.FraudCheckResponse;
import com.kapelczakservices.customer.exception.CustomerIsAFraudsterException;
import com.kapelczakservices.customer.exception.EmailAlreadyTakenException;
import com.kapelczakservices.customer.model.Customer;
import com.kapelczakservices.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        //todo: check if email valid
        //todo: check if email not taken
        Optional<Customer> customerByEmail = customerRepository.findByEmail(customerRegistrationRequest.email());
        if (customerByEmail.isPresent())
            throw new EmailAlreadyTakenException(format("Customer with email %s already exists!", customerRegistrationRequest.email()));

        customerRepository.saveAndFlush(customer);
        // todo check if fraudster
        FraudCheckResponse response = restTemplate.getForObject(
                "http://localhost:8081/api/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );
        if(response.isFraudster()) {
            throw new CustomerIsAFraudsterException(String.format("Customer with id %s is a Fraudster!", customer.getId()));
        }
        // todo send notification

    }
}
