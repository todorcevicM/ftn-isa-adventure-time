
package isa.adventuretime.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.DeletionRequest;
import isa.adventuretime.Entity.HeadEntityEnum;

@Repository
public interface DeletionRequestRepo extends JpaRepository<DeletionRequest, Long> {

	public void delete(DeletionRequest deletionRequest);

	public Optional<DeletionRequest> findByForTypeAndRequesterId(HeadEntityEnum forType, Long requesterId);

	public DeletionRequest getByForTypeAndRequesterId(HeadEntityEnum forType, Long requesterId);
}
