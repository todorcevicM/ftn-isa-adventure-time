package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.adventuretime.Entity.Room;

public interface RoomRepo extends JpaRepository<Room, Long>{
    
}
