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
// Ovaj property je dodat jer bez njega Spring baca ogromnu gresku,
// ovo je radjeno i na web-u.
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Administrator extends User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	// Main Admin ima id == 1

}
