package isa.adventuretime.DTO;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoatNameBoatBookingDTO {
	private String boatName;
	private ArrayList<UserNameBoatBookingDTO> userNameBoatBookingDTO;

	public BoatNameBoatBookingDTO() {

	}

	public BoatNameBoatBookingDTO(String boatName, ArrayList<UserNameBoatBookingDTO> userNameBoatBookingDTO) {
		this.boatName = boatName;
		this.userNameBoatBookingDTO = userNameBoatBookingDTO;
	}
}
