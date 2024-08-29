package com.hedrobyte.customer_manager.repository;

import com.hedrobyte.customer_manager.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByNameContainingIgnoreCaseAndCpfContaining(String name, String cpf);

    List<Customer> findByNameContainingIgnoreCase(String name);

    List<Customer> findByCpfContaining(String cpf);
}
