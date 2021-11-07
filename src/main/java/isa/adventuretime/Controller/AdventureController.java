package isa.adventuretime.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import isa.adventuretime.Entity.Adventure;
import isa.adventuretime.Service.AdventureService;

@RestController // Ili @Controller sa @ResponseBody, ne sme samo @Controller
@RequestMapping(path = "/api/adventures")
public class AdventureController {

	@Autowired
	private AdventureService as;

	@GetMapping(path = "/get")
	public ResponseEntity<ArrayList<Adventure>> getAdventures() {
		return new ResponseEntity<ArrayList<Adventure>>(as.findAll(), HttpStatus.OK);
	}
}
