package isa.adventuretime.Controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import isa.adventuretime.Entity.Administrator;
import isa.adventuretime.Entity.BoatOwner;
import isa.adventuretime.Entity.CottageOwner;
import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Entity.RegisteredUser;
import isa.adventuretime.Entity.UnregisteredUser;
import isa.adventuretime.Entity.User;
import isa.adventuretime.Service.AdministratorService;
import isa.adventuretime.Service.BoatOwnerService;
import isa.adventuretime.Service.CottageOwnerService;
import isa.adventuretime.Service.FishingInstructorService;
import isa.adventuretime.Service.MailService;
import isa.adventuretime.Service.RegisteredUserService;

@RestController
@RequestMapping(path = "/api/administrator")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	@GetMapping(value = ("/{id}"))
	public ResponseEntity<Administrator> getAdministrator(@PathVariable("id") Long id){
		Administrator administrator = administratorService.getById(id);
		if (administrator == null) {
			System.out.println("aaaaaaaaaa");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			System.out.println(administrator.getId());
			return new ResponseEntity<>(administrator, HttpStatus.OK);
		}

	}	

}