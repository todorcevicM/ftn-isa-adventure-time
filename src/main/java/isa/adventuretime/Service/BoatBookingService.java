package isa.adventuretime.Service;

import java.util.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Boat;
import isa.adventuretime.Entity.BoatBooking;
import isa.adventuretime.Repository.BoatBookingRepo;
import isa.adventuretime.Repository.BoatRepo;

@Service
public class BoatBookingService {
	@Autowired
	private BoatBookingRepo boatBookingRepo;

	@Autowired
	private BoatRepo boatRepo;

	public ArrayList<Date> getFreeTime(Long id) {
		Boat currentBoat = boatRepo.findById(id).get();
		if (currentBoat == null) {
			System.out.println("--> No boat with id: " + id + "\n");
			return null;
		}
		ArrayList<BoatBooking> bookings = boatBookingRepo.findAllByBookedBoatId(id);
		ArrayList<Date> dates = new ArrayList<>();

		dates.add(currentBoat.getReservationStart());
		for (BoatBooking booking : bookings) {
			if (dates.get(0).before(booking.getStart())) {
				dates.add(booking.getStart());
				dates.add(booking.getEnd());
			}
		}
		dates.add(currentBoat.getReservationEnd());
		return dates;
	}

	public ArrayList<BoatBooking> findAllByRegisteredUserIdAndEndBefore(Long id, Date date) {
		return boatBookingRepo.findAllByRegisteredUserIdAndEndBefore(id, date);
	}

	public ArrayList<BoatBooking> findAllByRegisteredUserIdAndEndAfter(Long id, Date date) {
		return boatBookingRepo.findAllByRegisteredUserIdAndEndAfter(id, date);
	}
}
