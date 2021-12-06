package isa.adventuretime.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.AdventureBooking;

@Repository
public interface AdventureBookingRepo extends JpaRepository<AdventureBooking, Long> {
	public ArrayList<AdventureBooking> findAllByBookedInstructorId(Long id);

	public ArrayList<AdventureBooking> findAllByRegisteredUserId(Long id);
}
