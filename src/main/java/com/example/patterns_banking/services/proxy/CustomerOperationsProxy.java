package com.example.patterns_banking.services.proxy;

import com.example.patterns_banking.dtos.CustomerDTO;
import com.example.patterns_banking.models.Customer;
import com.example.patterns_banking.repositories.ICustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerOperationsProxy implements ICustomerOperations{
    private final ICustomerRepository customerRepository;

    public CustomerOperationsProxy(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(CustomerDTO customerDTO) {
        Customer customer = Customer
                .builder()
                .name(customerDTO.getName())
                .email(customerDTO.getEmail())
                .build();
        validateCustomerEmail(customer.getEmail());
        return customerRepository.save(customer);
    }

    @Override
    public void validateCustomerEmail(String email) {
        if (email != null && email.toLowerCase().endsWith("@yahoo.com")) {
            throw new IllegalArgumentException("Yahoo emails are not allowed.");
        }
    }
}
