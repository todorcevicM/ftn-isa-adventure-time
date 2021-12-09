package isa.adventuretime.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.RoomBooking;
import isa.adventuretime.Repository.RoomBookingRepo;
import java.util.Date;

@Service
public class RoomBookingService {
	@Autowired
	private RoomBookingRepo roomBookingRepo;

	public ArrayList<RoomBooking> findAllByBookedRoomId(Long id) {
		return roomBookingRepo.findAllByBookedRoomId(id);
	}

	public ArrayList<RoomBooking> findAllByRegisteredUserIdAndEndBefore(Long id, Date date) {
		return roomBookingRepo.findAllByRegisteredUserIdAndEndBefore(id, date);
	}

	public ArrayList<RoomBooking> findAllByRegisteredUserIdAndEndAfter(Long id, Date date) {
		return roomBookingRepo.findAllByRegisteredUserIdAndEndAfter(id, date);
	}

	public ArrayList<RoomBooking> findAllByCottageIdAndEndBefore(Long id, Date date) {
		return roomBookingRepo.findAllByCottageIdAndEndBefore(id, date);
	}

	public Boolean existsByCottageId(Long id) {
		return roomBookingRepo.existsByCottageId(id);
	}

	public Boolean existsByCottageIdAndEndAfter(Long id, Date date) {
		return roomBookingRepo.existsByCottageIdAndEndAfter(id, date);
	}

	public Boolean existsByIdAndEndAfter(Long id, Date date) {
		return roomBookingRepo.existsByIdAndEndAfter(id, date);
	}
}
