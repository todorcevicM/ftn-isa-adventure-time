package isa.adventuretime.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BusinessConstants {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	private Double value;
	private String description;
}
