package com.itacademy.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptMain {


    public static void main(String[] args) {
        String encode = new BCryptPasswordEncoder().encode("user");
        System.out.println(encode);

    }
}
