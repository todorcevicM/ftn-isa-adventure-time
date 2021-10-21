package isa.adventuretime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdventureTimeApplication {

	@Autowired
	public static EntityManagerFactory emf;
	static EntityManager em = emf.createEntityManager();

	public static void main(String[] args) {
		em.createNativeQuery("drop table administrator;").executeUpdate();
		SpringApplication.run(AdventureTimeApplication.class, args);
		System.out.println("Test print iz main klase.");
	}

}
