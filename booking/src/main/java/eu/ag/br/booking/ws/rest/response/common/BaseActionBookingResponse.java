package eu.ag.br.booking.ws.rest.response.common;

import eu.ag.br.booking.ws.rest.response.exceptions.BaseActionBookingException;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author MOwsians
 *
 */
@Setter
@Getter
public class BaseActionBookingResponse {

	private String responseMessage;
	private BaseActionBookingException actionBookingException;
	
	private Integer updatedRows;
	private Long reservationId;
	private Long tableId;

	public BaseActionBookingResponse(String responseMessage, BaseActionBookingException actionBookingException,
			Integer updatedRows, Long reservationId, Long tableId) {
		super();
		this.responseMessage = responseMessage;
		this.actionBookingException = actionBookingException;
		this.updatedRows = updatedRows;
		this.reservationId = reservationId;
		this.tableId = tableId;
	}
	
	public BaseActionBookingResponse() {
	}

	@Override
	public String toString() {
		return "BaseActionBookingResponse [responseMessage=" + responseMessage + ", actionBookingException="
				+ actionBookingException + "]";
	}


	
	
	
}
