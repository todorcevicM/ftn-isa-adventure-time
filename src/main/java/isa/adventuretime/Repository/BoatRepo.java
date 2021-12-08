package isa.adventuretime.Repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.Boat;

@Repository
public interface BoatRepo extends JpaRepository<Boat, Long> {

	public ArrayList<Boat> findAll();

	public Optional<Boat> findById(Long id);

	public ArrayList<Boat> findAllByOwnerId(Long id);

	public ArrayList<Boat> findAllByIdIn(ArrayList<Long> ids);
}