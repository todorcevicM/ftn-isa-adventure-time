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
public class RoomBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long bookedRoomId;
	private Long registeredUserId;
	private Boolean quickBooking;

	private Date start;
	private Date end;
	private double price; // if not default price
	private String extraService; // if any
	private int maxUsers; // if not default, is this needed?
	private Long cottageId;

	@Column(columnDefinition = "boolean default false")
	private boolean reportMade;

	public RoomBooking() {

	}

	public RoomBooking(Long bookedRoomId, Long RegisteredUserId, Boolean quickBooking, Date start, Date end, double price, String extraService, int maxUsers, Long cottageId) {
		this.bookedRoomId = bookedRoomId;
		this.registeredUserId = RegisteredUserId;
		this.quickBooking = quickBooking;
		this.start = start;
		this.end = end;
		this.price = price;
		this.extraService = extraService;
		this.maxUsers = maxUsers;
		this.cottageId = cottageId;
		this.reportMade = false;
	}
	public RoomBooking(Long bookedRoomId, Long RegisteredUserId, Date start, Date end, String extraService, int maxUsers, Long cottageId) {
		this.bookedRoomId = bookedRoomId;
		this.registeredUserId = RegisteredUserId;
		this.start = start;
		this.end = end;
		this.extraService = extraService;
		this.maxUsers = maxUsers;
		this.cottageId = cottageId;
		this.quickBooking = false;
		this.price = 0;
		this.reportMade = false;
	}

	

}