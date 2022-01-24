package com.iobuilders.bank.application.service;

import com.iobuilders.bank.application.repository.TransactionRepository;
import com.iobuilders.bank.domain.Account;
import com.iobuilders.bank.domain.Transaction;
import com.iobuilders.bank.domain.TransactionType;
import com.iobuilders.bank.domain.exception.EntityNotFoundException;
import com.iobuilders.bank.domain.exception.TransferNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public List<Transaction> getAllTransaction() {
        return new ArrayList<>(transactionRepository.findAll());
    }

    public Transaction getTransactionById(int id) throws EntityNotFoundException {
        return transactionRepository.findById((long) id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find Transaction with id: " + id));
    }

    public Long create(Double amount, int accountId,
                       TransactionType transactionType,
                       Long withdrawalAccountFromId) throws EntityNotFoundException, TransferNotValidException {
        Account account = accountService.getAccountById(accountId);
        Transaction transaction = new Transaction();
        transaction.setType(transactionType);
        transaction.setAmount(amount);
        transaction.setAccount(account);

        if (transactionType.equals(TransactionType.TRANSFER)) {
            if (withdrawalAccountFromId == null) {
                throw new TransferNotValidException("Could not create the transfer");
            }
            transaction.setWithdrawalAccountFromId(withdrawalAccountFromId);

            Account accountFrom = accountService.getAccountById(withdrawalAccountFromId.intValue());

            account.setBalance(account.getBalance() + amount);
            accountFrom.setBalance(accountFrom.getBalance() - amount);
        } else if (transactionType.equals(TransactionType.DEPOSIT)) {
            account.setBalance(account.getBalance() + amount);
        } else if (transactionType.equals(TransactionType.WITHDRAWAL)) {
            account.setBalance(account.getBalance() - amount);
        }

        return transactionRepository.save(transaction).getId();
    }

    public void delete(int id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> findByAccountId(int userId) {
        return new ArrayList<>(transactionRepository.findByAccountId((long) userId));
    }
}
