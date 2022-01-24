package com.iobuilders.bank.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iobuilders.bank.application.service.TransactionService;
import com.iobuilders.bank.domain.Account;
import com.iobuilders.bank.domain.Transaction;
import com.iobuilders.bank.domain.TransactionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAll_WhenExist_ThenReturnTransactions() throws Exception {
        Transaction transaction1 = createTransaction(1L);
        Transaction transaction2 = createTransaction(2L);

        given(transactionService.getAllTransaction()).willReturn(List.of(transaction1, transaction2));

        mockMvc.perform(get("/transaction"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"amount\":10.0,\"withdrawalAccountFromId\":null,\"type\":\"DEPOSIT\",\"creationDate\":null},{\"id\":2,\"amount\":10.0,\"withdrawalAccountFromId\":null,\"type\":\"DEPOSIT\",\"creationDate\":null}]"));
    }

    @Test
    void getTransaction_WhenTransactionId_ThenReturnTransaction() throws Exception {
        Transaction transaction = createTransaction(1L);

        given(transactionService.getTransactionById(transaction.getId().intValue())).willReturn(transaction);

        mockMvc.perform(get("/transaction/{id}", transaction.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"amount\":10.0,\"withdrawalAccountFromId\":null,\"type\":\"DEPOSIT\",\"creationDate\":null}"));
    }

    @Test
    void saveTransaction_WhenTransactionId_ThenCreateTransaction() throws Exception {
        Transaction transaction = createTransaction(1L);

        given(transactionService.create(10.0, 1, TransactionType.TRANSFER, 2L)).willReturn(transaction.getId());

        mockMvc.perform(post("/transaction")
                        .param("amount", String.valueOf(10))
                        .param("accountId", String.valueOf(1))
                        .param("transactionType", String.valueOf(TransactionType.TRANSFER))
                        .param("withdrawalAccountFromId", String.valueOf(2L)))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    void deleteTransaction_WhenTransactionId_ThenDeleteTransaction() throws Exception {
        Transaction transaction = createTransaction(1L);

        mockMvc.perform(delete("/transaction/{id}", transaction.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void findByTransaction_WhenTransactionExist_ThenReturnTransactions() throws Exception {
        Transaction transaction1 = createTransaction(1L);
        Transaction transaction2 = createTransaction(2L);

        given(transactionService.findByAccountId(1)).willReturn(List.of(transaction1, transaction2));

        mockMvc.perform(get("/transaction/by/account/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"amount\":10.0,\"withdrawalAccountFromId\":null,\"type\":\"DEPOSIT\",\"creationDate\":null},{\"id\":2,\"amount\":10.0,\"withdrawalAccountFromId\":null,\"type\":\"DEPOSIT\",\"creationDate\":null}]"));
    }

    private Transaction createTransaction(Long id) {
        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setAmount(10.0);
        transaction.setAccount(new Account());
        transaction.setType(TransactionType.DEPOSIT);

        return transaction;
    }
}
