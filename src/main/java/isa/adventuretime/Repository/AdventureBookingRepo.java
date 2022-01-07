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

	public Boolean existsByBookedInstructorIdAndEndAfter(Long id, Date date);

	public Boolean existsByBookedInstructorId(Long id);

	public Boolean existsByRegisteredUserIdAndStartAfter(Long id, Date date);

	public Boolean existsByBookedInstructorIdAndStartAfter(Long id, Date date);

	public Boolean existsByBookedAdventureIdAndStartAfter(Long id, Date date);

	public Boolean existsByBookedInstructorIdAndStartBetweenOrBookedInstructorIdAndEndBetween(Long id, Date dateStart1, Date dateEnd1, Long id2, Date dateStart2, Date dateEnd2);


}
