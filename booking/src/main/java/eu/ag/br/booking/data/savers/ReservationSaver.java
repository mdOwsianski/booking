/**
 * 
 */
package eu.ag.br.booking.data.savers;

import eu.ag.br.booking.common.ObjectConverters;
import eu.ag.br.booking.data.dto.ReservationDTO;
import eu.ag.br.booking.entities.Reservation;
import eu.ag.br.booking.service.ReservationService;

/**
 * @author MOwsians
 *
 */
public class ReservationSaver {
	
	public static ReservationSaver create(ReservationService reservationService) {
		return new ReservationSaver(reservationService);
	}
	
	private ReservationService reservationService;

	private ReservationSaver(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	/**
	 * @param reservationByTableIdIfExist
	 * @return
	 */
	public ReservationDTO save(Reservation reservation) {
		Reservation save = reservationService.save(reservation);
		
		ReservationDTO reservationTO = ObjectConverters.toReservationTO(save);
		return reservationTO;
	}

}
