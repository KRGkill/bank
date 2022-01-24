package com.iobuilders.bank.infrastructure.controller;

import com.iobuilders.bank.application.service.AccountService;
import com.iobuilders.bank.domain.Account;
import com.iobuilders.bank.domain.exception.EntityNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@Tag(name = "Set of endpoints for Account Services")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    @Operation(summary = "Get all the accounts")
    public List<Account> getAllAccount() {
        return accountService.getAllAccount();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get the account")
    public Account getAccount(@PathVariable("id") int id) throws EntityNotFoundException {
        return accountService.getAccountById(id);
    }

    @PostMapping
    @Operation(summary = "Save the Account")
    public Long saveAccount(@RequestBody Account account) {
        accountService.saveOrUpdate(account);
        return account.getId();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the Account")
    public void deleteAccount(@PathVariable("id") int id) throws EntityNotFoundException {
        accountService.delete(id);
    }

    @GetMapping("/by/user/{id}")
    @Operation(summary = "Get the account by user")
    public List<Account> findByUser(@PathVariable("id") int id) {
        return accountService.findByUser(id);
    }
}
