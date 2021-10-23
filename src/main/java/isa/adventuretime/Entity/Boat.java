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

    private String navigationEquipment;

    private String address;
    private double GeoLng;
    private double GeoLat;

    private String promoDescription; 
    private String rules;
    private double pricePerDay;
    private Date reservationStart;
    private Date reservationEnd;
    private Long ownerId;
    private String priceAndInfo;
    private int maxUsers;
}
