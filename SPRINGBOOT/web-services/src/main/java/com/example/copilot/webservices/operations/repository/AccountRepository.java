package com.example.copilot.webservices.operations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.copilot.webservices.operations.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // Placeholder for custom query methods

}
