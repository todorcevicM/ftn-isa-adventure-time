package isa.adventuretime.Entity;

import java.util.Date;

import javax.persistence.Column;
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
public class Cottage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String address;
	private double geoLng;
	private double geoLat;
	private String promoDescription; // prethodno samo description

	private String rules;
	private double pricePerDay;
	private String priceAndInfo;
	// Cottage nema percentTakenIfCancelled

	private Date reservationStart;
	private Date reservationEnd;
	private int maxUsers;

	private Long ownerId;

	@Column(columnDefinition = "boolean default false")
	private Boolean hidden;

}
