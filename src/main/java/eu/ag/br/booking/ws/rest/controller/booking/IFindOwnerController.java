package eu.ag.br.booking.ws.rest.controller.booking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import eu.ag.br.booking.swagger.SwaggerAnnotations;
import eu.ag.br.booking.ws.rest.controller.booking.common.IBaseController;
import eu.ag.br.booking.ws.rest.response.FindOwnerResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author MOwsians
 *
 */
public interface IFindOwnerController extends IBaseController {
	
	@GetMapping(ALL)
	@ApiOperation(value = SwaggerAnnotations.FIND_ALL_RESERVATIONS, 
	  			  notes = SwaggerAnnotations.FIND_ALL_RESERVATIONS_DESCRIPTION)
	@ApiResponses({ 
	@ApiResponse(code = 200, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_200_MESSAGE),
	@ApiResponse(code = 500, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_500_MESSAGE) 
	})
	ResponseEntity<FindOwnerResponse> findAll();

}
