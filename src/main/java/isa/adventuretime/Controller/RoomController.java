package isa.adventuretime.Controller;

import java.util.ArrayList;

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

import isa.adventuretime.Entity.Room;
import isa.adventuretime.Service.RoomService;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

	@Autowired
	private RoomService roomService;

	@GetMapping("/getAllByCottageId/{id}")
	public ResponseEntity<ArrayList<Room>> getAllByCottageId(@PathVariable("id") Long id) {
		return new ResponseEntity<>(roomService.findAllByCottageId(id), HttpStatus.OK);
	}

	// @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
	// produces = MediaType.APPLICATION_JSON_VALUE)
	// public ResponseEntity<Room> saveRoom(RequestEntity<Room> room) {
	// // TODO: Prosledi objekat, ne RequestEntity
	// return new ResponseEntity<Room>(roomService.save(room), HttpStatus.OK);
	// }
}