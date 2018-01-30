package eu.ag.br.booking.ws.rest.response.common;

import eu.ag.br.booking.common.StatusType;

/**
 * 
 * @author MOwsians
 *
 */
public enum FindTableType {
	
	
	RESERVED,
	BUSY,
	EMPTY,
	ALL
	;
	
	

	@SuppressWarnings("incomplete-switch")
	public static FindTableType byStatusType(StatusType status) {
		
		FindTableType type = ALL;
		
		switch (status) {
		case BUSY:
			type = BUSY;
			break;
		case EMPTY:
			type = EMPTY;
			break;
			
		case RESERVED:
			type = RESERVED;
			break;
		}
		
		return type;
	}
}
