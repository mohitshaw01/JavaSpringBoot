package com.ApiProject.ApiProject.Utils;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class PasswordHashingUtil {
    public static String HashPassword(String Password){
        return Base64.getEncoder().encodeToString(Password.getBytes());
    }
}
