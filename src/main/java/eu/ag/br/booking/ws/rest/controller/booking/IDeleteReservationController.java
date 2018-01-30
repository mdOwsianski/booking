package eu.ag.br.booking.ws.rest.controller.booking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import eu.ag.br.booking.swagger.SwaggerAnnotations;
import eu.ag.br.booking.ws.rest.controller.booking.common.IControllerMappingsValues;
import eu.ag.br.booking.ws.rest.response.DeleteReservationResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author MOwsians
 *
 */
public interface IDeleteReservationController extends IControllerMappingsValues {

	@DeleteMapping(value = RESERVATION)
	@ApiOperation(value = SwaggerAnnotations.DELETE_RESERVATION, 
	    	      notes = SwaggerAnnotations.DELETE_RESERVATION_DESCRIPTION)
	@ApiResponses({ 
	@ApiResponse(code = 200, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_200_MESSAGE),
	@ApiResponse(code = 500, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_500_MESSAGE) 
	})
	ResponseEntity<DeleteReservationResponse> deleteReservetion(Long reservationId,Long tableId);
}
