package isa.adventuretime.DTO;

import isa.adventuretime.Entity.Revision;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PastRoomBookingRevisionDTO {

	private CottageNameRoomBookingDTO cottageNameRoomBookingDTO;
	private Revision revision;

	public PastRoomBookingRevisionDTO() {

	}

	public PastRoomBookingRevisionDTO(CottageNameRoomBookingDTO cottageNameRoomBookingDTO, Revision revision) {
		this.cottageNameRoomBookingDTO = cottageNameRoomBookingDTO;
		this.revision = revision;
	}

}
