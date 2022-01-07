package isa.adventuretime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AdventureTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdventureTimeApplication.class, args);
		System.out.println("🎇🎇🎇🎇🎇🎇🎇🎇🎇🎇🎇🎇🎇🎇🎇🎇");
		System.out.println("早安，中国！ 我真的很喜欢 BING CHILLING 🥶🍦");
		System.out.println("🎇🎇🎇🎇🎇🎇🎇🎇🎇🎇🎇🎇🎇🎇🎇🎇");
	}
}
