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
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int numberOfBeds;
	private Long cottageId;

	private Boolean hidden;

	public Room() {
	}

	public Room(int numberOfBeds, Long cottageId) {
		this.numberOfBeds = numberOfBeds;
		this.cottageId = cottageId;
	}
}
