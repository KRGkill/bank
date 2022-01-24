package com.iobuilders.bank.application.service;

import com.iobuilders.bank.application.repository.AccountRepository;
import com.iobuilders.bank.domain.Account;
import com.iobuilders.bank.domain.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccount() {
        return new ArrayList<>(accountRepository.getAllByDeletedFalse());
    }

    public Account getAccountById(int id) throws EntityNotFoundException {
        return accountRepository.findById((long)id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find Account with id: " + id));
    }

    public void saveOrUpdate(Account account) {
        accountRepository.save(account);
    }

    public void delete(int id) throws EntityNotFoundException {
        Account account = getAccountById(id);
        account.setDeleted(true);
        accountRepository.save(account);
    }

    public List<Account> findByUser(int userId) {
        return new ArrayList<>(accountRepository.findByUserId((long) userId));
    }
}
