package isa.adventuretime.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BoatOwner extends User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	public BoatOwner(String name, String lastname, String email, String password, String address, String city,
			String country, String telephoneNumber) {
		super(name, lastname, email, password, address, city, country, telephoneNumber);
	}

	public BoatOwner() {
		super();
	}


	@OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY,
	cascade = CascadeType.ALL)
	private Set<Boat> boatsOwned = new HashSet<>();
}
