package com.group.genshinProg.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "User already exists") // status code = 409
public class UserAlreadyExistException extends RuntimeException {}
