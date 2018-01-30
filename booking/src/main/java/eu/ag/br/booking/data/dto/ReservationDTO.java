/**
 * 
 */
package eu.ag.br.booking.data.dto;

import java.util.Date;

import eu.ag.br.booking.common.StatusType;
import lombok.Setter;

/**
 * @author MOwsians
 *
 */
@Setter
public class ReservationDTO {

	private Long id;
	private Date startDate;
	private Date endDate;
	private String owner;
	private TableDTO table;
	private Long numberOfPeople;
	private StatusType status;
	
	public Long getId() {
		return id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getOwner() {
		return owner;
	}

	public TableDTO getTable() {
		return table;
	}

	public StatusType getStatus() {
		return status;
	}

	public Long getNumberOfPeople() {
		return numberOfPeople;
	}
	@Override
	public String toString() {
		return new StringBuilder(this.getClass().getSimpleName())
				.append("[")
					.append("id=").append(id)
					.append(", fromDate=").append(startDate)
					.append(", toDate=").append(endDate)
					.append(", owner=").append(owner)
					.append(", table=").append(table)
				.append("]")
					.toString();
	}
	
}
