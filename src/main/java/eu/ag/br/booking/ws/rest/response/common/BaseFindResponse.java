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
public class BaseFindResponse {
	
	private String responseMessage;
	private BaseActionBookingException actionBookingException;
}
