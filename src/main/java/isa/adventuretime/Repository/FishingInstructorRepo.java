package isa.adventuretime.Repository;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.FishingInstructor;

@Repository
public interface FishingInstructorRepo extends JpaRepository<FishingInstructor, Long> {

	public FishingInstructor getOne(Long id);

	public FishingInstructor findByEmail(String email);

	public FishingInstructor findByEmailHash(int code);

	public ArrayList<FishingInstructor> findAllByAuthenticatedAndDenied(Boolean authenticated, Boolean denied);




	@Query(value = 
		"select sum(sumed) from"+
		"((select sum(price_per_day) as sumed from adventure_booking, adventure "+
		"where adventure_booking.booked_adventure_id = adventure.id and adventure.instructor_id = ?3 and price = 0 and end > ?2 and start < ?1) " +
		"Union all "+
		"(select sum(price) as sumed from adventure_booking, adventure "+
		" where adventure_booking.booked_adventure_id = adventure.id and adventure.instructor_id = ?3 and price != 0 and end > ?2 and start < ?1))profit"	
		, nativeQuery = true )
		public float reportProfit(Date today, Date inPast, Long id);


}
