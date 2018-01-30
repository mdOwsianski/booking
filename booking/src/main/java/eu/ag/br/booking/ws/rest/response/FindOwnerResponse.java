package eu.ag.br.booking.ws.rest.response;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import eu.ag.br.booking.ws.rest.response.common.BaseFindResponse;
import eu.ag.br.booking.ws.rest.response.dto.find.FindOwnerDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author MOwsians
 *
 */
public class FindOwnerResponse extends BaseFindResponse implements IBaseActionReservationResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2010911933688444969L;
	@Setter @Getter private List<FindOwnerDTO> owners;
	
	public FindOwnerResponse(List<FindOwnerDTO> owners) {
		super();
		this.owners = owners;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
