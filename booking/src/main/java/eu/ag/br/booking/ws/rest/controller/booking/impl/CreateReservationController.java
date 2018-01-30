package eu.ag.br.booking.ws.rest.controller.booking.impl;

import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.ag.br.booking.common.BRAGLogger;
import eu.ag.br.booking.common.ObjectResponseConverters;
import eu.ag.br.booking.data.BookingProvider;
import eu.ag.br.booking.data.dto.BookingTableDTO;
import eu.ag.br.booking.service.OwnerService;
import eu.ag.br.booking.service.ReservationService;
import eu.ag.br.booking.service.TableService;
import eu.ag.br.booking.ws.rest.controller.booking.ICreateReservationController;
import eu.ag.br.booking.ws.rest.controller.booking.common.IRequestMappings;
import eu.ag.br.booking.ws.rest.response.CreateReservationResponse;
import eu.ag.br.booking.ws.rest.response.exceptions.BaseActionResponseExceptionType;
import io.swagger.annotations.ApiParam;
/**
 * 
 * @author MOwsians
 *
 */
@RestController
@RequestMapping(IRequestMappings.API_VERSION_ADD)
public class CreateReservationController implements ICreateReservationController {

	private static BRAGLogger logger = BRAGLogger.newInstance(CreateReservationController.class);
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	TableService tableService;
	
	@Autowired
	OwnerService ownerService;
	
	@Override
	public ResponseEntity<CreateReservationResponse> bookFirstEmptyTableForPeople(@ApiParam(value = "Number of table", required = true)
																		   		@PathVariable Long numberOfPeople) {

		
		logger.info("  Booking first empty table for people " + numberOfPeople);
		
		BookingTableDTO createdAddBookingTableTO = BookingTableDTO.createForFirstEmptyTable(numberOfPeople);
		ResponseEntity<CreateReservationResponse> responseEntity = execute(createdAddBookingTableTO);
		
		return responseEntity;
	}

	@Override
	public ResponseEntity<CreateReservationResponse> bookTable(@ApiParam(value = "Number of table", required = true)
															@PathVariable Long numberOfTable, 
														@ApiParam(value = "Number of people", required = true)
															@PathVariable Long numberOfPeople) {
		
		String message = new StringBuilder("Booking table. Given parameters're:   [Table:").append(numberOfTable)
									.append( " for people: ").append(numberOfPeople)
									.append("]").toString();
		logger.info(message);
		
		BookingTableDTO createdAddBookingTableTO = BookingTableDTO.createForPeople(numberOfPeople,numberOfTable);
		ResponseEntity<CreateReservationResponse> responseEntity = execute(createdAddBookingTableTO);
		
		return responseEntity;
	}

	@Override
	public ResponseEntity<CreateReservationResponse> bookTableForDate(
											@ApiParam(value = "Number of table", required = true)
												@PathVariable Long numberOfTable, 
											@ApiParam(value = "Number of people", required = true)
												@PathVariable Long numberOfPeople, 
											@ApiParam(value = "put from date in format dd-MM-yy-HH:mm")
												@RequestParam(value = "start", required = false) @DateTimeFormat(pattern = "dd-MM-yy-HH:mm") Date startDate,
											@ApiParam(value = "put to date in format dd-MM-yy-HH:mm")
												@RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "dd-MM-yy-HH:mm")Date endDate) {
		
		logger.info(" Booking table for period " + numberOfTable + " " + numberOfPeople + " " + startDate + " " + endDate);
		
		BookingTableDTO createdAddBookingTableTO = BookingTableDTO
														.create(numberOfPeople, numberOfTable,startDate, endDate);
		ResponseEntity<CreateReservationResponse> responseEntity = execute(createdAddBookingTableTO);
		
		return responseEntity;
	}

	private ResponseEntity<CreateReservationResponse> execute(BookingTableDTO createdAddBookingTableTO) {
		
		ResponseEntity<CreateReservationResponse> responseEntity = null;
		try {

			
			if (NumberUtils.LONG_ONE > createdAddBookingTableTO.getNumberOfPeople()){
				throw new IllegalArgumentException("Number of people cannot less then 1");
			}
			
			BookingProvider bookingProvider = BookingProvider
												.create(reservationService, tableService, 
														ownerService, createdAddBookingTableTO);
			
			logger.info(bookingProvider.getReservation());

			responseEntity = ObjectResponseConverters.toResponseEntity(bookingProvider.getReservation());
			
		}  catch (IllegalArgumentException e1) {
			
			responseEntity = ObjectResponseConverters.toReservationExceptionResponse(createdAddBookingTableTO, 
														BaseActionResponseExceptionType.BAD_PARAMETERS, 
														Optional.of("Number of people cannot less then 1"));
			
			logger.error(responseEntity);
			
		}catch (Exception e) {
			responseEntity = ResponseEntity.unprocessableEntity().build();
		}
		
		return responseEntity;
	}

}
