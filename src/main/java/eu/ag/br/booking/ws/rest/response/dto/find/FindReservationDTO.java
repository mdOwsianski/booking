package eu.ag.br.booking.ws.rest.response.dto.find;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author MOwsians
 *
 */

public class FindReservationDTO  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6083186009041648434L;
	@Setter @Getter private Long id;
	@Setter @Getter private Date fromDate;
	@Setter @Getter private Date toDate;
	@Setter @Getter private String ownerName;
	@Setter @Getter private Long tableId;
}
