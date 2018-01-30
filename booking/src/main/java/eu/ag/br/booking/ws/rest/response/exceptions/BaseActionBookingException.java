package eu.ag.br.booking.ws.rest.response.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author MOwsians
 *
 */
@Setter
@Getter
public class BaseActionBookingException {

	private String exceptionMessage;
	private HttpStatus reason;

}
