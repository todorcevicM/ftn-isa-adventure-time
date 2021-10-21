package isa.adventuretime.Controller;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.Entity.User;

@RestController // Ili @Controller sa @ResponseBody, ne sme samo @Controller
@RequestMapping(path = "/api") // This means URL's start with /demo (after Application path)
public class MainController {
	// @Autowired
	// private PersonRepo pr;

	public MainController() {
	}

	@GetMapping(path = "/person")
	public String getPerson() {
		System.out.println(); // 1L je long
		User user = new User();
		user.setAddress("A");
		user.getAddress();
		return "This is a message from the controller. \n/person was accessed.";
	}
}