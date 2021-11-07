package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Room;
import isa.adventuretime.Repository.RoomRepo;

@Service
public class RoomService{
    private RoomRepo roomRepo;

    @Autowired
    public RoomService(RoomRepo aR){
        this.roomRepo = aR;
    }

    public  Room getById(Long id){
        return roomRepo.getById(id);
    }

}