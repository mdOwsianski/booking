/**
 * 
 */
package eu.ag.br.booking.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import eu.ag.br.booking.common.ReservationStatusType;
import lombok.Setter;

/**
 * @author MOwsians
 *
 */
@Entity
@Setter
public class Reservation {

	private Long id;
	private Date startDate;
	private Date endDate;
	private Owner owner;
	private Long  tableId;
	private Long numberOfPeople;
	private ReservationStatusType reservationStatus;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}

	@Column(name = "end_date")
	public Date getEndDate() {
		return endDate;
	}

	@ManyToOne()
	@JoinColumn(name="owner")
	public Owner getOwner() {
		return owner;
	}

	@Column(name = "number_people")
	public Long getNumberOfPeople() {
		return numberOfPeople;
	}

	@Column(name = "table_id")
	public Long getTableId() {
		return tableId;
	}

	@Column(name = "reservation_status")
	@Enumerated(EnumType.STRING)
	public ReservationStatusType getReservationStatus() {
		return reservationStatus;
	}

}
