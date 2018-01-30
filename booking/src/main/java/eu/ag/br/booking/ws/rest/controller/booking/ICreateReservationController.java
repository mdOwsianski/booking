package eu.ag.br.booking.ws.rest.controller.booking;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;

import eu.ag.br.booking.swagger.SwaggerAnnotations;
import eu.ag.br.booking.ws.rest.controller.booking.common.IBaseController;
import eu.ag.br.booking.ws.rest.response.CreateReservationResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author MOwsians
 *
 */
public interface ICreateReservationController extends IBaseController {
	
	@PutMapping(PEOPLE_WITH_PATH_NUMBER_OF_PEOPLE)
	@ApiOperation(value = SwaggerAnnotations.BOOK_FIRST_EMPTY_TABLE_FOR_PEOPLE, 
	              notes = SwaggerAnnotations.BOOK_FIRST_EMPTY_TABLE_FOR_PEOPLE_DESCRIPTION)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_200_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_500_MESSAGE) 
	})
	ResponseEntity<CreateReservationResponse> bookFirstEmptyTableForPeople(Long numberOfPeople);
	
	@PutMapping(TABLE_WITH_PATH_NUMBER_OF_TABLE_PEOPLE_WITH_NUMBER_OF_PEOPLE)
	@ApiOperation(value = SwaggerAnnotations.BOOK_TABLE_FOR_PEOPLE, 
	              notes = SwaggerAnnotations.BOOK_TABLE_FOR_PEOPLE_DESCRIPTION)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_200_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_500_MESSAGE) 
	})
	ResponseEntity<CreateReservationResponse> bookTable( Long numberOfTable,Long numberOfPeople);
	
	@PutMapping(TABLE_PATH_NUMBER_OF_TABLE_PEOPLE_PATH_NUMBER_OF_PEOPLE_DATE)
	@ApiOperation(value = SwaggerAnnotations.BOOK_TABLE_DATE, 
	              notes = SwaggerAnnotations.BOOK_TABLE_DATE_DESCRIPTION)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_200_MESSAGE),
		@ApiResponse(code = 500, message = SwaggerAnnotations.VALIDATE_PATH_STATUS_500_MESSAGE) 
	})
	ResponseEntity<CreateReservationResponse> bookTableForDate(Long numberOfTable,Long numberOfPeople, 
														  Date fromDate, Date toDate);
}
