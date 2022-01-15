package isa.adventuretime.Repository;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.AdventureBooking;

@Repository
public interface AdventureBookingRepo extends JpaRepository<AdventureBooking, Long> {
	public ArrayList<AdventureBooking> findAllByBookedInstructorId(Long id);

	public ArrayList<AdventureBooking> findAllByRegisteredUserId(Long id);

	public ArrayList<AdventureBooking> findAllByRegisteredUserIdAndEndBefore(Long id, Date date);

	public ArrayList<AdventureBooking> findAllByRegisteredUserIdAndEndAfter(Long id, Date date);

	public ArrayList<AdventureBooking> findAllByBookedAdventureIdAndEndBefore(Long adventureId, Date date);

	public Boolean existsByBookedInstructorIdAndEndAfter(Long id, Date date);

	public Boolean existsByBookedInstructorId(Long id);

	public Boolean existsByRegisteredUserIdAndStartAfter(Long id, Date date);

	public Boolean existsByBookedInstructorIdAndStartAfter(Long id, Date date);

	public Boolean existsByBookedAdventureIdAndStartAfter(Long id, Date date);

	public Boolean existsByBookedAdventureIdAndStartBeforeAndEndAfter(Long id, Date start, Date end);

	// public Boolean
	// existsByBookedInstructorIdAndStartBetweenOrBookedInstructorIdAndEndBetween(Long
	// id, Date dateStart1, Date dateEnd1, Long id2, Date dateStart2, Date
	// dateEnd2);
	@Query(value = "select * from adventure_booking where booked_adventure_id = ? and id not in" +
			"(select id from adventure_booking where end < ? OR start > ? )", nativeQuery = true)
	public ArrayList<AdventureBooking> findBadBookings(Long id, Date start, Date end);

	public ArrayList<AdventureBooking> findAllByBookedAdventureIdAndStartAfterAndQuickBookingAndRegisteredUserId(
			Long id, Date date, Boolean quickBooking, Long id2);

	public ArrayList<AdventureBooking> findAllByBookedAdventureIdAndEndAfterAndQuickBookingAndRegisteredUserId(
			Long id, Date date, Boolean quickBooking, Long id2);

	public ArrayList<AdventureBooking> findAllByBookedAdventureIdAndStartBeforeAndEndAfter(Long adventureId, Date start,
			Date end);

}
