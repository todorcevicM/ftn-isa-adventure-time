package isa.adventuretime.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.BoatBooking;

@Repository
public interface BoatBookingRepo extends JpaRepository<BoatBooking, Long> {
	public ArrayList<BoatBooking> findAllByBookedBoatId(Long id);

	public ArrayList<BoatBooking> findAllByRegisteredUserId(Long id);
}
