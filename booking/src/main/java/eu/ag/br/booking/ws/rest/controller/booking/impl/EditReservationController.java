package eu.ag.br.booking.ws.rest.controller.booking.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.ag.br.booking.common.BRAGLogger;
import eu.ag.br.booking.data.updaters.ReservationUpdater;
import eu.ag.br.booking.service.ReservationService;
import eu.ag.br.booking.ws.rest.controller.booking.IEditReservationController;
import eu.ag.br.booking.ws.rest.controller.booking.common.IRequestMappings;
import eu.ag.br.booking.ws.rest.response.EditReservationResponse;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author MOwsians
 *
 */
@RestController
@RequestMapping(IRequestMappings.API_VERSION_EDIT)
public class EditReservationController implements IEditReservationController{

	private static BRAGLogger logger = BRAGLogger.newInstance(EditReservationController.class);
	
	@Autowired
	ReservationService reservationService;
	
	@Override
	public ResponseEntity<EditReservationResponse> addPeopleToReservation(
										@ApiParam(value = "Number of people", required = true)
											@PathVariable Long numberOfPeople, 
										@ApiParam(value = "Reservation's id")
											@PathVariable("reservationId") Long reservationId) {
		
		logger.info(String.format(" add new people %d to reservation %d", numberOfPeople, reservationId));
		
		ResponseEntity<EditReservationResponse> responseEntityEditBookingResponse = ReservationUpdater
																					.create(reservationService, reservationId)
																						.updatePeople(numberOfPeople);
		
		String message = String.format("Parameter has been%s changed. ",
									obtainPostfixMessage(responseEntityEditBookingResponse.getStatusCode()));
		
		logger.info(message);
		
		return responseEntityEditBookingResponse;
	}

	@Override
	public ResponseEntity<EditReservationResponse> changeDateReservation(
			@ApiParam(value = "Reservation's id")
				@PathVariable("reservationId") Long reservationId, 
			@ApiParam(value = "put the start date in format dd-MM-yy-HH:mm", required = false)
				@RequestParam(value="start", required=false) @DateTimeFormat(pattern = "dd-MM-yy-HH:mm") Date changedStartDate, 
			@ApiParam(value = "put end date in format dd-MM-yy-HH:mm", required = false)
				@RequestParam(value="end", required=false) @DateTimeFormat(pattern = "dd-MM-yy-HH:mm") Date changedEndDate) {
		
		logger.info(String.format(" Change date for reservation %d", reservationId));
		
		ResponseEntity<EditReservationResponse> responseEditBookingResponse = ReservationUpdater
																				.create(reservationService, reservationId)
																					.updateReservationDate(changedStartDate, changedEndDate);
		
		String message = String.format("Date has been%s changed. ", 
										obtainPostfixMessage(responseEditBookingResponse.getStatusCode()));
		logger.info(message);
		
		return responseEditBookingResponse;
	}

	@Override
	public ResponseEntity<EditReservationResponse> changeOwnerReservation(@ApiParam(value = "Reservation's id")
											@PathVariable("reservationId") Long reservationId, 
									   @ApiParam(value = "owner's name")
											@RequestParam("name") String ownerName) {
		
		logger.info(String.format(" Change owner %s for reservation %d", ownerName, reservationId));
		
		ResponseEntity<EditReservationResponse> responseEditBookingResponse = ReservationUpdater
																			.create(reservationService, reservationId)
																				.updateOwner(reservationId, ownerName);
		
		String message = String.format("Owner has been%s changed.. ", 
									obtainPostfixMessage(responseEditBookingResponse.getStatusCode()));
		logger.info(message);
		
		return responseEditBookingResponse;
	}
	
	@Override
	public ResponseEntity<EditReservationResponse> changeTableForReservation(@ApiParam(value = "Reservation's id")
											@PathVariable("reservationId") Long reservationId, 
										  @ApiParam(value = "Number of table.")
											@RequestParam("number") Long numberOfTable) {
		
		logger.info(String.format(" Change table %d for reservation %d", numberOfTable, reservationId));
		
		ResponseEntity<EditReservationResponse> responseEditBookingResponse = new ResponseEntity<EditReservationResponse>(HttpStatus.NOT_FOUND);
		responseEditBookingResponse = ReservationUpdater.create(reservationService, reservationId).updateTable(numberOfTable);
		
		String message = String.format("The table has been%s changed. ",
								 obtainPostfixMessage(responseEditBookingResponse.getStatusCode()));
		logger.info(message);
		
		return responseEditBookingResponse;
	}
	
	public String obtainPostfixMessage( HttpStatus statusCode) {
		return HttpStatus.OK.equals(statusCode) ? " " : " not";
	}

}
