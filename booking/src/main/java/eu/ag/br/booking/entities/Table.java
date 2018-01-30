/**
 * 
 */
package eu.ag.br.booking.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import eu.ag.br.booking.common.StatusType;
import lombok.Setter;

/**
 * @author MOwsians
 *
 */
@Entity
@Setter
public class Table {

	private Long id;
	private StatusType status;

	@Id
	public Long getId() {
		return id;
	}

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	public StatusType getStatus() {
		return status;
	}


}
