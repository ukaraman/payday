package com.account.summary.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.account.summary.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByCode(String code);
    Optional<Account> findByCustomerCode(int code);

}
