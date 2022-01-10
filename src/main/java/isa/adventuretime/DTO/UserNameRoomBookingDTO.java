package isa.adventuretime.DTO;

import isa.adventuretime.Entity.RoomBooking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNameRoomBookingDTO {
	private String userName;
	private RoomBooking roomBooking;

	public UserNameRoomBookingDTO() {

	}

	public UserNameRoomBookingDTO(String userName, RoomBooking roomBooking) {
		this.userName = userName;
		this.roomBooking = roomBooking;
	}
}
