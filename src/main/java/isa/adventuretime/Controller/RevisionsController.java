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
import isa.adventuretime.Entity.Boat;
import isa.adventuretime.Entity.BoatBooking;
import isa.adventuretime.Entity.BoatOwner;
import isa.adventuretime.Entity.Cottage;
import isa.adventuretime.Entity.CottageOwner;
import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Entity.Revision;
import isa.adventuretime.Entity.RoomBooking;
import isa.adventuretime.Service.AdventureBookingService;
import isa.adventuretime.Service.AdventureService;
import isa.adventuretime.Service.BoatBookingService;
import isa.adventuretime.Service.BoatOwnerService;
import isa.adventuretime.Service.BoatService;
import isa.adventuretime.Service.CottageOwnerService;
import isa.adventuretime.Service.CottageService;
import isa.adventuretime.Service.FishingInstructorService;
import isa.adventuretime.Service.MailService;
import isa.adventuretime.Service.RevisionsService;
import isa.adventuretime.Service.RoomBookingService;

@RestController
@RequestMapping(path = "/api/revision")
public class RevisionsController {
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

    @RequestMapping(value = "/getAll")
    public ResponseEntity<ArrayList<Revision>> getAll() {
        return new ResponseEntity<>(revisionsService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllNotDeniedNotApproved")
    public ResponseEntity<ArrayList<Revision>> getAllNotDeniedNotApproved() {
        for (Revision r : revisionsService.findAllByNotDeniedAndNotApproved()) {
            System.out.println(r.getId());
        }
        return new ResponseEntity<>(revisionsService.findAllByNotDeniedAndNotApproved(), HttpStatus.OK);
    }

    @PostMapping(value = "/approveRevision/{id}")
    public Boolean approveRevision(@PathVariable("id") Long id) throws AddressException, UnsupportedEncodingException {
        Revision revision = revisionsService.getById(id);
        revision.setApproved(true);
        revisionsService.save(revision);

        // TODO: send email to user
        switch (revision.getType()) {
            case BOAT: 
                BoatBooking boatBooking = boatBookingService.getById(revision.getBookingId());
                Boat boat = boatService.getById(boatBooking.getBookedBoatId());
                BoatOwner boatOwner = boatOwnerService.getById(boat.getOwnerId());                
                
                mailService.SendMail(boatOwner.getEmail(), boatOwner.getName(), "A revision for your service has been submitted! \n\nSincerely, Adventure Time.");
                
                break;

            case COTTAGE: 
                RoomBooking roomBooking = roomBookingService.getById(revision.getBookingId());
                Cottage cottage = cottageService.getById(roomBooking.getCottageId());
                CottageOwner cottageOwner = cottageOwnerService.getById(cottage.getOwnerId());
                
                mailService.SendMail(cottageOwner.getEmail(), cottageOwner.getName(), "A revision for your service has been submitted! \n\nSincerely, Adventure Time.");
                break;

            case ADVENTURE: 
                AdventureBooking adventureBooking = adventureBookingService.getById(revision.getBookingId());
                Adventure adventure = adventureService.getById(adventureBooking.getBookedAdventureId());
                FishingInstructor fishingInstructor = fishingInstructorService.getById(adventure.getInstructorId());

                mailService.SendMail(fishingInstructor.getEmail(), fishingInstructor.getName(), "A revision for your service has been submitted! \n\nSincerely, Adventure Time.");
                break;
            
            default: 
                return false;
        }

        return true;
    }

    @PostMapping(value = "/denyRevision/{id}")
    public Boolean denyRevision(@PathVariable("id") Long id) {
        Revision revision = revisionsService.getById(id);
        revision.setDenied(true);
        revisionsService.save(revision);

        return true;
    }

    @PostMapping(value = "/sendRevision")
    public Revision sendRevision(RequestEntity<Revision> revision) {
        Revision newRevision = new Revision(revision.getBody());
        System.out.println(newRevision.getRating());
        System.out.println(newRevision.getRevision());
        System.out.println(newRevision.getType());
        System.out.println(newRevision.getBookingId());

        return revisionsService.save(newRevision);
    }


}
