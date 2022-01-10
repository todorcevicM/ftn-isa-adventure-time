package isa.adventuretime.DTO;

import isa.adventuretime.Entity.Revision;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PastAdventureBookingRevisionDTO {

	private AdventureNameAdventureBookingDTO adventureNameAdventureBookingDTO;
	private Revision revision;

	public PastAdventureBookingRevisionDTO() {

	}

	public PastAdventureBookingRevisionDTO(AdventureNameAdventureBookingDTO adventureNameAdventureBookingDTO,
			Revision revision) {
		this.adventureNameAdventureBookingDTO = adventureNameAdventureBookingDTO;
		this.revision = revision;
	}

}
