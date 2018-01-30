package eu.ag.br.booking.ws.rest.controller.booking.impl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.ag.br.booking.common.BRAGLogger;
import eu.ag.br.booking.common.ObjectResponseConverters;
import eu.ag.br.booking.data.dto.UpdatedReservation;
import eu.ag.br.booking.data.removers.ReservationRemover;
import eu.ag.br.booking.data.removers.TableRemover;
import eu.ag.br.booking.service.FindService;
import eu.ag.br.booking.service.ReservationService;
import eu.ag.br.booking.service.TableService;
import eu.ag.br.booking.ws.rest.controller.booking.IDeleteReservationController;
import eu.ag.br.booking.ws.rest.controller.booking.common.IRequestMappings;
import eu.ag.br.booking.ws.rest.response.DeleteReservationResponse;
import eu.ag.br.booking.ws.rest.response.exceptions.BaseActionBookingException;
import eu.ag.br.booking.ws.rest.response.exceptions.BaseActionResponseExceptionType;

/**
 * 
 * @author MOwsians
 *
 */
@RestController
@RequestMapping(IRequestMappings.API_VERSION_DELETE)
public class DeleteReservationController implements IDeleteReservationController{

	@Autowired
	ReservationService reservationService;
	
	@Autowired
	TableService tableService;
	
	@Autowired
	FindService findService;
	
	private static BRAGLogger logger = BRAGLogger.newInstance(DeleteReservationController.class);
	
	@Override
	public ResponseEntity<DeleteReservationResponse> deleteReservetion(@RequestParam(value="reservationId", required = false) Long reservationId, 
																   @RequestParam(value = "tableId", required = false) Long tableId) {
		
		logger.info(" delete reservation ");
		boolean isNotNullSomeOfParameters = Objects.nonNull(tableId) || Objects.nonNull(reservationId);
		ResponseEntity<DeleteReservationResponse> response = isNotNullSomeOfParameters 
															? removeReservation(reservationId, tableId) 
															: createResponseException( new NullPointerException(" One parameter is required"), 
																					  BaseActionResponseExceptionType.PARAMETERS_NULL);
		
		return response;
	}
	
	private ResponseEntity<DeleteReservationResponse> removeReservation(Long reservationId, Long tableId) {
		
		ResponseEntity<DeleteReservationResponse> response = null;
		try {
			
			
			ReservationRemover reservationRemover = Objects.isNull(reservationId) 
															? ReservationRemover.createByTableId(reservationService, tableId) 
															: ReservationRemover.create(reservationService, reservationId);
					
			UpdatedReservation updatedReservation = reservationRemover.removeReservation();

			releaseTable(reservationId, tableId);
			
			updatedReservation.setTableId(tableId);
			response = ObjectResponseConverters.toDeleteReservationResponse(updatedReservation);
			
		} catch (NullPointerException e) {
			
			e = new NullPointerException("I probably do not have that reservation. ");
			response = createResponseException(e, BaseActionResponseExceptionType.NPE);
		} catch (Exception e1) {
			response = createResponseException(e1, BaseActionResponseExceptionType.INTERNAL_ERROR);
		}
		
		return response;
	}
	
	public void releaseTable(Long reservationId, Long tableId) {
		
		if(Objects.isNull(tableId)) {
			tableId = findService.findTableIdByReservationId(reservationId);
		}
		
		TableRemover.create(tableService).remove(tableId);
	}
	
	private ResponseEntity<DeleteReservationResponse> createResponseException(Exception e, BaseActionResponseExceptionType baseActionExceptionType) {
		
		BaseActionBookingException baseActionResponseException = baseActionExceptionType.getBaseActionResponseException(Optional.of(e.getMessage()));
		DeleteReservationResponse deleteBookingResponse = new DeleteReservationResponse(baseActionResponseException);
		
		logger.error(deleteBookingResponse);
		
		return ResponseEntity.status(baseActionResponseException.getReason()).body(deleteBookingResponse);
	}
	
}
