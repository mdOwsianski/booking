package eu.ag.br.booking.ws.rest.response;

import eu.ag.br.booking.ws.rest.response.common.BaseActionReservationResponse;
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
public class DeleteReservationResponse extends BaseActionReservationResponse implements IBaseActionReservationResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8448834767013075582L;
	
	public DeleteReservationResponse() {
		super();
	}

	public DeleteReservationResponse(BaseActionBookingException actionBookingException) {
		setActionBookingException(actionBookingException);
	}

	public DeleteReservationResponse(BaseActionReservationResponse baseActionBookingResponse) {
	}

	@Override
	public String toString() {
		return new StringBuilder(this.getClass().getSimpleName())
				.append(" Response message ").append(getResponseMessage())
				.append(" short exception").append(getActionBookingException())
				.toString();
	}
	
	
}
