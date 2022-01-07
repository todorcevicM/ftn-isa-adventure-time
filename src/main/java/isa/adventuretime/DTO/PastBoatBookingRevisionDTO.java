package isa.adventuretime.DTO;

import isa.adventuretime.Entity.BoatBooking;
import isa.adventuretime.Entity.Revision;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PastBoatBookingRevisionDTO {

	private BoatBooking boatBooking;
	private Revision revision;

	public PastBoatBookingRevisionDTO() {

	}

	public PastBoatBookingRevisionDTO(BoatBooking boatBooking, Revision revision) {
		this.boatBooking = boatBooking;
		this.revision = revision;
	}

}
