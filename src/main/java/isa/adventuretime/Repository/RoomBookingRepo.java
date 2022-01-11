package isa.adventuretime.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.RoomBooking;
import java.util.Date;

@Repository
public interface RoomBookingRepo extends JpaRepository<RoomBooking, Long> {
	public ArrayList<RoomBooking> findAllByBookedRoomId(Long id);

	public ArrayList<RoomBooking> findAllByRegisteredUserId(Long id);

	public ArrayList<RoomBooking> findAllByRegisteredUserIdAndStartAfter(Long id, Date date);

	public ArrayList<RoomBooking> findAllByRegisteredUserIdAndEndBefore(Long id, Date date);

	public ArrayList<RoomBooking> findAllByRegisteredUserIdAndEndAfter(Long id, Date date);

	public ArrayList<RoomBooking> findAllByCottageIdAndEndBefore(Long id, Date date);

	public Boolean existsByCottageId(Long id);

	public Boolean existsByCottageIdAndEndAfter(Long id, Date date);

	public Boolean existsByIdAndEndAfter(Long id, Date date);

	public Boolean existsByRegisteredUserIdAndStartAfter(Long id, Date date);

	public Boolean existsByBookedRoomIdAndStartAfter(Long id, Date date);

	// public Boolean
	// existsByBookedRoomIdAndStartBetweenOrBookedRoomIdAndEndBetween(Long id, Date
	// dateStart1, Date dateEnd1, Long id2, Date dateStart2, Date dateEnd2);
	@Query(value = "select * from room_booking where booked_room_id = ? and id not in" +
			"(select id from room_booking where end < ? OR start > ? )", nativeQuery = true)
	public ArrayList<RoomBooking> findBadBookings(Long id, Date start, Date end);

	public ArrayList<RoomBooking> findAllByBookedRoomIdAndStartAfterAndQuickBookingAndRegisteredUserId(Long id,
			Date now,
			Boolean quickBooking, Long id2);

	public ArrayList<RoomBooking> findAllByCottageIdAndStartAfterAndQuickBookingAndRegisteredUserId(Long cottageId,
			Date now, Boolean quickBooking, Long registeredUserId);

	public ArrayList<RoomBooking> findAllByCottageIdAndEndAfterAndQuickBookingAndRegisteredUserId(Long cottageId,
			Date now, Boolean quickBooking, Long registeredUserId);
}
