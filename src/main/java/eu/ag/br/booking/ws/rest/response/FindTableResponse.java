package eu.ag.br.booking.ws.rest.response;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import eu.ag.br.booking.ws.rest.response.common.BaseFindResponse;
import eu.ag.br.booking.ws.rest.response.dto.find.FindTableDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author MOwsians
 *
 */
public class FindTableResponse extends BaseFindResponse implements IBaseActionReservationResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3440141244661228582L;
	@Setter @Getter private List<FindTableDTO> emptyTables;
	@Setter @Getter private List<FindTableDTO> reservedTables;
	@Setter @Getter private List<FindTableDTO> busyTables;
	
	
	public FindTableResponse() {
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}




	

}
