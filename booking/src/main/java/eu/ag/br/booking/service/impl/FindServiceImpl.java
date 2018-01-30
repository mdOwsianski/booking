package eu.ag.br.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.ag.br.booking.common.ObjectConverters;
import eu.ag.br.booking.entities.Reservation;
import eu.ag.br.booking.service.FindService;
import eu.ag.br.booking.service.OwnerService;
import eu.ag.br.booking.service.ReservationService;
import eu.ag.br.booking.service.TableService;
import eu.ag.br.booking.ws.rest.response.dto.find.FindOwnerDTO;
import eu.ag.br.booking.ws.rest.response.dto.find.FindReservationDTO;
import eu.ag.br.booking.ws.rest.response.dto.find.FindTableDTO;

@Service

public class FindServiceImpl implements FindService {

	@Autowired
	ReservationService reservationService;
	
	@Autowired
	TableService tableSerivce;
	
	@Autowired
	OwnerService ownerService;

	@Override
	public List<Long> findAllReservationIds() {
		
		List<Long> allIds = reservationService.getAllIds();
		
		return allIds;
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.FindService#findAllReservations()
	 */
	@Override
	public List<FindReservationDTO> findAllReservations() {
		
		List<Reservation> allReservations = reservationService.getAllReservations();
		List<FindReservationDTO> findBookingTOs = ObjectConverters.toFindBookingTOs(allReservations);
		
		return findBookingTOs;
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.FindService#findReservationDelAll()
	 */
	@Override
	public List<FindReservationDTO> findDeleteReservations() {
		
		List<Reservation> deletedReservations = reservationService.getAllDeletedReservations();
		List<FindReservationDTO> findBookingTOs = ObjectConverters.toFindBookingTOs(deletedReservations);
		
		return findBookingTOs;
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.FindService#findReservationActiveAll()
	 */
	@Override
	public List<FindReservationDTO> findActiveReservations() {
		
		List<Reservation> activeReservations = reservationService.getAllActiveReservations();
		List<FindReservationDTO> findBookingTOs = ObjectConverters.toFindBookingTOs(activeReservations);
		
		return findBookingTOs;
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.FindService#findAllTableds()
	 */
	@Override
	public List<Long> findAllTableIds() {
		return tableSerivce.findAllIds();
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.FindService#findReservedTables()
	 */
	@Override
	public List<FindTableDTO> findReservedTables() {
		return tableSerivce.findReservedTables();
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.FindService#findEmptyTables()
	 */
	@Override
	public List<FindTableDTO> findEmptyTables() {
		return tableSerivce.findEmptyTables();
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.FindService#findBusyTables()
	 */
	@Override
	public List<FindTableDTO> findBusyTables() {
		return tableSerivce.findBusyTables();
	}

	@Override
	public Long findTableIdByReservationId(Long reservationId) {
		return reservationService.findTableIdByReservationId(reservationId);
	}

	@Override
	public List<FindOwnerDTO> findAllOwners() {
		 List<FindOwnerDTO> ownerDTOs = ownerService.findAll();
		 
		return ownerDTOs;
	}

}
