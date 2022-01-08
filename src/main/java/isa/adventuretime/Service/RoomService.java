package isa.adventuretime.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Room;
import isa.adventuretime.Repository.RoomRepo;

@Service
public class RoomService {
	private RoomRepo roomRepo;

	@Autowired
	public RoomService(RoomRepo aR) {
		this.roomRepo = aR;
	}

	public Room getById(Long id) {
		return roomRepo.getById(id);
	}

	public ArrayList<Room> findAllByCottageId(Long id) {
		return roomRepo.findAllByCottageId(id);
	}

	public Room save(Room room) {
		return roomRepo.save(room);
	}

	public void deleteById(Long id) {
		roomRepo.deleteById(id);
	}

	public ArrayList<Room> getAllByCottageId(Long cottageId) {
		return roomRepo.findAllByCottageId(cottageId);
	}

}
