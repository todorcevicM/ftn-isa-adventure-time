package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import isa.adventuretime.Entity.Person;

public interface PersonRepo extends JpaRepository<Person, Long> {
	Person getOne(Long id);
}