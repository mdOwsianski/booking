/**
 * 
 */
package eu.ag.br.booking.data.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import lombok.Setter;

/**
 * @author MOwsians
 *
 */
@Setter
public class BookingTableDTO {

	public static BookingTableDTO createForFirstEmptyTable(Long numberOfPeople) {
		return create(numberOfPeople, null, new Timestamp(new Date().getTime()), null);
	}
	
	public static BookingTableDTO createForPeople(Long numberOfPeople, Long numberOfTable) {
		return create(numberOfPeople, numberOfTable, new Timestamp(new Date().getTime()), null);
	}
	
	public static BookingTableDTO create(Long numberOfPeople, Long numberOfTable, 
										 Date startDate, Date endDate) {
		if(Objects.isNull(startDate)) startDate = new Timestamp(new Date().getTime());
		
		return new BookingTableDTO(numberOfPeople, numberOfTable, startDate, endDate, "admin");
	}
	
	private Long numberOfPeople;
	private Long numberOfTable;
	private Date startDate;
	private Date endDate;
	private LocalDateTime createDate;
	private String ownerName;

	public BookingTableDTO(Long numberOfPeople, Long numberOfTable, Date startDate, Date endDate, String ownerName) {
		super();
		this.numberOfPeople = numberOfPeople;
		this.numberOfTable = numberOfTable;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createDate = LocalDateTime.now();
		this.ownerName = ownerName;
	}

	public Long getNumberOfPeople() {
		return numberOfPeople;
	}

	public Long getNumberOfTable() {
		return numberOfTable;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public String getOwnerName() {
		return ownerName;
	}

	

}
