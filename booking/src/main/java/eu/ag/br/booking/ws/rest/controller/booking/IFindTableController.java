package eu.ag.br.booking.ws.rest.controller.booking;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import eu.ag.br.booking.swagger.SwaggerAnnotations;
import eu.ag.br.booking.ws.rest.controller.booking.common.IBaseController;
import eu.ag.br.booking.ws.rest.response.FindTableResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author MOwsians
 *
 */
public interface IFindTableController extends IBaseController {
	
	@GetMapping(ALL_IDS)
	@ApiOperation(value = SwaggerAnnotations.FIND_ALL_RESERVATIONS, 
	  			  notes = SwaggerAnnotations.FIND_ALL_RESERVATIONS_DESCRIPTION)
	@ApiResponses({ 
	@ApiResponse(code = 200, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_200_MESSAGE),
	@ApiResponse(code = 500, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_500_MESSAGE) 
	})
	List<Long> findAllTableIds();
	
	@GetMapping(ALL_RESERVED)
	@ApiOperation(value = SwaggerAnnotations.FIND_ALL_RESERVATIONS, 
	  			  notes = SwaggerAnnotations.FIND_ALL_RESERVATIONS_DESCRIPTION)
	@ApiResponses({ 
	@ApiResponse(code = 200, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_200_MESSAGE),
	@ApiResponse(code = 500, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_500_MESSAGE) 
	})
	ResponseEntity<FindTableResponse> findTablesReserved();
	
	@GetMapping(ALL_EMPTY)
	@ApiOperation(value = SwaggerAnnotations.FIND_ALL_RESERVATIONS, 
	  			  notes = SwaggerAnnotations.FIND_ALL_RESERVATIONS_DESCRIPTION)
	@ApiResponses({ 
	@ApiResponse(code = 200, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_200_MESSAGE),
	@ApiResponse(code = 500, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_500_MESSAGE) 
	})
	ResponseEntity<FindTableResponse> findTablesEmpty();
	
	@GetMapping(ALL_BUSY)
	@ApiOperation(value = SwaggerAnnotations.FIND_ALL_RESERVATIONS, 
	  			  notes = SwaggerAnnotations.FIND_ALL_RESERVATIONS_DESCRIPTION)
	@ApiResponses({ 
	@ApiResponse(code = 200, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_200_MESSAGE),
	@ApiResponse(code = 500, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_500_MESSAGE) 
	})
	ResponseEntity<FindTableResponse> findTablesBusy();
	
	@GetMapping(ALL)
	@ApiOperation(value = SwaggerAnnotations.FIND_ALL_RESERVATIONS, 
	  			  notes = SwaggerAnnotations.FIND_ALL_RESERVATIONS_DESCRIPTION)
	@ApiResponses({ 
	@ApiResponse(code = 200, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_200_MESSAGE),
	@ApiResponse(code = 500, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_500_MESSAGE) 
	})
	ResponseEntity<FindTableResponse> findTablesAll();
	
	@GetMapping(TABLE)
	@ApiOperation(value = SwaggerAnnotations.FIND_ALL_RESERVATIONS, 
	  			  notes = SwaggerAnnotations.FIND_ALL_RESERVATIONS_DESCRIPTION)
	@ApiResponses({ 
	@ApiResponse(code = 200, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_200_MESSAGE),
	@ApiResponse(code = 500, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_500_MESSAGE) 
	})
	Long findTableIdByReservationId(Long reservationId);

	
}
