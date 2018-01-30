package eu.ag.br.booking.ws.rest.controller.booking.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import eu.ag.br.booking.common.BRAGLogger;
import eu.ag.br.booking.common.ObjectResponseConverters;
import eu.ag.br.booking.service.FindService;
import eu.ag.br.booking.ws.rest.controller.booking.IFindTableController;
import eu.ag.br.booking.ws.rest.controller.booking.common.IRequestMappings;
import eu.ag.br.booking.ws.rest.response.FindTableResponse;
import eu.ag.br.booking.ws.rest.response.common.FindTableType;
import eu.ag.br.booking.ws.rest.response.dto.find.FindTableDTO;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author MOwsians
 *
 */
@RestController
@RequestMapping(IRequestMappings.API_VERSION_FIND_TABLE)
public class FindTableController implements IFindTableController {

	
	@Autowired
	FindService findService;
	
	private static BRAGLogger logger = BRAGLogger.newInstance(FindTableController.class);
	
	@Override
	public List<Long> findAllTableIds() {
		List<Long> findAllTableIds = findService.findAllTableIds();
		return findAllTableIds;
	}

	@Override
	public ResponseEntity<FindTableResponse> findTablesReserved() {
		
		ResponseEntity<FindTableResponse> response = find(FindTableType.RESERVED);
		return response;
	}

	@Override
	public ResponseEntity<FindTableResponse> findTablesEmpty() {
		
		ResponseEntity<FindTableResponse> response = find(FindTableType.EMPTY);
		return response;
	}

	@Override
	public ResponseEntity<FindTableResponse> findTablesBusy() {
		
		ResponseEntity<FindTableResponse> response = find(FindTableType.BUSY);
		return response;
	}
	
	@Override
	public ResponseEntity<FindTableResponse> findTablesAll() {
		
		ResponseEntity<FindTableResponse> response = find(FindTableType.ALL);
		return response;
	}

	@Override
	public Long findTableIdByReservationId(@ApiParam(value = "Reservation's id")
											@RequestParam(value="reservationId", required=false) Long reservationId) {
		
		Long tableId = null;
		if(Objects.nonNull(reservationId)) {
			tableId = findService.findTableIdByReservationId(reservationId);
		}
		
		return tableId;
	}
	
	private ResponseEntity<FindTableResponse> find(FindTableType status) {
		
		logger.info(" Find Tables "+status.name());
		
		ResponseEntity<FindTableResponse> response = findTables(status);
		
		logger.info(response);
		
		return response;
	}
	
	private ResponseEntity<FindTableResponse> findTables(FindTableType status) {
		
		ResponseEntity<FindTableResponse> response = null;
		
		List<FindTableDTO> busyTables = Lists.newArrayList();
		List<FindTableDTO> emptyTables = Lists.newArrayList();
		List<FindTableDTO> reservedTables = Lists.newArrayList();
		
		switch (status) {
		case BUSY:
			busyTables = findService.findBusyTables();
			response = ObjectResponseConverters.toFindReservedTablesResponse(busyTables);
			break;
			
		case EMPTY:
			
			emptyTables = findService.findEmptyTables();
			response = ObjectResponseConverters.toFindReservedTablesResponse(emptyTables);
			break;
			
		case RESERVED:
			
			reservedTables = findService.findReservedTables();
			response = ObjectResponseConverters.toFindReservedTablesResponse(reservedTables);
			break;
			
		case ALL:
			
			busyTables = findService.findBusyTables();
			emptyTables = findService.findEmptyTables();
			reservedTables = findService.findReservedTables();
			
			response = ObjectResponseConverters.toFindTableResponse(emptyTables, reservedTables, busyTables);
			break;
		}
		
		return response;
	}

}
