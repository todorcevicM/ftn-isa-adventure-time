package isa.adventuretime.DTO;

import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Entity.RequestForAdmin;
import isa.adventuretime.Entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnauthenticatedUserDTO extends User {
	private Long id;
	private String userRegistrationReason;

	public UnauthenticatedUserDTO() {

	}

	public UnauthenticatedUserDTO(User user, RequestForAdmin requestForAdmin, HeadEntityEnum type, Long id) {
		this.id = id;
		this.updateWithUser(user);
		this.userRegistrationReason = requestForAdmin.getUserRegistrationReason();
		this.email = user.getEmail();
		this.setUserType(type.toString());
	}
}
