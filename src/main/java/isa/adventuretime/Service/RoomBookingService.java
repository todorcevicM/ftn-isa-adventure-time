package isa.adventuretime.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.DTO.*;
import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Entity.Revision;
import isa.adventuretime.Entity.RoomBooking;
import isa.adventuretime.Repository.RevisionRepo;
import isa.adventuretime.Repository.RoomBookingRepo;
import isa.adventuretime.Repository.CottageRepo;
import java.util.Date;
import java.util.Calendar;

@Service
public class RoomBookingService {
	@Autowired
	private RoomBookingRepo roomBookingRepo;

	@Autowired
	private RevisionRepo revisionRepo;

	@Autowired
	private CottageRepo cottageRepo;

	public RoomBooking getById(Long id) {
		return roomBookingRepo.getById(id);
	}

	public ArrayList<RoomBooking> findAllByBookedRoomId(Long id) {
		return roomBookingRepo.findAllByBookedRoomId(id);
	}

	public ArrayList<PastRoomBookingRevisionDTO> findAllByRegisteredUserIdAndEndBefore(Long id, Date date) {
		ArrayList<RoomBooking> bookings = roomBookingRepo.findAllByRegisteredUserIdAndEndBefore(id, date);
		ArrayList<PastRoomBookingRevisionDTO> pastBookings = new ArrayList<>();
		PastRoomBookingRevisionDTO pastBooking;

		for (RoomBooking booking : bookings) {
			Revision revision = revisionRepo.findByBookingIdAndType(booking.getId(), HeadEntityEnum.COTTAGE);
			if (revision == null) {
				revision = new Revision(HeadEntityEnum.COTTAGE, booking.getId(), booking.getCottageId());
			}
			String cottageName = cottageRepo.getById(booking.getCottageId()).getName();
			UserNameRoomBookingDTO userNameRoomBookingDTO = new UserNameRoomBookingDTO("", booking);
			ArrayList<UserNameRoomBookingDTO> UserNameRoomBookingDTOs = new ArrayList<>();
			UserNameRoomBookingDTOs.add(userNameRoomBookingDTO);
			CottageNameRoomBookingDTO cottageNameRoomBookingDTO = new CottageNameRoomBookingDTO(cottageName,
					UserNameRoomBookingDTOs);

			pastBooking = new PastRoomBookingRevisionDTO(cottageNameRoomBookingDTO, revision);
			pastBookings.add(pastBooking);
		}

		return pastBookings;
	}

	public ArrayList<CottageNameRoomBookingDTO> findAllByRegisteredUserIdAndEndAfter(Long id, Date date) {
		ArrayList<RoomBooking> bookings = roomBookingRepo.findAllByRegisteredUserIdAndEndAfter(id, date);
		ArrayList<CottageNameRoomBookingDTO> cottageNameRoomBookingDTOs = new ArrayList<>();
		CottageNameRoomBookingDTO cottageNameRoomBookingDTO;

		for (RoomBooking booking : bookings) {
			Revision revision = revisionRepo.findByBookingIdAndType(booking.getId(), HeadEntityEnum.COTTAGE);
			if (revision == null) {
				revision = new Revision(HeadEntityEnum.COTTAGE, booking.getId(), booking.getCottageId());
			}
			String cottageName = cottageRepo.getById(booking.getCottageId()).getName();
			UserNameRoomBookingDTO userNameRoomBookingDTO = new UserNameRoomBookingDTO("", booking);
			ArrayList<UserNameRoomBookingDTO> UserNameRoomBookingDTOs = new ArrayList<>();
			UserNameRoomBookingDTOs.add(userNameRoomBookingDTO);
			cottageNameRoomBookingDTO = new CottageNameRoomBookingDTO(cottageName, UserNameRoomBookingDTOs);

			cottageNameRoomBookingDTOs.add(cottageNameRoomBookingDTO);
		}

		return cottageNameRoomBookingDTOs;
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

	public RoomBooking save(RoomBooking roomBooking) {
		return roomBookingRepo.save(roomBooking);
	}

	public void delete(RoomBooking roomBooking) {
		roomBookingRepo.delete(roomBooking);
	}

	public ArrayList<RoomBooking> findAllByBookedRoomIdAndStartAfterAndQuickBookingAndRegisteredUserId(Long id) {
		return roomBookingRepo.findAllByBookedRoomIdAndStartAfterAndQuickBookingAndRegisteredUserId(id,
				Calendar.getInstance().getTime(),
				true, 0L);
	}

	public ArrayList<RoomBooking> findAllByCottageIdAndStartAfterAndQuickBookingAndRegisteredUserId(Long cottageId) {
		return roomBookingRepo.findAllByCottageIdAndStartAfterAndQuickBookingAndRegisteredUserId(cottageId,
				Calendar.getInstance().getTime(), true, 0L);
	}

	public ArrayList<RoomBooking> findAllByCottageIdAndEndAfterAndQuickBookingAndRegisteredUserId(Long cottageId) {
		return roomBookingRepo.findAllByCottageIdAndEndAfterAndQuickBookingAndRegisteredUserId(cottageId,
				Calendar.getInstance().getTime(), true, 0L);
	}

	public ArrayList<RoomBooking> findAllByBookedRoomIdAndRegisteredUserIdAndStartBeforeAndEndAfter(Long bookedRoomId, Long registeredUserId, Date start, Date end) {
		return roomBookingRepo.findAllByBookedRoomIdAndRegisteredUserIdAndStartBeforeAndEndAfter(bookedRoomId, registeredUserId, start, end);
	}
}
