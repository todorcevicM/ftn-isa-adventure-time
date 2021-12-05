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
public class Adventure {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String address;
	private double geoLng;
	private double geoLat;
	private String promoDescription; // prethodno samo description
	private Long instructorId;
	private String instructorBio;

	private String equipment; // ovo mozda prebaciti u termin avanture

	private String rules;
	private String priceAndInfo;
	private int percentTakenIfCancelled;

	private String location;
	private int maxUsers;
	private double pricePerDay;

}
