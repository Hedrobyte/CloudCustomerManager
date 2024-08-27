package com.hedrobyte.customer_manager.repository;

import com.hedrobyte.customer_manager.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
