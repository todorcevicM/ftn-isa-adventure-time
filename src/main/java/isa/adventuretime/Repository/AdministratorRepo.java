package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.Administrator;

@Repository
public interface AdministratorRepo extends JpaRepository<Administrator, Long> {
	Administrator getOne(Long id);
	Administrator findByEmailHash(int code);

}