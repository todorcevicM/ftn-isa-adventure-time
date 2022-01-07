package isa.adventuretime.DTO;

import isa.adventuretime.Entity.Revision;
import isa.adventuretime.Entity.RoomBooking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PastRoomBookingRevisionDTO {

	private RoomBooking roomBooking;
	private Revision revision;

	public PastRoomBookingRevisionDTO() {

	}

	public PastRoomBookingRevisionDTO(RoomBooking roomBooking, Revision revision) {
		this.roomBooking = roomBooking;
		this.revision = revision;
	}

}
