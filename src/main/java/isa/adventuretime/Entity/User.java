package isa.adventuretime.Entity;

import javax.persistence.Column;
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
	protected Boolean authenticated;
	protected int emailHash;
	// protected String loginStatus;

	@Column(columnDefinition = "boolean default false")
	protected Boolean deleted;

	private String userType;

	public User() {
	}

	public User(String name, String lastname, String email, String password, String address, String city,
			String country, String telephoneNumber) {
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.city = city;
		this.country = country;
		this.telephoneNumber = telephoneNumber;
		this.authenticated = false;
		this.emailHash = email.hashCode();
		this.userType = "";
		// this.loginStatus = "";
	}

	public void updateWithUser(User user) {
		this.address = user.address;
		this.authenticated = user.authenticated; // Ne treba
		this.city = user.city;
		this.country = user.country;
		// this.email = user.email; NOT Allowed
		this.emailHash = user.emailHash; // Ne treba
		this.lastname = user.lastname; // Ne treba
		this.name = user.name; // Ne treba
		this.telephoneNumber = user.telephoneNumber;
		this.userType = user.getUserType(); // Ne treba
		this.password = user.getPassword(); // Ne treba
	}

	public void updateUserWithUser(User user) {
		this.password = user.getPassword();
		this.address = user.address;
		this.city = user.city;
		this.country = user.country;
		this.telephoneNumber = user.telephoneNumber;
	}

}