/**
 * 
 */
package eu.ag.br.booking.common;

import java.util.function.Function;

import eu.ag.br.booking.entities.Owner;
import eu.ag.br.booking.entities.Reservation;
import eu.ag.br.booking.entities.Table;
import eu.ag.br.booking.ws.rest.response.dto.find.FindOwnerDTO;
import eu.ag.br.booking.ws.rest.response.dto.find.FindReservationDTO;
import eu.ag.br.booking.ws.rest.response.dto.find.FindTableDTO;

/**
 * @author MOwsians
 *
 */
public class FindBookingFunction {

	public static Function<Reservation, FindReservationDTO> CONVERT_RESERVATION_TO_FIND_BOOK_TO = 
			new Function<Reservation, FindReservationDTO>() {

		@Override
		public FindReservationDTO apply(Reservation t) {
			return ObjectConverters.toFindBookingTO(t);
		}
	};
	
	
	public static Function<Table, FindTableDTO> CONVERT_TABLE_TO_FIND_TABLE_BOOK_TO = 
			new Function<Table, FindTableDTO>() {

		@Override
		public FindTableDTO apply(Table arg0) {
			return ObjectConverters.toFindTableBookingDTO(arg0);
		}
	};
	
	public static Function<Owner, FindOwnerDTO> CONVERT_OWNER_TO_FIND_OWNER_DTO = 
			new Function<Owner, FindOwnerDTO>() {

		@Override
		public FindOwnerDTO apply(Owner arg0) {
			return ObjectConverters.toFindOwnerDTO(arg0);
		}
	};
	
	public static Function<Owner, FindOwnerDTO> CONVERT_TABLE_TO_FIND_TABLE_DTO = 
			new Function<Owner, FindOwnerDTO>() {

		@Override
		public FindOwnerDTO apply(Owner arg0) {
			return ObjectConverters.toFindOwnerDTO(arg0);
		}
	};
	
}
