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
public class BoatBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long bookedBoatId;
	private Long registeredUserId;
	private boolean quickBooking;

	private Date start;
	private Date end;
	private double price; // if not default price
	private String extraService; // if any
	private int maxUsers; // if not default, is this needed?

	@Column(columnDefinition = "boolean default false")
	private boolean reportMade;

	public BoatBooking() {

	}

	public BoatBooking(Long bookedBoatId, Long registeredUserId, Date start, Date end, String extraService, int maxUsers) {
		this.bookedBoatId = bookedBoatId;
		this.registeredUserId = registeredUserId;
		this.start = start;
		this.end = end;
		this.extraService = extraService;
		this.maxUsers = maxUsers;
		this.quickBooking = false;
		this.price = 0;
		this.reportMade = false;
	}



}