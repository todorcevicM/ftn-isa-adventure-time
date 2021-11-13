package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.Room;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long>{
    
}
