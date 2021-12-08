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
public class BusinessConstants {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	// Svaka konstanta je opisana u description, kao na primer
	// Percentage taken on successful reservation
	private Double value;
	private String description;
	private String name;

	public BusinessConstants() {

	}

	public BusinessConstants(Double value, String description, String name) {
		this.value = value;
		this.description = description;
		this.name = name;
	}
}
