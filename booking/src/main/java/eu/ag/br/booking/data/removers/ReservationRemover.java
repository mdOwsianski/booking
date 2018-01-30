/**
 * 
 */
package eu.ag.br.booking.data.removers;

import java.util.Objects;

import eu.ag.br.booking.common.ObjectConverters;
import eu.ag.br.booking.data.dto.UpdatedReservation;
import eu.ag.br.booking.service.ReservationService;

/**
 * @author MOwsians
 *
 */
public class ReservationRemover {
	
	public static ReservationRemover createByTableId(ReservationService reservationService, Long tableId) {
		Long reservationIdByTableId = reservationService.findDurningReservationIdByTableId(tableId);
		return new ReservationRemover(reservationService,reservationIdByTableId);
	}
	
	public static ReservationRemover create(ReservationService reservationService,Long reservationId) {
		return new ReservationRemover(reservationService, reservationId);
	}

	private ReservationService reservationService;
	private Long reservationId;

	private ReservationRemover(ReservationService reservationService, Long reservationId) {
		this.reservationService = reservationService;
		this.reservationId = reservationId;
	}
	
	private ReservationRemover(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	public void removeByTableId(Long tableId){
		
		Long reservationId = reservationService.findDurningReservationIdByTableId(tableId);
		if(Objects.nonNull(reservationId)) {
			reservationService.markReservationDeleted(reservationId);
			this.reservationId = reservationId;
		}
	}
	
	public UpdatedReservation removeReservation() {
		
		Integer updatedRows = reservationService.markReservationDeleted(reservationId);
		return ObjectConverters.toUpdatedReservationWithoutTableId(reservationId, updatedRows);
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

}
