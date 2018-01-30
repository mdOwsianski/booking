package eu.ag.br.booking.ws.rest.controller.booking;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import eu.ag.br.booking.swagger.SwaggerAnnotations;
import eu.ag.br.booking.ws.rest.controller.booking.common.IControllerMappingsValues;
import eu.ag.br.booking.ws.rest.response.EditReservationResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author MOwsians
 *
 */
public interface IEditReservationController extends IControllerMappingsValues {

	@PostMapping(EDIT_ADD_PEOPLE_TO_RESERVATION)
	@ApiOperation(value = SwaggerAnnotations.ADD_PEOPLE_TO_RESERVATION, 
            	  notes = SwaggerAnnotations.ADD_PEOPLE_TO_RESERVATION_DESCRIPTION)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_200_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_500_MESSAGE) 
	})
	ResponseEntity<EditReservationResponse> addPeopleToReservation(Long numberOfPeople, Long reservationId);
	
	@PostMapping(CHANGE_RESERVATION_OWNER)
	@ApiOperation(value = SwaggerAnnotations.CHANGE_OWNER, 
            	  notes = SwaggerAnnotations.CHANGE_OWNER_DESCRIPTION)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_200_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_500_MESSAGE) 
	})
	ResponseEntity<EditReservationResponse> changeOwnerReservation(Long reservationId, String ownerName);
	
	@PostMapping(CHANGE_RESERVATION_TABLE)
	@ApiOperation(value = SwaggerAnnotations.CHANGE_TABLE, 
            	  notes = SwaggerAnnotations.CHANGE_TABLE_DESCRIPTION)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_200_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_500_MESSAGE) 
	})
	ResponseEntity<EditReservationResponse> changeTableForReservation(Long reservationId, Long numberOfTable);
	
	@PostMapping(CHANGE_RESERVATION_DATE)
	@ApiOperation(value = SwaggerAnnotations.CHANGE_DATE_RESERVATION, 
            	  notes = SwaggerAnnotations.CHANGE_DATE_RESERVATION_DESCRIPTION)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_200_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_500_MESSAGE) 
	})
	ResponseEntity<EditReservationResponse> changeDateReservation(Long reservationId, Date changedStartDate, Date changedStartDate2);
}
