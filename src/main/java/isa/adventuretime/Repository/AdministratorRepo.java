package isa.adventuretime.Repository;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.Administrator;

@Repository
public interface AdministratorRepo extends JpaRepository<Administrator, Long> {
	public Administrator getOne(Long id);

	public Administrator findByEmailHash(int emailHash);

	public Administrator findByEmail(String email);

	public ArrayList<Administrator> findAll();

	public ArrayList<Administrator> findAllByDeleted(Boolean a);



	@Query(value = 
		"select sum(sumed) * (select value/100 from business_constants where id = 1) from" +
		"((select sum(price_per_day) as \"sumed\" from adventure_booking, adventure where " +
		"adventure_booking.booked_adventure_id = adventure.id and price = 0 and end > ?2 and start < ?1)" +
		"Union all" +
		"(select sum(price) from adventure_booking, adventure where " +
		"adventure_booking.booked_adventure_id = adventure.id and price != 0 and end > ?2 and start < ?1)" +
		"Union all" +
		"(select sum(price_per_day) as \"sumed\" from boat_booking, boat where " +
		"boat_booking.booked_boat_id = boat.id and price = 0 and end > ?2 and start < ?1)" +
		"Union all" +
		"(select sum(price) from boat_booking, boat where " +
		"boat_booking.booked_boat_id = boat.id and price != 0 and end > ?2 and start < ?1)" +
		"Union all" +
		"(select sum(price_per_day) as \"sumed\" from room_booking, cottage where " +
		"room_booking.cottage_id = cottage.id and price != 0 and end > ?2 and start < ?1)" +
		"Union all" +
		"(select sum(price_per_day) as \"sumed\" from room_booking, cottage where " +
		"room_booking.cottage_id = cottage.id and price = 0 and end > ?2 and start < ?1))price", nativeQuery = true )
		public float reportProfit(Date today, Date inPast);


}