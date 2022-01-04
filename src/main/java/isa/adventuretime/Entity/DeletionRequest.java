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
public class DeletionRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	private Long requesterId;
	private HeadEntityEnum forType;
	private String reason;
	private String name;
	private String lastname;

	public DeletionRequest() {
	}

	public DeletionRequest(Long requesterId, HeadEntityEnum forType, String reason, String name, String lastname) {
		this.requesterId = requesterId;
		this.forType = forType;
		this.reason = reason;
		this.name = name;
		this.lastname = lastname;
	}
}
