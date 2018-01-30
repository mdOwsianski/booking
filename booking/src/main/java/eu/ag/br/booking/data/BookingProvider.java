/**
 * 
 */
package eu.ag.br.booking.data;

import eu.ag.br.booking.data.dto.BookingTableDTO;
import eu.ag.br.booking.data.dto.ObtainedOwner;
import eu.ag.br.booking.data.dto.ReservationDTO;
import eu.ag.br.booking.data.obatiners.OwnerObtainer;
import eu.ag.br.booking.data.providers.ReservationProvider;
import eu.ag.br.booking.data.providers.TableProvider;
import eu.ag.br.booking.entities.Table;
import eu.ag.br.booking.service.OwnerService;
import eu.ag.br.booking.service.ReservationService;
import eu.ag.br.booking.service.TableService;

/**
 * @author MOwsians
 *
 */
public class BookingProvider {

	
	public static BookingProvider create(ReservationService resevationService, 
										 TableService tableService,
										 OwnerService ownerService, BookingTableDTO addBookingTableTO) {
		
		BookingProvider bookingProvider = new BookingProvider(resevationService, tableService, 
															  ownerService, addBookingTableTO);
		
		bookingProvider.saveTable();
		bookingProvider.saveReservation();
		
		return bookingProvider;
	}

	private Table table;
	private ReservationDTO reservation;
	private OwnerObtainer ownerObtainer;
	private TableProvider tableProvider;
	private ReservationProvider reservationProvider;
	
	
	public BookingProvider(ReservationService reservationService, 
						   TableService tableService, 
						   OwnerService ownerService,
						   BookingTableDTO addBookingTableTO) {
		
		this.reservationProvider = ReservationProvider.create(reservationService);
		this.tableProvider = TableProvider.create(tableService, addBookingTableTO);
		this.ownerObtainer = OwnerObtainer.create(ownerService, addBookingTableTO.getOwnerName());
	}
	
	public void saveTable() {
		tableProvider.saveTable();
		table = tableProvider.getTable();
		reservationProvider.setAddBookingTableTO(tableProvider.getAddBookingTableTO());
	}

	public void saveReservation() {

		ObtainedOwner obtainedOwner = ownerObtainer.getOrCreateIfNotExist();
		reservationProvider.saveReservation(obtainedOwner, table);
		reservation = reservationProvider.getReservation();
	}

	public ReservationDTO getReservation() {
		return reservation;
	}
	
}
