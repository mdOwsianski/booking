package eu.ag.br.booking.data.updaters;

import java.util.Date;
import java.util.Objects;

/**
 * 
 * @author MOwsians
 *
 */
public enum ReservationDateUpdateType {
	
	UKNOW,
	START,
	END,
	START_END
	;
	
	
	public static  ReservationDateUpdateType getByDate(Date startDate, Date endDate) {
		
		ReservationDateUpdateType type = UKNOW;
		
		if (Objects.nonNull(startDate) && Objects.nonNull(endDate)) {
			type = START_END;
		} else if (Objects.nonNull(startDate) && Objects.isNull(endDate)) {
			type = START;
		} else if (Objects.isNull(startDate) && Objects.nonNull(endDate)) {
			type = END;
		}
		
		return type;
	}

}
