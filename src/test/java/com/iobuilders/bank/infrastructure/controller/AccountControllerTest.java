package com.iobuilders.bank.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iobuilders.bank.application.service.AccountService;
import com.iobuilders.bank.domain.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAll_WhenExist_ThenReturnAccounts() throws Exception {
        Account account1 = createAccount(1L);
        Account account2 = createAccount(2L);

        given(accountService.getAllAccount()).willReturn(List.of(account1, account2));

        mockMvc.perform(get("/account"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"number\":\"number1\",\"balance\":10.0,\"deleted\":false,\"creationDate\":null},{\"id\":2,\"number\":\"number2\",\"balance\":10.0,\"deleted\":false,\"creationDate\":null}]"));
    }

    @Test
    void getAccount_WhenAccountId_ThenReturnAccount() throws Exception {
        Account account = createAccount(1L);

        given(accountService.getAccountById(account.getId().intValue())).willReturn(account);

        mockMvc.perform(get("/account/{id}", account.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"number\":\"number1\",\"balance\":10.0,\"deleted\":false,\"creationDate\":null}"));
    }

    @Test
    void saveAccount_WhenAccountId_ThenCreateAccount() throws Exception {
        Account account = createAccount(1L);

        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    void deleteAccount_WhenAccountId_ThenDeleteAccount() throws Exception {
        Account account = createAccount(1L);

        mockMvc.perform(delete("/account/{id}", account.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void findByUser_WhenUserExist_ThenReturnAccounts() throws Exception {
        Account account = createAccount(1L);

        given(accountService.findByUser(1)).willReturn(List.of(account));

        mockMvc.perform(get("/account/by/user/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"number\":\"number1\",\"balance\":10.0,\"deleted\":false,\"creationDate\":null}]"));
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
