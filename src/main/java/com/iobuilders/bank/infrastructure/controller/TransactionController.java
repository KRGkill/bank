package com.iobuilders.bank.infrastructure.controller;

import com.iobuilders.bank.application.service.TransactionService;
import com.iobuilders.bank.domain.Transaction;
import com.iobuilders.bank.domain.TransactionType;
import com.iobuilders.bank.domain.exception.EntityNotFoundException;
import com.iobuilders.bank.domain.exception.TransferNotValidException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@Tag(name = "Set of endpoints for Transaction Services")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    @Operation(summary = "Get all the transactions")
    public List<Transaction> getAllTransaction() {
        return transactionService.getAllTransaction();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get the transaction")
    public Transaction getTransaction(@PathVariable("id") int id) throws EntityNotFoundException {
        return transactionService.getTransactionById(id);
    }

    @PostMapping
    @Operation(summary = "Create the Transaction")
    public Long saveTransaction(@RequestParam Double amount,
                                @RequestParam int accountId,
                                @RequestParam TransactionType transactionType,
                                @RequestParam(required = false) Long withdrawalAccountFromId) throws EntityNotFoundException, TransferNotValidException {
        return transactionService.create(amount, accountId, transactionType, withdrawalAccountFromId);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the Transaction")
    public void deleteTransaction(@PathVariable("id") int id) {
        transactionService.delete(id);
    }

    @GetMapping("/by/account/{id}")
    @Operation(summary = "Get the transaction by account")
    public List<Transaction> findByAccountId(@PathVariable("id") int id) {
        return transactionService.findByAccountId(id);
    }
}
