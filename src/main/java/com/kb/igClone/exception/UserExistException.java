package com.kb.igClone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="User with login exist")
public class UserExistException extends RuntimeException{
}
