package isa.adventuretime.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.Room;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
	Optional<Room> findById(Long id);
}
