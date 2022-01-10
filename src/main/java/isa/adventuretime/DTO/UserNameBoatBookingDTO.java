package isa.adventuretime.DTO;

import isa.adventuretime.Entity.BoatBooking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNameBoatBookingDTO {
	private String userName;
	private BoatBooking boatBooking;

	public UserNameBoatBookingDTO() {

	}

	public UserNameBoatBookingDTO(String userName, BoatBooking boatBooking) {
		this.userName = userName;
		this.boatBooking = boatBooking;
	}
}
