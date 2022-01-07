package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Entity.Revision;

@Repository
public interface RevisionRepo extends JpaRepository<Revision, Long> {

	public Revision findByBookingIdAndType(Long bookingId, HeadEntityEnum type);

}
