package com.payday.history.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payday.history.entities.History;

@Repository
public interface AccountHistoryRepository extends JpaRepository<History, Long> {

    Optional<History> findByCustomerCode(String customerCode);
}
