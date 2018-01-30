/**
 * 
 */
package eu.ag.br.booking.service;

import java.util.Date;
import java.util.List;

import eu.ag.br.booking.common.ReservationStatusType;
import eu.ag.br.booking.data.dto.UpdatedReservation;
import eu.ag.br.booking.entities.Reservation;

/**
 * @author MOwsians
 *
 */
public interface ReservationService {

	Reservation save(Reservation reservation);
	List<Long> getAllIds();
	List<Reservation> getAllReservations();
	
	/**
	 * @param id
	 * @return
	 */
	List<Reservation> findReservationsByTableId(Long tableId);
	Long findDurningReservationIdByTableId(Long tableId);
	Integer markReservationDeletedByTableId(Long tableId);
	Integer markReservationDeleted(Long reservationId);
	Integer changeStatus(Long reservationId, ReservationStatusType status);
	Integer changeStatusByTable(Long tableId, ReservationStatusType status);
	
	/**
	 * @return
	 */
	List<Reservation> getAllActiveReservations();
	List<Reservation> getAllDeletedReservations();
	Long findTableIdByReservationId(Long reservationId);
	UpdatedReservation changeOwner(String ownerName, Long reservationId);
	UpdatedReservation updatePeople(Long reservationId, Long numberOfPeople);
	Integer changeStartDateReservation(Long reservationId, Date changedStartDate);
	Integer changeEndDateReservation(Long reservationId, Date changedEndDate);
	UpdatedReservation changeTableForReservation(Long reservationId, Long numberOfTable);
	Integer updateReservationDate(Long reservationId, Date changedStartDate, Date changedEndDate);
}
