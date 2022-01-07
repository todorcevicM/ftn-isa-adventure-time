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
public class Revision {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private HeadEntityEnum type;
	private Long bookingId;

	private Boolean revised;
	private String revision;
	private float rating;

	private Boolean approved;

	public Revision() {
		this.revised = false;
		this.approved = false;
	}

	public Revision(HeadEntityEnum type, Long bookingId, String revision, float rating) {
		this.type = type;
		this.bookingId = bookingId;
		this.revision = revision;
		this.rating = rating;
		this.revised = true;
		this.approved = false;
	}

	public Revision(HeadEntityEnum type, Long bookingId) {
		this.type = type;
		this.bookingId = bookingId;
		this.revised = false;
		this.approved = false;
	}
}