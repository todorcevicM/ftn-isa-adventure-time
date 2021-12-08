package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.BusinessConstants;

@Repository
public interface BusinessConstantsRepo extends JpaRepository<BusinessConstants, Long> {

	public BusinessConstants getById(Long id);

	public BusinessConstants getByName(String name);
}
