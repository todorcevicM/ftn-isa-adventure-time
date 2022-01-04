
package isa.adventuretime.Repository;

import isa.adventuretime.Entity.DeletionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletionRequestRepo extends JpaRepository<DeletionRequest, Long> {

}
