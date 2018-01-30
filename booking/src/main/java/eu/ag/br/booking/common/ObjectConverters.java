/**
 * 
 */
package eu.ag.br.booking.common;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import eu.ag.br.booking.data.dto.ObtainedOwner;
import eu.ag.br.booking.data.dto.ReservationDTO;
import eu.ag.br.booking.data.dto.TableDTO;
import eu.ag.br.booking.data.dto.UpdatedReservation;
import eu.ag.br.booking.entities.Owner;
import eu.ag.br.booking.entities.Reservation;
import eu.ag.br.booking.entities.Table;
import eu.ag.br.booking.ws.rest.response.dto.find.FindOwnerDTO;
import eu.ag.br.booking.ws.rest.response.dto.find.FindReservationDTO;
import eu.ag.br.booking.ws.rest.response.dto.find.FindTableDTO;

/**
 * @author MOwsians
 *
 */
public class ObjectConverters {
	
	/**
	 * Create entity Owner.
	 * 
	 * @param ownerName
	 * @param reservation
	 * @return converted object.
	 */
	public static Owner createOwner(String ownerName, Reservation reservation) {
		
		Owner createdOwner = new Owner();
		createdOwner.setName(ownerName);
		createdOwner.setReservations(Arrays.asList(reservation));
		
		return createdOwner;
	}
	
	/**
	 *  Create simple DTO ReservationDTO. 
	 *  
	 * @param reservation
	 * @return converted object.
	 */
	public static ReservationDTO toReservationTO(Reservation reservation) {
		
		ReservationDTO createdReservationTO = new ReservationDTO();
		
		createdReservationTO.setStartDate(reservation.getStartDate());
		createdReservationTO.setId(reservation.getId());
		createdReservationTO.setOwner(reservation.getOwner().getName());
		
		TableDTO tableTO = toTableTO(reservation.getTableId(), StatusType.BUSY);
		createdReservationTO.setTable(tableTO);
		
		createdReservationTO.setEndDate(reservation.getEndDate());
		createdReservationTO.setNumberOfPeople(reservation.getNumberOfPeople());
		
		return createdReservationTO;
	}
	
	/**
	 * 
	 * @param tableId
	 * @param status
	 * @return
	 */
	public static TableDTO toTableTO(Long tableId, StatusType status) {
		return new TableDTO(tableId, status);
	}

	/**
	 * @param obtainedOwnerByName
	 * @param action
	 * @return converted object. 
	 */
	public static ObtainedOwner toObtainedOwnerTO(Owner obtainedOwnerByName, ActionType action) {
		return new ObtainedOwner(obtainedOwnerByName, action);
	}
	
	/**
	 * 
	 * @param  entity reservation
	 * @return converted object.
	 */
	public static FindReservationDTO toFindBookingTO(Reservation reservation) {
		
		FindReservationDTO to = new FindReservationDTO();
		
		to.setFromDate(reservation.getStartDate());
		to.setId(reservation.getId());
		to.setOwnerName(reservation.getOwner().getName());
		to.setTableId(reservation.getTableId());
		to.setToDate(reservation.getEndDate());
		
		return to;
	}
	
	/**
	 * 
	 * @param entities reservations
	 * @return
	 */
	public static List<FindReservationDTO> toFindBookingTOs(List<Reservation> reservations) {
		return reservations.stream()
				.map(FindBookingFunction.CONVERT_RESERVATION_TO_FIND_BOOK_TO)
				 .collect(Collectors.toList());
	}
	
	/**
	 * 
	 * @param entities tables
	 * @return
	 */
	public static List<FindTableDTO> toFindTableBookingDTOs(List<Table> tables) {
		return tables.stream()
				.map(FindBookingFunction.CONVERT_TABLE_TO_FIND_TABLE_BOOK_TO)
				 .collect(Collectors.toList());
	}
	
	/**
	 * 
	 * @param Entity table
	 * @return
	 */
	public static FindTableDTO toFindTableBookingDTO(Table table) {
		
		FindTableDTO dto = new FindTableDTO();
		dto.setStatus(table.getStatus().name());
		dto.setId(table.getId());
		
		return dto;
	}
	
	/**
	 * Create UpdatedReservation. 
	 * 
	 * @param reservationId
	 * @param updatedRows
	 * @return converted object.
	 */
	public static UpdatedReservation toUpdatedReservation(Long numberOfTable, Long reservationId, Integer updatedRows) {
		
		UpdatedReservation pdatedReservation = new UpdatedReservation();
		
		pdatedReservation.setTableId(numberOfTable);
		pdatedReservation.setReservationId(reservationId);
		pdatedReservation.setUpdatedRows(updatedRows);
		
		return pdatedReservation;
	}
	
	/**
	 * Create UpdatedReservation. 
	 * 
	 * @param reservationId
	 * @param updatedRows
	 * @return converted object.
	 */
	public static UpdatedReservation toUpdatedReservationWithoutTableId(Long reservationId, Integer updatedRows) {
		return toUpdatedReservation(null, reservationId, updatedRows);
	}
	
	/**
	 * 
	 * Simple converter to FindOwnerDTO.
	 * 
	 * @param owners
	 * @return converted object.
	 */
	public static List<FindOwnerDTO> toFindOwnerDTOs(List<Owner> owners) {
		
		if(CollectionUtils.isEmpty(owners))owners = Arrays.asList();
		List<FindOwnerDTO> convertedOwners = owners.stream()
														.map(FindBookingFunction.CONVERT_OWNER_TO_FIND_OWNER_DTO)
															.collect(Collectors.toList());
		
		return convertedOwners;
	}
	
	/**
	 * 
	 * Simple converter to FindOwnerDTO.
	 * 
	 * @param owner
	 * @return converted object.
	 */
	public static FindOwnerDTO toFindOwnerDTO(Owner owner) {
		
		FindOwnerDTO ownerDTO = new FindOwnerDTO();
		
		ownerDTO.setId(owner.getId());
		ownerDTO.setName(owner.getName());
		
		return ownerDTO;
	}

}
