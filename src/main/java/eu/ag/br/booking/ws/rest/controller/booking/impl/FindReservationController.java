package eu.ag.br.booking.ws.rest.controller.booking.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.ag.br.booking.common.BRAGLogger;
import eu.ag.br.booking.common.ObjectResponseConverters;
import eu.ag.br.booking.service.FindService;
import eu.ag.br.booking.ws.rest.controller.booking.IFindReservationController;
import eu.ag.br.booking.ws.rest.controller.booking.common.IRequestMappings;
import eu.ag.br.booking.ws.rest.response.FindReservationResponse;
import eu.ag.br.booking.ws.rest.response.dto.find.FindReservationDTO;

/**
 * 
 * @author MOwsians
 *
 */
@RestController
@RequestMapping(IRequestMappings.API_VERSION_FIND_RESERVATION)
public class FindReservationController implements IFindReservationController {

	@Autowired
	FindService findService;
	
	private static BRAGLogger logger = BRAGLogger.newInstance(FindReservationController.class);

	@Override
	public ResponseEntity<FindReservationResponse> findAll() {
		
		logger.info(" Find all Reservations ");
		List<FindReservationDTO> findDelReservations = findService.findDeleteReservations();
		List<FindReservationDTO> findAllActiveReservations = findService.findActiveReservations();
		
		ResponseEntity<FindReservationResponse> responseEntity = ObjectResponseConverters
																	.toFindReservationResponse(findDelReservations, findAllActiveReservations);
		
		logger.info(responseEntity);
		
		return responseEntity;
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.ws.rest.controller.booking.IFindReservationController#findDelAll()
	 */
	@Override
	public ResponseEntity<FindReservationResponse> findDelAll() {
		
		logger.info(" Find all  deleted Reservations ");
		
		List<FindReservationDTO> findDelReservations = findService.findDeleteReservations();
		ResponseEntity<FindReservationResponse> responseEntity = ObjectResponseConverters
																	.toFindReservationResponse(findDelReservations, null);
		
		logger.info(responseEntity);
		
		return responseEntity;
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.ws.rest.controller.booking.IFindReservationController#findActiveAll()
	 */
	@Override
	public ResponseEntity<FindReservationResponse> findActiveAll() {
		
		logger.info(" Find all  active Reservations ");
		
		List<FindReservationDTO> findAllActiveReservations = findService.findActiveReservations();
		ResponseEntity<FindReservationResponse> responseEntity = ObjectResponseConverters
																	.toFindReservationResponse(null, findAllActiveReservations);
		
		logger.info(responseEntity);
		
		return responseEntity;
	}

}
