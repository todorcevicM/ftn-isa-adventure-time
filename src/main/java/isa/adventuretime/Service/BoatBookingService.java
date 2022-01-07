package isa.adventuretime.Service;

import java.util.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.DTO.PastBoatBookingRevisionDTO;
import isa.adventuretime.Entity.Boat;
import isa.adventuretime.Entity.BoatBooking;
import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Entity.Revision;
import isa.adventuretime.Repository.BoatBookingRepo;
import isa.adventuretime.Repository.BoatRepo;
import isa.adventuretime.Repository.RevisionRepo;

@Service
public class BoatBookingService {
	@Autowired
	private BoatBookingRepo boatBookingRepo;

	@Autowired
	private BoatRepo boatRepo;

	@Autowired
	private RevisionRepo revisionRepo;

	public BoatBooking getById(Long id) {
		return boatBookingRepo.getById(id);
	}

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

	public ArrayList<PastBoatBookingRevisionDTO> findAllByRegisteredUserIdAndEndBefore(Long id, Date date) {
		ArrayList<BoatBooking> bookings = boatBookingRepo.findAllByRegisteredUserIdAndEndBefore(id, date);
		ArrayList<PastBoatBookingRevisionDTO> pastBookings = new ArrayList<>();
		PastBoatBookingRevisionDTO pastBooking;

		for (BoatBooking booking : bookings) {
			Revision revision = revisionRepo.findByBookingIdAndType(booking.getId(), HeadEntityEnum.BOAT);
			if (revision == null) {
				revision = new Revision(HeadEntityEnum.BOAT, booking.getId());
			}
			pastBooking = new PastBoatBookingRevisionDTO(booking, revision);
			pastBookings.add(pastBooking);
		}

		return pastBookings;
	}

	public ArrayList<BoatBooking> findAllByRegisteredUserIdAndEndAfter(Long id, Date date) {
		return boatBookingRepo.findAllByRegisteredUserIdAndEndAfter(id, date);
	}

	public Boolean existsByBookedBoatIdAndEndAfter(Long id, Date date) {
		return boatBookingRepo.existsByBookedBoatIdAndEndAfter(id, date);
	}

	public Boolean existsByBookedBoatIdAndStartAfter(Long id, Date date) {
		return boatBookingRepo.existsByBookedBoatIdAndStartAfter(id, date);
	}

	public void deleteById(Long id) {
		boatBookingRepo.deleteById(id);
	}

	public ArrayList<BoatBooking> findAllByBookedBoatIdAndEndBefore(Long id, Date date) {
		return boatBookingRepo.findAllByBookedBoatIdAndEndBefore(id, date);
	}
}
