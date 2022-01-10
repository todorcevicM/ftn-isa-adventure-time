package isa.adventuretime.DTO;

import isa.adventuretime.Entity.Revision;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PastBoatBookingRevisionDTO {

	private BoatNameBoatBookingDTO boatNameBoatBookingDTO;
	private Revision revision;

	public PastBoatBookingRevisionDTO() {

	}

	public PastBoatBookingRevisionDTO(BoatNameBoatBookingDTO boatNameBoatBookingDTO, Revision revision) {
		this.boatNameBoatBookingDTO = boatNameBoatBookingDTO;
		this.revision = revision;
	}

}
