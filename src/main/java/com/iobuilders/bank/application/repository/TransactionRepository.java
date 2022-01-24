package com.iobuilders.bank.application.repository;

import com.iobuilders.bank.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByAccountId(Long accountId);

    Optional<Transaction> findById(Long id);
}
