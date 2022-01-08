package isa.adventuretime.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.DTO.CottageWithRoomDTO;
import isa.adventuretime.Entity.Adventure;
import isa.adventuretime.Entity.AdventureBooking;
import isa.adventuretime.Entity.Boat;
import isa.adventuretime.Entity.BoatBooking;
import isa.adventuretime.Entity.Cottage;
import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Entity.Room;
import isa.adventuretime.Entity.RoomBooking;
import isa.adventuretime.Service.AdventureBookingService;
import isa.adventuretime.Service.AdventureService;
import isa.adventuretime.Service.BoatBookingService;
import isa.adventuretime.Service.BoatService;
import isa.adventuretime.Service.CottageService;
import isa.adventuretime.Service.FishingInstructorService;
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

    @Autowired
    CottageService cottageService;
    @Autowired
    AdventureService adventureService;
    @Autowired
    BoatService boatService;

    @Autowired
    FishingInstructorService fishingInstructorService;

    @PostMapping(path = "/room/{param}")
    public Boolean bookRoom(@RequestParam("param") String param, RequestEntity<CottageWithRoomDTO> cottageRoomDTO){
        // return cottageService.save(cottage.getBody()) != null;
        Cottage cottage = cottageRoomDTO.getBody().getCottage();
        Room room = cottageRoomDTO.getBody().getRoom();

        Long userId = Long.parseLong(param.split(";")[0]);
        int maxUsers = Integer.parseInt(param.split(";")[1]);
        String extraService = param.split(";")[2];

        RoomBooking roomBooking = new RoomBooking(
            room.getId(),
            userId, 
            cottage.getReservationStart(),
            cottage.getReservationEnd(),
            extraService,
            maxUsers,
            cottage.getId()
        );

        RoomBooking rb = roomBookingService.save(roomBooking);
        System.out.println(rb.getId());
        return true;
    }

    @PostMapping(path = "/boat/{param}")
    public Boolean bookBoat(@RequestParam("param") String param, RequestEntity<Boat> boat){

        Long userId = Long.parseLong(param.split(";")[0]);
        int maxUsers = Integer.parseInt(param.split(";")[1]);
        String extraService = param.split(";")[2];

        BoatBooking boatBooking = new BoatBooking(
            boat.getBody().getId(),
            userId,
            boat.getBody().getReservationStart(),
            boat.getBody().getReservationEnd(),
            extraService,
            maxUsers
        );  

        BoatBooking bb = boatBookingService.save(boatBooking);
        return bb != null;
    }

    @PostMapping(path = "/adventure/{param}")
    public Boolean bookAdventure(@RequestParam("param") String param, RequestEntity<Adventure> adventure){
        FishingInstructor fishingInstructor = fishingInstructorService.getById(adventure.getBody().getInstructorId());
        Long userId = Long.parseLong(param.split(";")[0]);
        int maxUsers = Integer.parseInt(param.split(";")[1]);
        String extraService = param.split(";")[2];

        AdventureBooking adventureBooking = new AdventureBooking(
            adventure.getBody().getId(),
            fishingInstructor.getId(),
            userId,
            fishingInstructor.getStartWorkPeriod(),
            fishingInstructor.getEndWorkPeriod(),
            extraService,
            maxUsers
        );

        AdventureBooking ab = adventureBookingService.save(adventureBooking);
        return ab != null;
    }

}
