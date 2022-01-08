package isa.adventuretime.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.DTO.PastRoomBookingRevisionDTO;
import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Entity.Revision;
import isa.adventuretime.Entity.RoomBooking;
import isa.adventuretime.Repository.RevisionRepo;
import isa.adventuretime.Repository.RoomBookingRepo;
import java.util.Date;

@Service
public class RoomBookingService {
	@Autowired
	private RoomBookingRepo roomBookingRepo;

	@Autowired
	private RevisionRepo revisionRepo;

	public  RoomBooking getById(Long id) {
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
			pastBooking = new PastRoomBookingRevisionDTO(booking, revision);
			pastBookings.add(pastBooking);
		}

		return pastBookings;
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

	public RoomBooking save(RoomBooking roomBooking){
		return roomBookingRepo.save(roomBooking);
	}
}
