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
public class Boat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String type;
	private double length;
	private int engineNumber;
	private int enginePower;
	private int maxSpeed;

	private String navigationEquipment;
	private String equipment;

	private String address;
	private double geoLng;
	private double geoLat;

	private String promoDescription;
	@Column(columnDefinition = "integer default 0")
	private int percentTakenIfCancelled;
	private String rules;
	private double pricePerDay;
	private Date reservationStart;
	private Date reservationEnd;
	private String priceAndInfo;
	private int maxUsers;

	private Long ownerId;

	private Boolean hidden;
}
