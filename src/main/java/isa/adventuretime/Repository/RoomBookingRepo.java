package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.RoomBooking;

@Repository
public interface RoomBookingRepo extends JpaRepository<RoomBooking, Long>{
    
}
