package isa.adventuretime.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import isa.adventuretime.Service.AdministratorService;

@RestController // Ili @Controller sa @ResponseBody, ne sme samo @Controller
@RequestMapping(path = "/api")
public class MainController {

	@Autowired
	private AdministratorService as;

	public MainController() {
	}

	@GetMapping(path = "/get")
	public String frontendGETTest() {
		System.out.println(as.getById(1L).getEmail()); // 1L je long
		return as.getById(1L).getEmail();
	}

	@PostMapping(path = "/post")
	public String frontendPOSTTest() {
		System.out.println(as.getById(1L).getName());
		return as.getById(1L).getName();
	}

	// TODO: Testirati JSON return
}