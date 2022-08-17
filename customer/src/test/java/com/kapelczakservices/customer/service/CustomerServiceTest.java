package com.kapelczakservices.customer.service;

import com.kapelczakservices.customer.dto.CustomerRegistrationRequest;
import com.kapelczakservices.customer.model.Customer;
import com.kapelczakservices.customer.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;


    //todo - try to implement this with ArgumentCaptor
    @DisplayName("Unit test for register customer")
    @Test
    void shouldRegisterCustomer() {
        //given
        String email = "kacperkapela1@wp.pl";
        String lastName = "Kapela";
        String firstName = "Kacper";
        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest(firstName, lastName, email);

        Customer customer = new Customer(null, firstName, lastName, email);
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        //when
        customerService.registerCustomer(customerRegistrationRequest);
        //then
        assertThat(customer).isNotNull();
        assertThat(customer.getEmail()).isEqualTo(customerRegistrationRequest.email());
        assertThat(customer.getFirstName()).isEqualTo(customerRegistrationRequest.firstName());
        assertThat(customer.getLastName()).isEqualTo(customerRegistrationRequest.lastName());
    }
}