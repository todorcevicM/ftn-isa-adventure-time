package isa.adventuretime.Repository;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.CottageOwner;

@Repository
public interface CottageOwnerRepo extends JpaRepository<CottageOwner, Long> {

	public CottageOwner getOne(Long id);

	public CottageOwner findByEmail(String email);

	public CottageOwner findByEmailHash(int code);

	public ArrayList<CottageOwner> findAllByAuthenticatedAndDenied(Boolean authenticated, Boolean denied);


	@Query(value = 
		"select sum(sumed) from ((select sum(price_per_day) as sumed from room_booking, cottage where room_booking.cottage_id = cottage.id " +
		" and price = 0 and cottage.owner_id = ?3 and end > ?2 and start < ?1)" +	
		"Union all" +
		"(select sum(price) as sumed from room_booking, cottage where room_booking.cottage_id = cottage.id " +
		" and price != 0 and cottage.owner_id = ?3 and end > ?2 and start < ?1))profit"
		, nativeQuery = true )
		public float reportProfit(Date today, Date inPast, Long id);
}
