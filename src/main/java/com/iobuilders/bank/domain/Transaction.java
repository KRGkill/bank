package com.iobuilders.bank.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table
public class Transaction {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private Double amount;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="account_id")
    @JsonIgnore
    private Account account;

    @Column(name="withdrawal_account_from_id")
    private Long withdrawalAccountFromId;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name="creation_date")
    private Date creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getWithdrawalAccountFromId() {
        return withdrawalAccountFromId;
    }

    public void setWithdrawalAccountFromId(Long withdrawalAccountFromId) {
        this.withdrawalAccountFromId = withdrawalAccountFromId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
