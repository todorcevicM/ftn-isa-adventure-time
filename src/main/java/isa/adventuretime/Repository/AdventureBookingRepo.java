package isa.adventuretime.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.AdventureBooking;

import java.util.Date;

@Repository
public interface AdventureBookingRepo extends JpaRepository<AdventureBooking, Long> {
	public ArrayList<AdventureBooking> findAllByBookedInstructorId(Long id);

	public ArrayList<AdventureBooking> findAllByRegisteredUserId(Long id);

	public ArrayList<AdventureBooking> findAllByRegisteredUserIdAndEndBefore(Long id, Date date);

	public ArrayList<AdventureBooking> findAllByRegisteredUserIdAndEndAfter(Long id, Date date);

	public ArrayList<AdventureBooking> findAllByBookedAdventureIdAndEndBefore(Long adventureId, Date date);
}
