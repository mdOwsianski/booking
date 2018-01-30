package eu.ag.br.booking.ws.rest.response.exceptions;

import java.util.Optional;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author MOwsians
 *
 */
public enum BaseActionResponseExceptionType {
	
	NOT_FOUND,
	PARAMETER_NULL,
	PARAMETERS_NULL,
	NPE,
	INTERNAL_ERROR,
	BAD_PARAMETERS
	;
	
	public BaseActionBookingException getBaseActionResponseException(Optional<String> exceptionMessage) {
		
		BaseActionBookingException exception = null;
		HttpStatus reason = HttpStatus.NO_CONTENT;
		
		switch (this) {
		case INTERNAL_ERROR:
			reason = HttpStatus.INTERNAL_SERVER_ERROR;
			break;
		case NPE:
		case PARAMETERS_NULL:
		case PARAMETER_NULL:
			reason = HttpStatus.BAD_REQUEST;
			break;
		case BAD_PARAMETERS:
			reason = HttpStatus.BAD_REQUEST;
			break;
		case NOT_FOUND:
			reason = HttpStatus.NOT_FOUND;
			break;
		}
		
		exception = new BaseActionBookingException();
		exception.setReason(reason);
		
		if(exceptionMessage.isPresent()) {
			exception.setExceptionMessage(exceptionMessage.get());
		}
		
		return exception;
	}

}
