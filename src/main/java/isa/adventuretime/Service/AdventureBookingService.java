package isa.adventuretime.Service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.DTO.PastAdventureBookingRevisionDTO;
import isa.adventuretime.Entity.AdventureBooking;
import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Entity.Revision;
import isa.adventuretime.Repository.AdventureBookingRepo;
import isa.adventuretime.Repository.FishingInstructorRepo;
import isa.adventuretime.Repository.RevisionRepo;

@Service
public class AdventureBookingService {
	@Autowired
	private AdventureBookingRepo adventureBookingRepo;

	@Autowired
	private FishingInstructorRepo fishingInstructorRepo;

	@Autowired
	private RevisionRepo revisionRepo;

	public ArrayList<Date> getFreeTime(Long id) {
		FishingInstructor fishingInstructor = fishingInstructorRepo.findById(id).get();
		if (fishingInstructor == null) {
			System.out.println("--> No fishingInstructor with id: " + id + "\n");
			return null;
		}
		ArrayList<AdventureBooking> bookings = adventureBookingRepo.findAllByBookedInstructorId(id);
		ArrayList<Date> dates = new ArrayList<>();

		dates.add(fishingInstructor.getStartWorkPeriod());
		for (AdventureBooking booking : bookings) {
			if (dates.get(0).before(booking.getStart())) {
				dates.add(booking.getStart());
				dates.add(booking.getEnd());
			}
		}
		dates.add(fishingInstructor.getEndWorkPeriod());
		return dates;
	}

	public ArrayList<PastAdventureBookingRevisionDTO> findAllByRegisteredUserIdAndEndBefore(Long id, Date date) {
		ArrayList<AdventureBooking> bookings = adventureBookingRepo.findAllByRegisteredUserIdAndEndBefore(id, date);
		ArrayList<PastAdventureBookingRevisionDTO> pastBookings = new ArrayList<>();
		PastAdventureBookingRevisionDTO pastBooking;

		for (AdventureBooking booking : bookings) {
			Revision revision = revisionRepo.findByBookingIdAndType(booking.getId(), HeadEntityEnum.ADVENTURE);
			if (revision == null) {
				revision = new Revision(HeadEntityEnum.ADVENTURE, booking.getId());
			}
			pastBooking = new PastAdventureBookingRevisionDTO(booking, revision);
			pastBookings.add(pastBooking);
		}

		return pastBookings;
	}

	public ArrayList<AdventureBooking> findAllByRegisteredUserIdAndEndAfter(Long id, Date date) {
		return adventureBookingRepo.findAllByRegisteredUserIdAndEndAfter(id, date);
	}

	public ArrayList<AdventureBooking> findAllByBookedAdventureIdAndEndBefore(Long adventureId, Date date) {
		return adventureBookingRepo.findAllByBookedAdventureIdAndEndBefore(adventureId, date);
	}

	public Boolean existsByBookedInstructorIdAndEndAfter(Long id, Date date) {
		return adventureBookingRepo.existsByBookedInstructorIdAndEndAfter(id, date);
	}

	public AdventureBooking save(AdventureBooking adventureBooking){
		return adventureBookingRepo.save(adventureBooking);
	}
}
