package isa.adventuretime.DTO;

import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Entity.RequestForAdmin;
import isa.adventuretime.Entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnauthenticatedUserDTO extends User {
	private String requestText;

	public UnauthenticatedUserDTO() {

	}

	public UnauthenticatedUserDTO(User user, RequestForAdmin requestForAdmin, HeadEntityEnum type) {
		this.updateWithUser(user);
		this.requestText = requestForAdmin.getRequestText();
		this.email = user.getEmail();
		this.setUserType(type.toString());
	}
}
