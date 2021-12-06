package isa.adventuretime.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.RoomBooking;

@Repository
public interface RoomBookingRepo extends JpaRepository<RoomBooking, Long> {
	ArrayList<RoomBooking> findAllByBookedRoomId(Long id);

	ArrayList<RoomBooking> findAllByBookedRegisteredUserId(Long id);
}
