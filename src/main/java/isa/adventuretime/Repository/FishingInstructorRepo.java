package isa.adventuretime.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.FishingInstructor;

@Repository
public interface FishingInstructorRepo extends JpaRepository<FishingInstructor, Long> {

	public FishingInstructor getOne(Long id);

	public FishingInstructor findByEmail(String email);

	public FishingInstructor findByEmailHash(int code);

	public ArrayList<FishingInstructor> findAllByAuthenticatedAndDenied(Boolean authenticated, Boolean denied);

}
