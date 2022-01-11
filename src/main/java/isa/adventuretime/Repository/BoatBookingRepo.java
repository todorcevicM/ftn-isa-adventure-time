package isa.adventuretime.Repository;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.BoatBooking;

@Repository
public interface BoatBookingRepo extends JpaRepository<BoatBooking, Long> {
	public ArrayList<BoatBooking> findAllByBookedBoatId(Long id);

	public ArrayList<BoatBooking> findAllByRegisteredUserId(Long id);

	public ArrayList<BoatBooking> findAllByRegisteredUserIdAndEndBefore(Long id, Date end);

	public ArrayList<BoatBooking> findAllByRegisteredUserIdAndEndAfter(Long id, Date end);

	public Boolean existsByBookedBoatIdAndEndAfter(Long id, Date date);

	public Boolean existsByRegisteredUserIdAndStartAfter(Long id, Date date);

	public Boolean existsByBookedBoatIdAndStartAfter(Long id, Date date);

	public ArrayList<BoatBooking> findAllByBookedBoatIdAndEndBefore(Long id, Date date);

	// public Boolean
	// existsByBookedBoatIdAndStartBetweenOrBookedBoatIdAndEndBetween(Long id, Date
	// dateStart1, Date dateEnd1, Long id2, Date dateStart2, Date dateEnd2);
	@Query(value = "select * from boat_booking where booked_boat_id = ? and id not in" +
			"(select id from boat_booking where end < ? OR start > ? )", nativeQuery = true)
	public ArrayList<BoatBooking> findBadBookings(Long id, Date start, Date end);

	public ArrayList<BoatBooking> findAllByBookedBoatIdAndStartAfterAndQuickBookingAndRegisteredUserId(Long id,
			Date date, Boolean quickBooking, Long id2);

	public ArrayList<BoatBooking> findAllByBookedBoatIdAndEndAfterAndQuickBookingAndRegisteredUserId(
			Long id, Date date, Boolean quickBooking, Long id2);
}
