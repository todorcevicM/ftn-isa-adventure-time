package isa.adventuretime.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import isa.adventuretime.Entity.Cottage;
import isa.adventuretime.Service.CottageService;

@RestController // Ili @Controller sa @ResponseBody, ne sme samo @Controller
@RequestMapping(path = "/api/cottages")
public class CottageController {

	@Autowired
	private CottageService cs;

	@GetMapping(path = "/get")
	public ResponseEntity<ArrayList<Cottage>> getCottages() {
		return new ResponseEntity<ArrayList<Cottage>>(cs.findAll(), HttpStatus.OK);
	}
}
