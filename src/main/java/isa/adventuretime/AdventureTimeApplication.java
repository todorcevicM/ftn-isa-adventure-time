package isa.adventuretime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdventureTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdventureTimeApplication.class, args);
		System.out.println("Test print iz main klase.");
	}
}
