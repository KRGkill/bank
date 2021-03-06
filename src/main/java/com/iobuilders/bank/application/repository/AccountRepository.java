package com.iobuilders.bank.application.repository;

import com.iobuilders.bank.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> getAllByDeletedFalse();

    List<Account> findByUserId(Long userId);

    Optional<Account> findById(Long id);
}
