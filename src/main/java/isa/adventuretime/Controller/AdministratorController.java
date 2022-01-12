package isa.adventuretime.Controller;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import isa.adventuretime.Entity.Administrator;
import isa.adventuretime.Service.AdministratorService;

@RestController
@RequestMapping(path = "/api/administrator")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;



	@GetMapping(value = "/profitYear")
	public float reportProfitYear(){
		return administratorService.reportProfitYear();
	}

	@GetMapping(value = "/profitMonth")
	public float reportProfitMonth(){
		return administratorService.reportProfitMonth();
	}

	@GetMapping(value = "/profitWeek")
	public float reportProfitWeek(){
		return administratorService.reportProfitWeek();
	}

	@GetMapping(value = ("/{id}"))
	public ResponseEntity<Administrator> getAdministratorById(@PathVariable("id") Long id) {
		Administrator administrator = administratorService.getById(id);
		if (administrator == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(administrator, HttpStatus.OK);
		}
	}

	@GetMapping(value = ("/get/{emailHash}"))
	public ResponseEntity<Administrator> getAdministratorByEmailHash(@PathVariable("emailHash") int emailHash) {
		Administrator administrator = administratorService.findByEmailHash(emailHash);
		if (administrator == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(administrator, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getAllNotDeleted")
	public ResponseEntity<ArrayList<Administrator>> getAllNotDeleted() {
		return new ResponseEntity<ArrayList<Administrator>>(administratorService.findAllByDeleted(), HttpStatus.OK);
	}

}