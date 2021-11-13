package isa.adventuretime.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Room {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numberOfBeds;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cottage_id", nullable = false)
    private Cottage cottage;
    
}
