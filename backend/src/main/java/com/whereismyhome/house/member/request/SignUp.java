package com.whereismyhome.house.member.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUp {

    private String userId;
    private String email;
    private String password;
    private String name;
    private String address;
    private String phoneNumber;
    private String createdAt;


}
