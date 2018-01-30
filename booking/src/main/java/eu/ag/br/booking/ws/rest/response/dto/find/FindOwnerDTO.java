package eu.ag.br.booking.ws.rest.response.dto.find;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author MOwsians
 *
 */
@Setter
@Getter
public class FindOwnerDTO {
	
	private Long id;
	private String name;
	
	@Override
	public String toString() {
		return "FindOwnerDTO [id=" + id + ", name=" + name + "]";
	}
}
