package eu.ag.br.booking.ws.rest.response;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import eu.ag.br.booking.ws.rest.response.common.BaseFindResponse;
import eu.ag.br.booking.ws.rest.response.dto.find.FindReservationDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author MOwsians
 *
 */
@Setter
@Getter
public class FindReservationResponse  extends BaseFindResponse implements IBaseActionReservationResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2107493846059209770L;
	
	private List<FindReservationDTO> durningReservations;
	private List<FindReservationDTO> deletedReservations;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
