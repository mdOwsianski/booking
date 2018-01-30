package eu.ag.br.booking.ws.rest.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

import eu.ag.br.booking.ws.rest.response.common.BaseActionReservationResponse;

/**
 * 
 * @author MOwsians
 *
 */
public class EditReservationResponse extends BaseActionReservationResponse implements IBaseActionReservationResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5066666539581616513L;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
