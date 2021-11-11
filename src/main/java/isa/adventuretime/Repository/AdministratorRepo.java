package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.adventuretime.Entity.Administrator;

public interface AdministratorRepo extends JpaRepository<Administrator, Long> {
	Administrator getOne(Long id);
	Administrator findByEmailHash(int code);

}