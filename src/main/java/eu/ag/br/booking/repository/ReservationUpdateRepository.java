package eu.ag.br.booking.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import eu.ag.br.booking.entities.Owner;
import eu.ag.br.booking.entities.Reservation;

/**
 * 
 * @author MOwsians
 *
 */
public interface ReservationUpdateRepository extends JpaRepository<Reservation, Integer> {

	@Transactional
	@Modifying
	@Query("update Reservation r set r.reservationStatus=eu.ag.br.booking.common.ReservationStatusType.DELETED where r.tableId=:tableId")
	Integer markReservationDeletedByTableId(@Param("tableId") Long tableId);
	
	@Transactional
	@Modifying
	@Query("update Reservation r set r.reservationStatus=eu.ag.br.booking.common.ReservationStatusType.DELETED where r.id= :id ")
	Integer markReservationDeleted(@Param("id") Long reservationId);
	
	@Transactional
	@Modifying
	@Query("update Reservation r set r.reservationStatus=eu.ag.br.booking.common.ReservationStatusType.DURING where r.tableId=:tableId")
	Integer markReservationDuringByTableId(@Param("tableId") Long tableId);
	
	@Transactional
	@Modifying
	@Query("update Reservation r set r.reservationStatus=eu.ag.br.booking.common.ReservationStatusType.DURING where r.id= :id ")
	Integer markReservationDuring(@Param("id") Long reservationId);

	@Transactional
	@Modifying
	@Query("update Reservation r set r.numberOfPeople=:numberOfPeople where r.id= :id ")
	Integer updatePeopleToReservation(@Param("id") Long reservationId, @Param("numberOfPeople") Long numberOfPeople);
	
	@Transactional
	@Modifying
	@Query("update Reservation r set r.startDate=:startDate where r.id= :id ")
	Integer updateStartDateReservation(@Param("id") Long reservationId, @Param("startDate") Date changedStartDate);
	
	@Transactional
	@Modifying
	@Query("update Reservation r set r.endDate=:endDate where r.id= :id ")
	Integer updateEndDateReservation(@Param("id") Long reservationId, @Param("endDate") Date changedEndDate);
	
	@Transactional
	@Modifying
	@Query("update Reservation r set r.endDate=:endDate, r.startDate=:startDate where r.id= :id ")
	Integer updateDateReservation(@Param("id") Long reservationId,
						       @Param("startDate") Date changedStartDate, 
							   @Param("endDate") Date changedEndDate);
	
	@Transactional
	@Modifying
	@Query("update Reservation r set r.owner=:owner where r.id= :id ")
	Integer updateOwnerReservation(@Param("id") Long reservationId, @Param("owner") Owner owner);
	
	@Transactional
	@Modifying
	@Query("update Reservation r set r.tableId=:numberOfTable where r.id= :id ")
	Integer updateTableForReservation(@Param("id") Long reservationId, @Param("numberOfTable") Long numberOfTable);

}
