package isa.adventuretime.Entity;

import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class User {
	protected String name;
	protected String lastname;
	protected String email;
	protected String password;
	protected String address;
	protected String city;
	protected String country;
	protected String telephoneNumber;
	protected Boolean wantsDeletion;
	protected Boolean authenticated;

	public User() {
	}

	public User(String name, String lastname, String email, String password, String address, String city, String country, String telephoneNumber) {
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.city = city;
		this.country = country;
		this.telephoneNumber = telephoneNumber;
        this.wantsDeletion = false;
        this.authenticated = false;
	}
}