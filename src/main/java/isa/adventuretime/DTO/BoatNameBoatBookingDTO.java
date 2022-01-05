package isa.adventuretime.DTO;
import java.util.ArrayList;

import isa.adventuretime.Entity.BoatBooking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoatNameBoatBookingDTO {
    private String boatName;
    private ArrayList<BoatBooking> boatBookings;

    public BoatNameBoatBookingDTO() {

    }
    
    public BoatNameBoatBookingDTO(String boatName, ArrayList<BoatBooking> boatBookings) {
        this.boatName = boatName;
        this.boatBookings = boatBookings;
    }
}
