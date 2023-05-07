package com.kb.igClone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Problem with server")
public class ServerErrorException extends RuntimeException {

    public ServerErrorException(Exception e) {
        super(e);
    }

    public ServerErrorException(String message) {
        super(message);
    }
}
