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
public class Adventure {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String name;
    private String address;
    private double GeoLng;
    private double GeoLat; 
    private String description;
    private Long instructorId;
    private String instructorBio;

    private String equipment; //ovo mozda prebaciti u termin avanture
    private String extraService;

    private String rules;
    private int maxUsers;
    private double pricePerDay;
    private String priceAndInfo;
    private int percentTakenIfCanceled;
}
