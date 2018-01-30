/**
 * 
 */
package eu.ag.br.booking.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Setter;

/**
 * @author MOwsians
 *
 */
@Setter
@Entity
public class Owner {

	private Long id;
	private String name;
	private List<Reservation> reservations;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	@Column(name ="owner_name")
	public String getName() {
		return name;
	}

	@OneToMany(mappedBy="owner", 
				cascade = CascadeType.ALL, 
				fetch=FetchType.LAZY)
	public List<Reservation> getReservations() {
		return reservations;
	}
	
}
