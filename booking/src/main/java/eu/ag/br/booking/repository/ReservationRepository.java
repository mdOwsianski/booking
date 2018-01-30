/**
 * 
 */
package eu.ag.br.booking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eu.ag.br.booking.entities.Reservation;

/**
 * @author MOwsians
 *
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	/**
	 * @param numberOfTable
	 * @return 
	 */
	@Query("select r from Reservation r where r.tableId= :tableId order by r.startDate desc")
	List<Reservation> findReservationsByTableId(@Param("tableId") Long tableId);
	
	@Query("select r from Reservation r where r.tableId= :tableId and r.reservationStatus=eu.ag.br.booking.common.ReservationStatusType.DURING")
	Reservation findDurningReservationByTableId(@Param("tableId") Long tableId);
	
	/**
	 * @param numberOfTable
	 * @return 
	 */
	@Query("select r from Reservation r where r.tableId= :tableId  and r.reservationStatus=eu.ag.br.booking.common.ReservationStatusType.DELETED order by r.startDate desc")
	List<Reservation> findDelReservationsByTableId(@Param("tableId") Long tableId);
	
	@Query("select r from Reservation r where r.reservationStatus=eu.ag.br.booking.common.ReservationStatusType.DELETED")
	List<Reservation> getDeletedReservations();
	
	@Query("select r from Reservation r where r.reservationStatus=eu.ag.br.booking.common.ReservationStatusType.DURING")
	List<Reservation> getActiveReservations();
	
	@Query("select r.id from Reservation r")
	List<Long> getAllIds();
	
	@Query("select r from Reservation r where r.tableId=:tableId and r.reservationStatus=eu.ag.br.booking.common.ReservationStatusType.DURING")
	Optional<Reservation> findActiveReservationByTableId(@Param("tableId") Long tableId);
	
	@Query("select r.id from Reservation r where r.tableId=:tableId and r.reservationStatus=eu.ag.br.booking.common.ReservationStatusType.DURING")
	Optional<Long> findActiveReservationIdByTableId(@Param("tableId") Long tableId);
	
	@Query("select r.tableId from Reservation r where r.id=:id and r.reservationStatus=eu.ag.br.booking.common.ReservationStatusType.DURING")
	Optional<Long> findTableIdByReservationId(@Param("id") Long reservationId);
}
