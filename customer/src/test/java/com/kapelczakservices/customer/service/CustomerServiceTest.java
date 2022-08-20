package com.kapelczakservices.customer.service;

import com.kapelczakservices.customer.dto.CustomerRegistrationRequest;
import com.kapelczakservices.customer.exception.EmailAlreadyTakenException;
import com.kapelczakservices.customer.model.Customer;
import com.kapelczakservices.customer.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;

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

    //todo -  watch video about unit testing and perform a better testing maybe?
    @Test
    void shouldThrowEmailAlreadyTakenException() {
        //given
        String email = "kacperkapela1@wp.pl";
        String lastName = "Kapela";
        String firstName = "Kacper";
        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest(firstName, lastName, email);

        Customer customer = new Customer(null, firstName, lastName, email);
        BDDMockito.when(customerRepository.findByEmail(email)).thenReturn(Optional.of(customer));
        //when
        //then
        Assertions.assertThatThrownBy(() -> customerService.registerCustomer(customerRegistrationRequest))
                .isInstanceOf(EmailAlreadyTakenException.class)
                .hasMessageContaining(format("Customer with email %s already exists!", email));

        BDDMockito.then(customerRepository).should(never()).save(any());
    }
}