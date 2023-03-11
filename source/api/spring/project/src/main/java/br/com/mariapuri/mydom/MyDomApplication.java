package br.com.mariapuri.mydom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MyDomApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyDomApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("dom123"));
	}

}
