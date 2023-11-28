package com.whereismyhome.house.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class WhereIsMyHomeException extends RuntimeException {

    public final Map<String, String> validation = new HashMap<>();

    public WhereIsMyHomeException(String message) {
        super(message);
    }

    public WhereIsMyHomeException(String message, Throwable cause) {
        super(message, cause);

    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
