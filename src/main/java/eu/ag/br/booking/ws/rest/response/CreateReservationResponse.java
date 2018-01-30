package eu.ag.br.booking.ws.rest.response;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import eu.ag.br.booking.common.StatusType;
import eu.ag.br.booking.ws.rest.response.common.BaseActionReservationResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * Simple response's object.
 * 
 * @author codeprime
 *
 */
@Setter
@Getter
public class CreateReservationResponse extends BaseActionReservationResponse implements IBaseActionReservationResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1589243006236913479L;

	private Date startBookingDate;
	private Date endBookingDate;
	private Long numberOfPeople;
	private StatusType status;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
