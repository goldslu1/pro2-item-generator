package com.ItemGenerator.ItemGenerator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = encoder.encode("lukas");
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);
    }
}
