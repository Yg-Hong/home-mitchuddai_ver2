package com.whereismyhome.house.exception;

import lombok.Getter;

/**
 * statusCode = 400
 */
@Getter
public class InvalidRequest extends WhereIsMyHomeException {

    private static final String MESSAGE = "잘못된 요청입니다.";
    public String fieldName;
    public String message;

    public InvalidRequest() {
        super(MESSAGE);
    }

    public InvalidRequest(String fieldName, String message) {
        super(MESSAGE);
        addValidation(fieldName, message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
