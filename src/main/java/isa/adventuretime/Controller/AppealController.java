package isa.adventuretime.Controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.Entity.Adventure;
import isa.adventuretime.Entity.AdventureBooking;
import isa.adventuretime.Entity.Appeal;
import isa.adventuretime.Entity.Boat;
import isa.adventuretime.Entity.BoatBooking;
import isa.adventuretime.Entity.BoatOwner;
import isa.adventuretime.Entity.Cottage;
import isa.adventuretime.Entity.CottageOwner;
import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Entity.RegisteredUser;
import isa.adventuretime.Entity.RoomBooking;
import isa.adventuretime.Service.AdventureBookingService;
import isa.adventuretime.Service.AdventureService;
import isa.adventuretime.Service.AppealService;
import isa.adventuretime.Service.BoatBookingService;
import isa.adventuretime.Service.BoatOwnerService;
import isa.adventuretime.Service.BoatService;
import isa.adventuretime.Service.CottageOwnerService;
import isa.adventuretime.Service.CottageService;
import isa.adventuretime.Service.FishingInstructorService;
import isa.adventuretime.Service.MailService;
import isa.adventuretime.Service.RegisteredUserService;
import isa.adventuretime.Service.RevisionsService;
import isa.adventuretime.Service.RoomBookingService;

@RestController
@RequestMapping(path = "/api/appeal")
public class AppealController {
    @Autowired 
    private RevisionsService revisionsService;

    @Autowired
	private MailService mailService;

    @Autowired
    private BoatBookingService boatBookingService;

    @Autowired
    private AdventureBookingService adventureBookingService;

    @Autowired
    private RoomBookingService roomBookingService;

    @Autowired 
    private BoatService boatService;
    @Autowired
    private AdventureService adventureService;
    @Autowired
    private CottageService cottageService;

    @Autowired 
    private BoatOwnerService boatOwnerService;
    @Autowired
    private FishingInstructorService fishingInstructorService;
    @Autowired
    private CottageOwnerService cottageOwnerService;

    @Autowired 
    private AppealService appealService;

    @Autowired
    private RegisteredUserService registeredUserService;

    @RequestMapping(value = "/getAll")
    public ResponseEntity<ArrayList<Appeal>> getAll() {
        return new ResponseEntity<>(appealService.getAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/answerAppeal/{id}")
    public Boolean approveRevision(@PathVariable("id") Long id, RequestEntity<String> answer) throws AddressException, UnsupportedEncodingException {
        Appeal appeal = appealService.getById(id);
        RegisteredUser registeredUser = new RegisteredUser();
        
        switch (appeal.getType()) {
            case BOAT: 
                BoatBooking boatBooking = boatBookingService.getById(appeal.getBookingId());
                Boat boat = boatService.getById(boatBooking.getBookedBoatId());
                BoatOwner boatOwner = boatOwnerService.getById(boat.getOwnerId());  

                
                mailService.SendMail(boatOwner.getEmail(), boatOwner.getName(), "An appeal concernig your service has been answered!\nPlease take some time to read the answer and...\n" + answer.getBody() + "\n\nSincerely, Adventure Time.");

                registeredUser = registeredUserService.getById(boatBooking.getRegisteredUserId());
                mailService.SendMail(registeredUser.getEmail(), registeredUser.getName(), "Your appeal has been answered and has been forwarded to the service provider...\n" + answer.getBody() + "\n\nSincerely, Adventure Time.");

                break;

            case COTTAGE: 
                RoomBooking roomBooking = roomBookingService.getById(appeal.getBookingId());
                Cottage cottage = cottageService.getById(roomBooking.getCottageId());
                CottageOwner cottageOwner = cottageOwnerService.getById(cottage.getOwnerId());
                
                mailService.SendMail(cottageOwner.getEmail(), cottageOwner.getName(), "An appeal concernig your service has been answered!\nPlease take some time to read the answer and...\n" + answer.getBody() + "\n\nSincerely, Adventure Time.");

                registeredUser = registeredUserService.getById(roomBooking.getRegisteredUserId());
                mailService.SendMail(registeredUser.getEmail(), registeredUser.getName(), "Your appeal has been answered and has been forwarded to the service provider...\n" + answer.getBody() + "\n\nSincerely, Adventure Time.");

                break;

            case ADVENTURE: 
                AdventureBooking adventureBooking = adventureBookingService.getById(appeal.getBookingId());
                Adventure adventure = adventureService.getById(adventureBooking.getBookedAdventureId());
                FishingInstructor fishingInstructor = fishingInstructorService.getById(adventure.getInstructorId());

                mailService.SendMail(fishingInstructor.getEmail(), fishingInstructor.getName(), "An appeal concernig your service has been answered!\nPlease take some time to read the answer and...\n" + answer.getBody() + "\n\nSincerely, Adventure Time.");

                registeredUser = registeredUserService.getById(adventureBooking.getRegisteredUserId());
                mailService.SendMail(registeredUser.getEmail(), registeredUser.getName(), "Your appeal has been answered and has been forwarded to the service provider...\n" + answer.getBody() + "\n\nSincerely, Adventure Time.");

                break;
            
            default: 
                return false;
        }

        appealService.delete(appeal);

        return true;
    }

    @PostMapping(value = "/sendAppeal")
    public Appeal sendAppeal(RequestEntity<Appeal> appeal) {
        return appealService.save(appeal.getBody());
    }

}
