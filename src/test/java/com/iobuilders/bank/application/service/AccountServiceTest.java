package com.iobuilders.bank.application.service;

import com.iobuilders.bank.application.repository.AccountRepository;
import com.iobuilders.bank.domain.Account;
import com.iobuilders.bank.domain.exception.EntityNotFoundException;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    void getAllAccount_WhenAccountExist_ThenGetAccounts() {
        // given
        Account account1 = createAccount(1L);
        Account account2 = createAccount(2L);

        // When
        when(accountRepository.getAllByDeletedFalse()).thenReturn(List.of(account1, account2));

        List<Account> resultList = accountService.getAllAccount();

        // Then
        assertThat(resultList).isEqualTo(List.of(account1, account2));

        verify(accountRepository).getAllByDeletedFalse();
    }

    @Test
    void getAccountById_WhenAccountExist_ThenGetAccount() throws EntityNotFoundException {
        // given
        Account account = createAccount(1L);

        // When
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));

        Account result = accountService.getAccountById(account.getId().intValue());

        // Then
        assertThat(result).isEqualTo(account);

        verify(accountRepository).findById(account.getId());
    }

    @Test
    void saveOrUpdate_WhenAccountValid_ThenSaveAccount() {
        // given
        Account account = createAccount(1L);

        // When
        when(accountRepository.save(account)).thenReturn(account);

        accountService.saveOrUpdate(account);

        // Then
        verify(accountRepository).save(account);
    }

    @Test
    void delete_WhenAccountValid_ThenSaveAccount() throws EntityNotFoundException {
        // given
        Account account = createAccount(1L);
        account.setDeleted(true);

        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));

        // When
        accountService.delete(account.getId().intValue());

        // Then
        verify(accountRepository).save(account);
    }

    @Test
    void findByUser_WhenUserExist_ThenGetAccounts() {
        // given
        Account account1 = createAccount(1L);
        Account account2 = createAccount(2L);

        // When
        when(accountRepository.findByUserId(1L)).thenReturn(List.of(account1, account2));

        List<Account> resultList = accountService.findByUser(1);

        // Then
        assertThat(resultList).isEqualTo(List.of(account1, account2));

        verify(accountRepository).findByUserId(1L);
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
