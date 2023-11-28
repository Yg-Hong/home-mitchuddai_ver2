package com.whereismyhome.house.exception;

public class InvalidSignIn extends WhereIsMyHomeException{

    private static final String MESSAGE = "아이디/비밀번호가 올바르지 않습니다.";

    public InvalidSignIn() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
