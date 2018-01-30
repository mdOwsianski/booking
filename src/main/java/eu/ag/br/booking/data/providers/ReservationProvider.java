/**
 * 
 */
package eu.ag.br.booking.data.providers;

import eu.ag.br.booking.data.dto.BookingTableDTO;
import eu.ag.br.booking.data.dto.ObtainedOwner;
import eu.ag.br.booking.data.dto.ReservationDTO;
import eu.ag.br.booking.data.obatiners.ReservationObtainer;
import eu.ag.br.booking.data.savers.ReservationSaver;
import eu.ag.br.booking.entities.Owner;
import eu.ag.br.booking.entities.Reservation;
import eu.ag.br.booking.entities.Table;
import eu.ag.br.booking.service.ReservationService;

/**
 * @author MOwsians
 *
 */
public class ReservationProvider {
	
	
	public static ReservationProvider create(ReservationService reservationService) {
		return new ReservationProvider(reservationService);
	}
	
	private BookingTableDTO addBookingTableTO;
	private ReservationDTO reservation;
	private ReservationObtainer reservationObtainer;
	private ReservationSaver reservationSaver;

	private ReservationProvider(ReservationService reservationService) {
		this.reservationObtainer = ReservationObtainer.create(reservationService);
		this.reservationSaver = ReservationSaver.create(reservationService);
	}
	
	public void saveReservation(ObtainedOwner obtainedOwner, Table table) {

		Reservation reservationEntity = createReservation(obtainedOwner.getOwner(), table);

		reservation = reservationSaver.save(reservationEntity);
		reservation.setStatus(table.getStatus());
	}

	public Reservation createReservation(Owner owner, Table table) {

		Reservation reserv = reservationObtainer.createReservation(addBookingTableTO.getStartDate(), 
																   addBookingTableTO.getEndDate(),
																   addBookingTableTO.getNumberOfPeople(), 
																   table, owner);

		return reserv;
	}

	public void setAddBookingTableTO(BookingTableDTO addBookingTableTO) {
		this.addBookingTableTO = addBookingTableTO;
	}

	public ReservationDTO getReservation() {
		return reservation;
	}

}
