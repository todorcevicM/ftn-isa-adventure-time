package isa.adventuretime.DTO;

import isa.adventuretime.Entity.AdventureBooking;
import isa.adventuretime.Entity.Revision;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PastAdventureBookingRevisionDTO {

	private AdventureBooking adventureBooking;
	private Revision revision;

	public PastAdventureBookingRevisionDTO() {

	}

	public PastAdventureBookingRevisionDTO(AdventureBooking adventureBooking, Revision revision) {
		this.adventureBooking = adventureBooking;
		this.revision = revision;
	}

}
