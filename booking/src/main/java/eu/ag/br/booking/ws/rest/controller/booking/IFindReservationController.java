package eu.ag.br.booking.ws.rest.controller.booking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import eu.ag.br.booking.swagger.SwaggerAnnotations;
import eu.ag.br.booking.ws.rest.controller.booking.common.IBaseController;
import eu.ag.br.booking.ws.rest.response.FindReservationResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author MOwsians
 *
 */
public interface IFindReservationController extends IBaseController {
	
	@GetMapping(ALL)
	@ApiOperation(value = SwaggerAnnotations.FIND_ALL_RESERVATIONS, 
	  			  notes = SwaggerAnnotations.FIND_ALL_RESERVATIONS_DESCRIPTION)
	@ApiResponses({ 
	@ApiResponse(code = 200, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_200_MESSAGE),
	@ApiResponse(code = 500, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_500_MESSAGE) 
	})
	ResponseEntity<FindReservationResponse> findAll();
	
	@GetMapping(ALL_DEL)
	@ApiOperation(value = SwaggerAnnotations.FIND_ALL_DEL_RESERVATIONS, 
	  			  notes = SwaggerAnnotations.FIND_ALL_DEL_RESERVATIONS_DESCRIPTION)
	@ApiResponses({ 
	@ApiResponse(code = 200, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_200_MESSAGE),
	@ApiResponse(code = 500, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_500_MESSAGE) 
	})
	ResponseEntity<FindReservationResponse> findDelAll();
	
	@GetMapping(ALL_ACTIVE)
	@ApiOperation(value = SwaggerAnnotations.FIND_ALL_ACTIVE_RESERVATIONS, 
	  			 notes = SwaggerAnnotations.FIND_ALL_ACTIVE_RESERVATIONS_DESCRIPTION)
	@ApiResponses({ 
	@ApiResponse(code = 200, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_200_MESSAGE),
	@ApiResponse(code = 500, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_500_MESSAGE) 
	})
	ResponseEntity<FindReservationResponse> findActiveAll();
}
