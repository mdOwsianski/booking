package eu.ag.br.booking.common;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author MOwsians
 *
 */
public enum StatusType {
	
	
	UKNOW,
	RESERVED,
	BUSY,
	EMPTY
	;
	
	private static List<StatusType> USED_STATUSES = Arrays.asList(RESERVED, BUSY);
	public static boolean isUsed(StatusType status) {
		return USED_STATUSES.contains(status);
	}
	
	public static boolean isNotUsed(StatusType status) {
		
		return !isUsed(status);
	}
	
	public static StatusType mappedFrom(ReservationStatusType reservationStatusType) {
		
		StatusType status = null;

		switch (reservationStatusType) {
		case DELETED:
			status = EMPTY;
			break;
		case DURING:
			status = BUSY;
			break;
		case UKNOW:
		default:
			status = UKNOW;
			break;
		}
		
		return status;
	}

}
