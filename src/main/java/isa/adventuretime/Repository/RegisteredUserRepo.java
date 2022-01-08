package isa.adventuretime.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import isa.adventuretime.Entity.RegisteredUser;

@Repository
public interface RegisteredUserRepo extends JpaRepository<RegisteredUser, Long> {

	public RegisteredUser findByEmail(String email);

	public RegisteredUser findByEmailHash(int code);

	public Long findIdByEmailHash(int emailHash);

	@Query(value = "select * from registered_user where id = (select distinct registered_user_id from boat_booking, boat where boat_booking.booked_boat_id = boat.id and boat.owner_id = ?)", nativeQuery  = true)
	public ArrayList<RegisteredUser> getAllUsersOfBoatOwner(Long boatOwnerId);
}
