package com.elmojke.issuetracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IssueNotFoundException extends IllegalArgumentException{

    public IssueNotFoundException(String msg) {
        super(msg);
    }
}
