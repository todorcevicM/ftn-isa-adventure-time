package isa.adventuretime.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnregisteredUser {
	protected String name;
	protected String lastname;
	protected String email;
	protected String password;
	protected String address;
	protected String city;
	protected String country;
	protected String telephoneNumber;
    protected String type;
    protected String registrationReason;
	protected Boolean wantsDeletion;
	protected Boolean authenticated;

	public UnregisteredUser() {
	}

	public UnregisteredUser(String name, String lastname, String email, String password, String address, String city, String country, String telephoneNumber) {
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