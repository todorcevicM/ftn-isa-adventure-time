package isa.adventuretime.Controller;

import javax.print.attribute.standard.Media;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.Entity.Administrator;
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

	@PostMapping(path = "/post", consumes = MediaType.ALL_VALUE)
	public String frontendPOSTTest(Integer num) {
		// TODO: Da li radi ovo num?
		System.out.println(as.getById(1L).getName() + " i dobijeno " + num + " sa fronta!");
		return as.getById(1L).getName();
	}

	// JSON Response na isti nacin kao sa Web-a, vanilla, bez koriscenja Jackson-a
	@GetMapping(path = "/get/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Administrator> frontendGETJSONTest() {
		Administrator test = as.getById(1L);
		System.out.println("JSON Sent Out!");
		return new ResponseEntity<>(test, HttpStatus.OK);
	}
}