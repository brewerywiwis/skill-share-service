package com.example.accountservice.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptUtil {
    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encode(String message) {
        return bCryptPasswordEncoder.encode(message);
    }

    public static boolean validate(String rawMessage, String encodedMessage) {
        return bCryptPasswordEncoder.matches(rawMessage, encodedMessage);
    }
}