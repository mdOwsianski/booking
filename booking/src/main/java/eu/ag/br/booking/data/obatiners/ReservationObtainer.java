/**
 * 
 */
package eu.ag.br.booking.data.obatiners;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import eu.ag.br.booking.common.ReservationStatusType;
import eu.ag.br.booking.entities.Owner;
import eu.ag.br.booking.entities.Reservation;
import eu.ag.br.booking.entities.Table;
import eu.ag.br.booking.service.ReservationService;

/**
 * @author MOwsians
 *
 */
public class ReservationObtainer {
	
	public static ReservationObtainer create(ReservationService reservationService) {
		return new ReservationObtainer(reservationService);
	}
	
	private ReservationService reservationService;

	private ReservationObtainer(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	public List<Reservation> findReservationsByTableId(Long tableId) {
		return reservationService.findReservationsByTableId(tableId);
	}
	
	public Long findActiveReservationIdByTableId(Long tableId) {
		return reservationService.findDurningReservationIdByTableId(tableId);
	}
	
	public Reservation createReservation(Date fromDate, Long numberOfPeople, Table table, Owner owner) {
		
		Reservation reservation = new Reservation();
		
		reservation.setStartDate(fromDate);
		reservation.setNumberOfPeople(numberOfPeople);
		reservation.setTableId(table.getId());
		reservation.setOwner(owner);
		reservation.setReservationStatus(ReservationStatusType.DURING);
		
		return reservation;
	}
	
	public Reservation createReservation(Date startDate,Date endDate, Long numberOfPeople, Table table, Owner owner) {
		
		Reservation reservation = new Reservation();
	
		addDatesToReservation(reservation, startDate, endDate);
		reservation.setNumberOfPeople(numberOfPeople);
		reservation.setTableId(table.getId());
		reservation.setOwner(owner);
		reservation.setReservationStatus(ReservationStatusType.DURING);
		
		return reservation;
	}
	
	private void addDatesToReservation(Reservation reservation, Date startDate, Date endDate) {
		
		if(Objects.nonNull(startDate) && Objects.nonNull(endDate) && startDate.compareTo(endDate) == 0) {
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(endDate);
			calendar.add(Calendar.HOUR, 1);
			
			endDate = calendar.getTime();
		}
		
		List<Date> sortedDates = Stream.of(startDate, endDate)
									  .sorted(Comparator.nullsLast(Comparator.naturalOrder()))
									  	.collect(Collectors.toList());
		
		reservation.setStartDate(sortedDates.get(0));
		reservation.setEndDate(sortedDates.get(1));
	}

}
