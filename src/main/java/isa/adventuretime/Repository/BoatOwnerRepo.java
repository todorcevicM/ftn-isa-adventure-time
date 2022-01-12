package isa.adventuretime.Repository;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.BoatOwner;

@Repository
public interface BoatOwnerRepo extends JpaRepository<BoatOwner, Long> {

	public BoatOwner getOne(Long id);

	public BoatOwner findByEmail(String email);

	public BoatOwner findByEmailHash(int code);

	public ArrayList<BoatOwner> findAllByAuthenticatedAndDenied(Boolean authenticated, Boolean denied);



	@Query(value = 
		"select sum(sumed) from " +
		"((select sum(price_per_day) as sumed from boat_booking, boat where " + 
		" boat_booking.booked_boat_id = boat.id and price = 0 and owner_id = ?3 and end > ?2 and start < ?1)" +	
		"Union all" +
		"(select sum(price) from boat_booking, boat where "+
		"boat_booking.booked_boat_id = boat.id and price != 0 and owner_id = ?3 and end > ?2 and start < ?1))profit"	
		, nativeQuery = true )
		public float reportProfit(Date today, Date inPast, Long id);

}
