package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.adventuretime.Entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long>{
    
}
