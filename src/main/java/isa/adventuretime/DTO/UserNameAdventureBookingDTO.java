package isa.adventuretime.DTO;

import isa.adventuretime.Entity.AdventureBooking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNameAdventureBookingDTO {
	private String userName;
	private AdventureBooking adventureBooking;

	public UserNameAdventureBookingDTO() {

	}

	public UserNameAdventureBookingDTO(String userName, AdventureBooking adventureBooking) {
		this.userName = userName;
		this.adventureBooking = adventureBooking;
	}
}
