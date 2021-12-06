package isa.adventuretime.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.RoomBooking;
import isa.adventuretime.Repository.RoomBookingRepo;

@Service
public class RoomBookingService {
	@Autowired
	private RoomBookingRepo roomBookingRepo;

	ArrayList<RoomBooking> findAllByBookedRoomId(Long id) {
		return roomBookingRepo.findAllByBookedRoomId(id);
	}

}
