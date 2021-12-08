package isa.adventuretime.DTO;

import java.util.ArrayList;

import isa.adventuretime.Entity.RoomBooking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CottageNameRoomBookingDTO {
    private String cottageName;
    private ArrayList<RoomBooking> roomBookings;

    public CottageNameRoomBookingDTO() {
    
    }

    public CottageNameRoomBookingDTO(String cottageName, ArrayList<RoomBooking> roomBookings) {
        this.cottageName = cottageName;
        this.roomBookings = roomBookings;
    }
}
