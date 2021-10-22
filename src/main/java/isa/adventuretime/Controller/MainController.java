package isa.adventuretime.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import isa.adventuretime.Entity.User;
import isa.adventuretime.Repository.AdministratorRepo;

@RestController // Ili @Controller sa @ResponseBody, ne sme samo @Controller
@RequestMapping(path = "/api") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired
	private AdministratorRepo ar;

	public MainController() {
	}

	@GetMapping(path = "/test")
	public String frontendTest() {
		System.out.println(); // 1L je long
		User user = new User();
		user.setAddress("A");
		user.getAddress();
		return ar.getOne(1L).getEmail();
	}
}