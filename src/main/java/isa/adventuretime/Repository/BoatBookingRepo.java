package isa.adventuretime.Repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import isa.adventuretime.Entity.BoatBooking;
import java.util.Date;

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

}
