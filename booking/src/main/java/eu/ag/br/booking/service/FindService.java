package eu.ag.br.booking.service;

import java.util.List;

import eu.ag.br.booking.ws.rest.response.dto.find.FindOwnerDTO;
import eu.ag.br.booking.ws.rest.response.dto.find.FindReservationDTO;
import eu.ag.br.booking.ws.rest.response.dto.find.FindTableDTO;

/**
 * 
 * @author MOwsians
 *
 */
public interface FindService {
	
	/** Reservation **/
	List<Long> findAllReservationIds();
	List<FindReservationDTO> findAllReservations();
	List<FindReservationDTO> findDeleteReservations();
	List<FindReservationDTO> findActiveReservations();
	
	/** Table **/
	List<Long> findAllTableIds();
	List<FindTableDTO> findReservedTables();
	List<FindTableDTO> findEmptyTables();
	List<FindTableDTO> findBusyTables();
	Long findTableIdByReservationId(Long reservationId);
	List<FindOwnerDTO> findAllOwners();
}
