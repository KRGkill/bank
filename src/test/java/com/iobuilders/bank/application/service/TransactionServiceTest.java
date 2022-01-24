package com.iobuilders.bank.application.service;

import com.iobuilders.bank.application.repository.TransactionRepository;
import com.iobuilders.bank.domain.Account;
import com.iobuilders.bank.domain.Transaction;
import com.iobuilders.bank.domain.TransactionType;
import com.iobuilders.bank.domain.exception.EntityNotFoundException;
import com.iobuilders.bank.domain.exception.TransferNotValidException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepository;
    @MockBean
    private AccountService accountService;

    @Test
    void getAllTransaction_WhenTransactionExist_ThenGetTransactions() {
        // given
        Transaction transaction1 = createTransaction(1L);
        Transaction transaction2 = createTransaction(2L);

        // When
        when(transactionRepository.findAll()).thenReturn(List.of(transaction1, transaction2));

        List<Transaction> resultList = transactionService.getAllTransaction();

        // Then
        assertThat(resultList).isEqualTo(List.of(transaction1, transaction2));

        verify(transactionRepository).findAll();
    }

    @Test
    void getTransactionById_WhenTransactionExist_ThenGetTransaction() throws EntityNotFoundException {
        // given
        Transaction transaction = createTransaction(1L);

        // When
        when(transactionRepository.findById(transaction.getId())).thenReturn(Optional.of(transaction));

        Transaction result = transactionService.getTransactionById(transaction.getId().intValue());

        // Then
        assertThat(result).isEqualTo(transaction);

        verify(transactionRepository).findById(transaction.getId());
    }

    @Test
    void create_WhenTransactionValid_ThenSaveTransaction() throws EntityNotFoundException, TransferNotValidException {
        // given
        Transaction transaction = createTransaction(1L);
        Account account1 = createAccount(1L);
        Account account2 = createAccount(2L);

        // When
        given(accountService.getAccountById(1)).willReturn(account1);
        given(accountService.getAccountById(2)).willReturn(account2);
        when(transactionRepository.save(any())).thenReturn(transaction);

        Long result = transactionService.create(transaction.getAmount(), 1, transaction.getType(), 2L);

        // Then
        assertThat(result).isEqualTo(transaction.getId());

        verify(transactionRepository).save(any());
    }

    @Test
    void delete_WhenTransactionValid_ThenSaveTransaction() throws EntityNotFoundException {
        // given
        Transaction transaction = createTransaction(1L);

        // When
        transactionService.delete(transaction.getId().intValue());

        // Then
        verify(transactionRepository).deleteById(transaction.getId().intValue());
    }

    @Test
    void findByAccountId_WhenUserExist_ThenGetTransactions() {
        // given
        Transaction transaction1 = createTransaction(1L);
        Transaction transaction2 = createTransaction(2L);

        // When
        when(transactionRepository.findByAccountId(1L)).thenReturn(List.of(transaction1, transaction2));

        List<Transaction> resultList = transactionService.findByAccountId(1);

        // Then
        assertThat(resultList).isEqualTo(List.of(transaction1, transaction2));

        verify(transactionRepository).findByAccountId(1L);
    }

    private Transaction createTransaction(Long id) {
        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setAmount(10.0);
        transaction.setAccount(new Account());
        transaction.setType(TransactionType.DEPOSIT);

        return transaction;
    }

    private Account createAccount(Long id) {
        Account account = new Account();
        account.setId(id);
        account.setDeleted(false);
        account.setBalance(10.0);
        account.setNumber("number" + id);

        return account;
    }
}
