package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.AdventureBooking;

@Repository
public interface AdventureBookingRepo extends JpaRepository<AdventureBooking, Long> {
    
}
