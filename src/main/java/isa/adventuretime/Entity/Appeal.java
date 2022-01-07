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
public class Appeal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private HeadEntityEnum type;
	private Long bookingId;
    private String appeal;

	public Appeal() {

	}

    public Appeal(HeadEntityEnum type, Long bookingId, String appeal) {
        this.type = type;
        this.bookingId = bookingId;
        this.appeal = appeal;
    }
	
}