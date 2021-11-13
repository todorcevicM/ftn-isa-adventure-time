package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.Booking;
@Repository
public interface BookingRepo extends JpaRepository<Booking, Long>{
    
}
