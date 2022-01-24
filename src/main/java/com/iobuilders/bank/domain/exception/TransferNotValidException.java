package com.iobuilders.bank.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not create the transfer.")
public class TransferNotValidException extends Exception {

    public TransferNotValidException(String message) {
        super(message);
    }
}
