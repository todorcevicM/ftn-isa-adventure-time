package isa.adventuretime.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.Entity.BoatOwner;
import isa.adventuretime.Entity.CottageOwner;
import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Entity.RegisteredUser;
import isa.adventuretime.Entity.UnregisteredUser;
import isa.adventuretime.Service.BoatOwnerService;
import isa.adventuretime.Service.CottageOwnerService;
import isa.adventuretime.Service.FishingInstructorService;
import isa.adventuretime.Service.RegisteredUserService;

@RestController
@RequestMapping(path = "/api/registerRequest")
public class UserController {

    @Autowired
    private RegisteredUserService registeredUserService;

    @Autowired 
    private BoatOwnerService boatOwnerService;

    @Autowired
    private CottageOwnerService cottageOwnerService;

    @Autowired 
    private FishingInstructorService fishingInstructorService;
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerUser(RequestEntity<UnregisteredUser> request) {  
        System.out.println(request.getBody().getEmail());   

        String new_user_email = request.getBody().getEmail();

        if (registeredUserService.findByEmail(new_user_email) == null && 
            boatOwnerService.findByEmail(new_user_email) == null && 
            cottageOwnerService.findByEmail(new_user_email) == null && 
            fishingInstructorService.findByEmail(new_user_email) == null) {
        
                switch (request.getBody().getType()) {
                    case "Standard User":                 
                        RegisteredUser registeredUser = new RegisteredUser(
                            request.getBody().getName(),
                            request.getBody().getLastname(),
                            request.getBody().getEmail(),
                            request.getBody().getPassword(),
                            request.getBody().getAddress(),
                            request.getBody().getCity(),
                            request.getBody().getCountry(),
                            request.getBody().getTelephoneNumber()
                        );
                        registeredUserService.register(registeredUser);                        
                        break;
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
        else {
            System.out.println("User with that E-mail already exists");
            // TODO: do something with this
        }    
    }

    
}
