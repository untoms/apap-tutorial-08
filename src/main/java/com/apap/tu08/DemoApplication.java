package com.apap.tu08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("admin"));
		System.out.println(encoder.encode("admin"));
		System.out.println(encoder.encode("admin"));
		System.out.println(encoder.encode("admin"));
		System.out.println(encoder.encode("admin"));
		System.out.println(encoder.encode("admin"));
	}
}
