package isa.adventuretime.Service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.DTO.*;
import isa.adventuretime.Entity.AdventureBooking;
import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Entity.Revision;
import isa.adventuretime.Repository.AdventureBookingRepo;
import isa.adventuretime.Repository.FishingInstructorRepo;
import isa.adventuretime.Repository.RevisionRepo;
import isa.adventuretime.Repository.AdventureRepo;
import java.util.Calendar;

@Service
public class AdventureBookingService {
	@Autowired
	private AdventureBookingRepo adventureBookingRepo;

	@Autowired
	private FishingInstructorRepo fishingInstructorRepo;

	@Autowired
	private RevisionRepo revisionRepo;

	@Autowired
	private AdventureRepo adventureRepo;

	public AdventureBooking getById(Long id) {
		return adventureBookingRepo.getById(id);
	}

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
				revision = new Revision(HeadEntityEnum.ADVENTURE, booking.getId(), booking.getBookedAdventureId());
			}
			String adventureName = adventureRepo.findById(booking.getBookedAdventureId()).get().getName();
			UserNameAdventureBookingDTO userNameAdventureBookingDTO = new UserNameAdventureBookingDTO("",
					booking);
			ArrayList<UserNameAdventureBookingDTO> userNameAdventureBookingDTOs = new ArrayList<>();
			userNameAdventureBookingDTOs.add(userNameAdventureBookingDTO);
			AdventureNameAdventureBookingDTO adventureNameAdventureBookingDTO = new AdventureNameAdventureBookingDTO(
					adventureName, userNameAdventureBookingDTOs);
			pastBooking = new PastAdventureBookingRevisionDTO(adventureNameAdventureBookingDTO, revision);
			pastBookings.add(pastBooking);
		}

		return pastBookings;
	}

	public ArrayList<AdventureNameAdventureBookingDTO> findAllByRegisteredUserIdAndEndAfter(Long id, Date date) {
		ArrayList<AdventureBooking> bookings = adventureBookingRepo.findAllByRegisteredUserIdAndEndAfter(id, date);
		ArrayList<AdventureNameAdventureBookingDTO> adventureNameAdventureBookingDTOs = new ArrayList<>();
		AdventureNameAdventureBookingDTO adventureNameAdventureBookingDTO;

		for (AdventureBooking booking : bookings) {
			String adventureName = adventureRepo.findById(booking.getBookedAdventureId()).get().getName();
			UserNameAdventureBookingDTO userNameAdventureBookingDTO = new UserNameAdventureBookingDTO("", booking);
			ArrayList<UserNameAdventureBookingDTO> userNameAdventureBookingDTOs = new ArrayList<>();
			userNameAdventureBookingDTOs.add(userNameAdventureBookingDTO);
			adventureNameAdventureBookingDTO = new AdventureNameAdventureBookingDTO(adventureName,
					userNameAdventureBookingDTOs);
			adventureNameAdventureBookingDTOs.add(adventureNameAdventureBookingDTO);
		}

		return adventureNameAdventureBookingDTOs;
	}

	public ArrayList<AdventureBooking> findAllByBookedAdventureIdAndEndBefore(Long adventureId, Date date) {
		return adventureBookingRepo.findAllByBookedAdventureIdAndEndBefore(adventureId, date);
	}

	public Boolean existsByBookedInstructorIdAndEndAfter(Long id, Date date) {
		return adventureBookingRepo.existsByBookedInstructorIdAndEndAfter(id, date);
	}

	public AdventureBooking save(AdventureBooking adventureBooking) {
		return adventureBookingRepo.save(adventureBooking);
	}

	public void delete(AdventureBooking adventureBooking) {
		adventureBookingRepo.delete(adventureBooking);
	}

	public ArrayList<AdventureBooking> findAllByBookedAdventureIdAndStartAfterAndQuickBookingAndRegisteredUserId(
			Long id) {
		return adventureBookingRepo.findAllByBookedAdventureIdAndStartAfterAndQuickBookingAndRegisteredUserId(id,
				Calendar.getInstance().getTime(), true, 0L);
	}
}
