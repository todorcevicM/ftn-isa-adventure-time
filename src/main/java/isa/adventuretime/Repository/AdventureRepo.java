package isa.adventuretime.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.Adventure;

@Repository
public interface AdventureRepo extends JpaRepository<Adventure, Long> {
	public ArrayList<Adventure> findAll();

	public ArrayList<Adventure> findAllByInstructorId(Long id);
}
