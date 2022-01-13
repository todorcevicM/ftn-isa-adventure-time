package isa.adventuretime.Entity;

import java.util.Date;

import javax.persistence.Column;
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
public class AdventureBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long bookedAdventureId;
	private Long bookedInstructorId;
	private Long registeredUserId;
	private Boolean quickBooking;
	@Column(columnDefinition = "boolean default false")
	private Boolean reportMade;

	private Date start;
	private Date end;
	private double price; // if not default price
	private String extraService; // if any
	private int maxUsers; // if not default, is this needed?

	public AdventureBooking() {

	}

	public AdventureBooking(Long bookedAdventureId, Long bookedInstrucotrId, Long registeredUserId, Date start,
			Date end, String extraService, int maxUsers) {
		this.bookedAdventureId = bookedAdventureId;
		this.bookedInstructorId = bookedInstrucotrId;
		this.registeredUserId = registeredUserId;
		this.start = start;
		this.end = end;
		this.extraService = extraService;
		this.maxUsers = maxUsers;
		this.quickBooking = false;
		this.price = 0;
		this.reportMade = false;
	}

	// Konstruktor za akcije
	public AdventureBooking(Long bookedAdventureId, Date start, Date end, double price, int maxUsers, Long instructorId, String extraService) {
		this.bookedAdventureId = bookedAdventureId;
		this.start = start;
		this.end = end;
		this.registeredUserId = 0L;
		this.quickBooking = true;
		this.price = price;
		this.reportMade = false;
		this.maxUsers = maxUsers;
		// TODO:
		this.extraService = extraService;
		this.bookedInstructorId = instructorId;
	}
}