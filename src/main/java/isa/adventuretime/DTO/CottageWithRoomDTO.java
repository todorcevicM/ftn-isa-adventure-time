package isa.adventuretime.DTO;

import isa.adventuretime.Entity.Cottage;
import isa.adventuretime.Entity.Room;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CottageWithRoomDTO {
    private Cottage cottage;
    private Room room;

    public CottageWithRoomDTO(){

    }

    public CottageWithRoomDTO(Cottage cottage, Room room){
        this.cottage = cottage;
        this.room = room;
    }

}
