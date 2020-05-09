package com.payday.email.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payday.email.entities.Customer;

public interface SendEmailRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCustomerCode(String customerCode);
}
