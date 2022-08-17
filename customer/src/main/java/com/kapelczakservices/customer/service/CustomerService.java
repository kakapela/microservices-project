package com.kapelczakservices.customer.service;

import com.kapelczakservices.customer.dto.CustomerRegistrationRequest;
import com.kapelczakservices.customer.model.Customer;
import com.kapelczakservices.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        //todo: check if email valid
        //todo: check if email not taken
        customerRepository.save(customer);

    }
}
