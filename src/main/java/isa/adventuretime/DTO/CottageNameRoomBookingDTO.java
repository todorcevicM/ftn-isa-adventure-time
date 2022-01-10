package isa.adventuretime.DTO;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CottageNameRoomBookingDTO {
	private String cottageName;
	private ArrayList<UserNameRoomBookingDTO> userNameRoomBookingDTO;

	public CottageNameRoomBookingDTO() {

	}

	public CottageNameRoomBookingDTO(String cottageName, ArrayList<UserNameRoomBookingDTO> userNameRoomBookingDTO) {
		this.cottageName = cottageName;
		this.userNameRoomBookingDTO = userNameRoomBookingDTO;
	}
}
