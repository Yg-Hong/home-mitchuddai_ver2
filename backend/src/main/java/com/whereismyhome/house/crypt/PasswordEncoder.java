//package com.whereismyhome.house.crypt;
//
//import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PasswordEncoder {
//
//    private static final SCryptPasswordEncoder encoder = new SCryptPasswordEncoder(
//            16384,
//            8,
//            1,
//            32,
//            64);
//
//    public String encrypt(String rawPassword) {
//        return encoder.encode(rawPassword);
//    }
//
//    public boolean matches(String rawPassword, String encrypted) {
//        return encoder.matches(rawPassword, encrypted);
//    }
//
//
//}
