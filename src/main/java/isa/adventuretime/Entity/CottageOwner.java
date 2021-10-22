package isa.adventuretime.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CottageOwner extends User{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

    //lista vikendica koju poseduje

}
