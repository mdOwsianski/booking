/**
 * 
 */
package eu.ag.br.booking.data.updaters;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.ResponseEntity;

import eu.ag.br.booking.common.ObjectConverters;
import eu.ag.br.booking.common.ObjectResponseConverters;
import eu.ag.br.booking.data.dto.UpdatedReservation;
import eu.ag.br.booking.service.ReservationService;
import eu.ag.br.booking.ws.rest.response.EditReservationResponse;
import eu.ag.br.booking.ws.rest.response.exceptions.BaseActionResponseExceptionType;

/**
 * @author MOwsians
 *
 */
public class ReservationUpdater {
	
	public static ReservationUpdater createByTableId(ReservationService reservationService, Long tableId) {
		Long reservationIdByTableId = reservationService.findDurningReservationIdByTableId(tableId);
		return new ReservationUpdater(reservationService,reservationIdByTableId);
	}
	
	public static ReservationUpdater create(ReservationService reservationService,Long reservationId) {
		return new ReservationUpdater(reservationService, reservationId);
	}

	private ReservationService reservationService;
	private Long reservationId;

	private ReservationUpdater(ReservationService reservationService, Long reservationId) {
		this.reservationService = reservationService;
		this.reservationId = reservationId;
	}
	
	private ReservationUpdater(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	public ResponseEntity<EditReservationResponse> updatePeople(Long numberOfNewPeople) {
		
		ResponseEntity<EditReservationResponse> editBookingResponse = null;
		try
		{
			if(NumberUtils.LONG_ZERO > numberOfNewPeople) {
				throw new IllegalArgumentException();
			}
			numberOfNewPeople = Objects.nonNull(numberOfNewPeople)  ? numberOfNewPeople : 0L;
			
			UpdatedReservation udatedReservation = reservationService.updatePeople(reservationId, numberOfNewPeople);
			editBookingResponse = ObjectResponseConverters.toEditReservationResponse(udatedReservation);
			
		}catch (IllegalArgumentException e) {
			
			
			UpdatedReservation updatedReservation = new UpdatedReservation();
			updatedReservation.setUpdatedRows(0);
			updatedReservation.setReservationId(reservationId);
			
			editBookingResponse = ObjectResponseConverters.toEditReservationExceptionResponse(updatedReservation, 
																		BaseActionResponseExceptionType.BAD_PARAMETERS, 
																		Optional.of(" Nummber of people cannot less then 1"));
		}
		
		return editBookingResponse;
	}
	
	public ResponseEntity<EditReservationResponse> updateReservationDate(Date startDate, Date endDate) {
		
		ReservationDateUpdateType reservationDateUpdateType = ReservationDateUpdateType
																	.getByDate(startDate, endDate);
		
		Integer updatedRows = 0;
		
		switch (reservationDateUpdateType) {
		case END:
			updatedRows = reservationService.changeEndDateReservation(reservationId, endDate);
			break;
			
		case START:
			updatedRows = reservationService.changeStartDateReservation(reservationId, startDate);
			break;
			
		case START_END:
			updatedRows = reservationService.updateReservationDate(reservationId, startDate, endDate);
			break;
			
		case UKNOW:
			break;
		}
		
		UpdatedReservation updatedReservation = ObjectConverters
													.toUpdatedReservationWithoutTableId(reservationId, updatedRows);
		ResponseEntity<EditReservationResponse> editBookingResponse = ObjectResponseConverters
																		.toEditReservationResponse(updatedReservation);

		return editBookingResponse;
	}
	
	public ResponseEntity<EditReservationResponse> updateTable(Long numberOfTable) {
		
		UpdatedReservation changeTableForReservation = reservationService
															.changeTableForReservation(reservationId, numberOfTable);
		
		ResponseEntity<EditReservationResponse> editBookingResponse = ObjectResponseConverters
																	.toEditReservationResponse(changeTableForReservation);
		
		return editBookingResponse;
	}
	
	public ResponseEntity<EditReservationResponse> updateOwner(Long reservationId, String ownerName) {
		
		UpdatedReservation updatedReservation = reservationService
															.changeOwner(ownerName, reservationId);
		
		ResponseEntity<EditReservationResponse> editBookingResponse = Objects.nonNull(updatedReservation.getReservationId())
																	  ? ObjectResponseConverters
																			  	.toEditReservationResponse(updatedReservation) 
																	  : ResponseEntity.notFound().build();
		
		return editBookingResponse;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

}
