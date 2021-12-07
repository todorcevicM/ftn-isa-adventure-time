package isa.adventuretime.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RequestForAdmin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	private Long requesterId;
	private HeadEntityEnum forType;
	private String userRegistrationReason;

	public RequestForAdmin() {
	}

	public RequestForAdmin(Long requesterId, HeadEntityEnum forType, String userRegistrationReason) {
		this.requesterId = requesterId;
		this.forType = forType;
		this.userRegistrationReason = userRegistrationReason;
	}

}
