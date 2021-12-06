package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.FishingInstructor;

@Repository
public interface FishingInstructorRepo extends JpaRepository<FishingInstructor, Long> {

	public FishingInstructor findByEmail(String email);

	public FishingInstructor findByEmailHash(int code);

}
