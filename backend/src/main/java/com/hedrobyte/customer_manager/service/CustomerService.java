package com.hedrobyte.customer_manager.service;

import com.hedrobyte.customer_manager.dto.CustomerDTO;
import com.hedrobyte.customer_manager.model.Customer;
import com.hedrobyte.customer_manager.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + id));
        return convertToDto(customer);
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        customer = customerRepository.save(customer);
        return convertToDto(customer);
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + id));

        // Update the existing customer fields with the data from the DTO
        existingCustomer.setName(customerDTO.name());
        existingCustomer.setCpf(customerDTO.cpf());
        existingCustomer.setDateOfBirth(customerDTO.dateOfBirth());
        existingCustomer.setEmail(customerDTO.email());

        existingCustomer = customerRepository.save(existingCustomer);
        return convertToDto(existingCustomer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + id));
        customerRepository.delete(customer);
    }

    // Helper methods to convert between Customer and CustomerDTO

    private CustomerDTO convertToDto(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getCpf(),
                customer.getDateOfBirth(),
                customer.getEmail()
        );
    }

    private Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.name());
        customer.setCpf(customerDTO.cpf());
        customer.setDateOfBirth(customerDTO.dateOfBirth());
        customer.setEmail(customerDTO.email());
        return customer;
    }
}