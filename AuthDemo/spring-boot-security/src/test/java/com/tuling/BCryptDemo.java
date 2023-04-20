package com.tuling;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.net.PasswordAuthentication;

public class BCryptDemo {

    public static void main(String[] args) {
        String password = "123456";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass1 = passwordEncoder.encode(password);
        System.out.println(pass1);
        String pass2 = passwordEncoder.encode(password);
        System.out.println(pass2);

//        String pass1 = BCrypt.hashpw(password, BCrypt.gensalt());
//        System.out.println(pass1);
//        System.out.println(BCrypt.checkpw(password, pass1));
//        String pass2 = BCrypt.hashpw(password, BCrypt.gensalt());
//        System.out.println(pass2);
//        System.out.println(BCrypt.checkpw(password, pass2));

        System.out.println(passwordEncoder.matches(password, pass1));
        System.out.println(passwordEncoder.matches(password, pass2));

    }
}
