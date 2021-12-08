package isa.adventuretime.DTO;
import java.util.ArrayList;

import isa.adventuretime.Entity.AdventureBooking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdventureNameAdventureBookingDTO {
    private String adventureName;
    private ArrayList<AdventureBooking> adventureBookings;

    public AdventureNameAdventureBookingDTO() {
    
    }

    public AdventureNameAdventureBookingDTO(String adventureName, ArrayList<AdventureBooking> adventureBookings) {
        this.adventureName = adventureName;
        this.adventureBookings = adventureBookings;
    }
}
