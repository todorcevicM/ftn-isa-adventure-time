package isa.adventuretime.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.Entity.AdventureBooking;
import isa.adventuretime.Entity.BoatBooking;
import isa.adventuretime.Entity.RoomBooking;
import isa.adventuretime.Repository.RatingRepo;
import isa.adventuretime.Service.AdventureBookingService;
import isa.adventuretime.Service.BoatBookingService;
import isa.adventuretime.Service.RoomBookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    
    @Autowired
    AdventureBookingService adventureBookingService;

    @Autowired
    BoatBookingService boatBookingService;

    @Autowired
    RoomBookingService roomBookingService;

    @PostMapping(path = "/room")
    public Boolean bookRoom(RequestEntity<RoomBooking> roomBooking){
        return roomBookingService.save(roomBooking.getBody()) != null;
    }

    @PostMapping(path = "/boat")
    public Boolean bookBoat(RequestEntity<BoatBooking> boatBooking){
        return boatBookingService.save(boatBooking.getBody()) != null;
    }

    @PostMapping(path = "/adventure")
    public Boolean bookAdventure(RequestEntity<AdventureBooking> adventureBooking){
        return adventureBookingService.save(adventureBooking.getBody()) != null;
    }

}
