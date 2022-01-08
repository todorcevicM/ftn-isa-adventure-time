package isa.adventuretime.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Entity.Revision;

@Repository
public interface RevisionRepo extends JpaRepository<Revision, Long> {

	public Revision findByBookingIdAndType(Long bookingId, HeadEntityEnum type);

	public ArrayList<Revision> findAll(); 

	public ArrayList<Revision> findAllByDeniedAndApproved(Boolean denied, Boolean approved);

	@Query(value = "select sum(rating)/count(rating) from revision where type = ? and main_entity_id = ?", nativeQuery = true)
    public int getScore(int type, int mainEntityId);


}
