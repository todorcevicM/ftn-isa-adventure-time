package isa.adventuretime.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class FishingInstructor extends User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	private Date startWorkPeriod;
	private Date endWorkPeriod;

	public FishingInstructor(String name, String lastname, String email, String password, String address, String city,
			String country, String telephoneNumber) {
		super(name, lastname, email, password, address, city, country, telephoneNumber);
	}

	public FishingInstructor() {
		super();
	}

	public void updateFishingInstructor(User user, Date startWorkPeriod, Date endWorkPeriod) {
		this.updateUserWithUser(user);
		this.startWorkPeriod = startWorkPeriod;
		this.endWorkPeriod = endWorkPeriod;
	}

}
