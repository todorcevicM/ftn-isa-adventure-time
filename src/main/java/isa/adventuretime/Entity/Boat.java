package isa.adventuretime.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
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

    private String navigationEquipment; //TODO da li samo sting za navigacionu opermu

    private String address;
    private double GeoLng;
    private double GeoLat;

    // kapacitet broda, jel se to odnosi na koliko je maksimalno moguce ljudi da nosi 
    // a onda maksimalni broj osoba je samo neki broj koji vlasnik bira ali taj broj mora biti manji od ovog

    private String promoDescription; // prethodno samo description
    private String rules;
    private double pricePerDay;
    private Date reservationStart;
    private Date reservationEnd;
    private Long ownerId;
    private String priceAndInfo;
    private int maxUsers;
}
