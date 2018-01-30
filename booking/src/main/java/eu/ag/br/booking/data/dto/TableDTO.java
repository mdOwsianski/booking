/**
 * 
 */
package eu.ag.br.booking.data.dto;

import eu.ag.br.booking.common.StatusType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author MOwsians
 *
 */
@Setter
@Getter
public class TableDTO {
	
	private Long id;
	private StatusType status;
	
	public TableDTO(Long id, StatusType status) {
		super();
		this.id = id;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return new StringBuilder(this.getClass().getSimpleName())
				.append("[")
					.append("id").append("=").append( id )
					.append(", status=").append(status)
				.append("]").toString();
	}
	
}
