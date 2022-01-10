package isa.adventuretime.DTO;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdventureNameAdventureBookingDTO {
	private String adventureName;
	private ArrayList<UserNameAdventureBookingDTO> userNameAdventureBookingDTO;

	public AdventureNameAdventureBookingDTO() {

	}

	public AdventureNameAdventureBookingDTO(String adventureName,
			ArrayList<UserNameAdventureBookingDTO> userNameAdventureBookingDTO) {
		this.adventureName = adventureName;
		this.userNameAdventureBookingDTO = userNameAdventureBookingDTO;
	}
}
