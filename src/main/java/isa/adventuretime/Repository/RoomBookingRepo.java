package isa.adventuretime.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.RoomBooking;
import java.util.Date;

@Repository
public interface RoomBookingRepo extends JpaRepository<RoomBooking, Long> {
	public ArrayList<RoomBooking> findAllByBookedRoomId(Long id);

	public ArrayList<RoomBooking> findAllByRegisteredUserId(Long id);

	public ArrayList<RoomBooking> findAllByRegisteredUserIdAndEndBefore(Long id, Date date);

	public ArrayList<RoomBooking> findAllByRegisteredUserIdAndEndAfter(Long id, Date date);

	public ArrayList<RoomBooking> findAllByCottageIdAndEndBefore(Long id, Date date);

	public Boolean existsByCottageId(Long id);

	public Boolean existsByCottageIdAndEndAfter(Long id, Date date);

}
