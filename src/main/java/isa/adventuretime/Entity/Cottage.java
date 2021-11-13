package isa.adventuretime.Entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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

	private Date reservationStart;
	private Date reservationEnd;
	private int maxUsers;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private CottageOwner cottageOwner;

	@OneToMany(mappedBy = "cottage", fetch = FetchType.LAZY,
	cascade = CascadeType.ALL)
	private Set<Room> rooms = new HashSet<>();

}
