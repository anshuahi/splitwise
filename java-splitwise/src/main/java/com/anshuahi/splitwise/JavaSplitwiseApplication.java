package com.anshuahi.splitwise;

import com.anshuahi.splitwise.utils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaSplitwiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSplitwiseApplication.class, args);
        String s = "Anshu";
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.generateToken(s);
        System.out.println("token " + token);
        String re = jwtUtil.extractUser(token);
        System.out.println("string " + re);
	}

}
