package com.whereismyhome.house.exception;

public class DuplicateUserIdException extends WhereIsMyHomeException {

    private static final String MESSAGE = "이미 등록된 아이디입니다.";

    public DuplicateUserIdException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
