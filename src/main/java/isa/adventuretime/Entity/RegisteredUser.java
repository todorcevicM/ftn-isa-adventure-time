package isa.adventuretime.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RegisteredUser extends User{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
}
