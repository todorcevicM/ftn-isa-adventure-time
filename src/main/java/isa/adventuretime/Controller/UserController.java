package isa.adventuretime.Controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.Entity.BoatOwner;
import isa.adventuretime.Entity.CottageOwner;
import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Entity.UnregisteredUser;
import isa.adventuretime.Service.BoatOwnerService;
import isa.adventuretime.Service.CottageOwnerService;
import isa.adventuretime.Service.FishingInstructorService;

@RestController
@RequestMapping(path = "/api/registerRequest")
public class UserController {

    @Autowired 
    private BoatOwnerService boatOwnerService;

    @Autowired
    private CottageOwnerService cottageOwnerService;

    @Autowired 
    private FishingInstructorService fishingInstructorService;
    
    // post mapping to every type of user
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerUser(RequestEntity<UnregisteredUser> request) {     
        switch (request.getBody().getType()) {
            case "Boat Owner":
                BoatOwner boatOwner = new BoatOwner(
                        request.getBody().getName(),
                        request.getBody().getLastname(),
                        request.getBody().getEmail(),
                        request.getBody().getPassword(),
                        request.getBody().getAddress(),
                        request.getBody().getCity(),
                        request.getBody().getCountry(),
                        request.getBody().getTelephoneNumber()
                );
                boatOwnerService.register(boatOwner);
                break;
            case "Cottage Owner":
                CottageOwner cottageOwner = new CottageOwner(
                        request.getBody().getName(),
                        request.getBody().getLastname(),
                        request.getBody().getEmail(),
                        request.getBody().getPassword(),
                        request.getBody().getAddress(),
                        request.getBody().getCity(),
                        request.getBody().getCountry(),
                        request.getBody().getTelephoneNumber()
                );
                cottageOwnerService.register(cottageOwner);
                break;
            case "Fishing Instructor":
                FishingInstructor fishingInstructor = new FishingInstructor(
                    request.getBody().getName(),
                    request.getBody().getLastname(),
                    request.getBody().getEmail(),
                    request.getBody().getPassword(),
                    request.getBody().getAddress(),
                    request.getBody().getCity(),
                    request.getBody().getCountry(),
                    request.getBody().getTelephoneNumber()
                );
                fishingInstructorService.register(fishingInstructor);
                break;
            default:
                break;
        } 


    }

    
}
