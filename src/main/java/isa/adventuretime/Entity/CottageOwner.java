package isa.adventuretime.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CottageOwner extends User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	public CottageOwner(String name, String lastname, String email, String password, String address, String city,
			String country, String telephoneNumber) {
		super(name, lastname, email, password, address, city, country, telephoneNumber);
	}

	public CottageOwner() {
		super();
	}


}
