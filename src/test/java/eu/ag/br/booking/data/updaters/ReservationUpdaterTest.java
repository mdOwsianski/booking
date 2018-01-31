package eu.ag.br.booking.data.updaters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import eu.ag.br.booking.TestHelper;
import eu.ag.br.booking.common.ReservationStatusType;
import eu.ag.br.booking.data.dto.BookingTableDTO;
import eu.ag.br.booking.data.savers.TableSaver;
import eu.ag.br.booking.entities.Owner;
import eu.ag.br.booking.entities.Reservation;
import eu.ag.br.booking.entities.Table;
import eu.ag.br.booking.repository.OwnerRepository;
import eu.ag.br.booking.repository.ReservationRepository;
import eu.ag.br.booking.service.ReservationService;
import eu.ag.br.booking.service.TableService;
import eu.ag.br.booking.service.impl.ReservationServiceImpl;
import eu.ag.br.booking.service.impl.TableServiceImpl;
import eu.ag.br.booking.ws.rest.response.EditReservationResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationUpdaterTest {

	@Bean
	public ReservationService reservationService() {
		return new ReservationServiceImpl();
	}
	
	@Bean
	public TableService tableService() {
		return new TableServiceImpl();
	}
	
	@Autowired
	TableService tableService;
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	OwnerRepository ownerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	private Reservation savedReservation;
	
	
	@Before
	public void init() {
		
		Owner savedOwner = ownerRepository.save(TestHelper.createOwner("admin"));
		
		Table createdBusyTable = TestHelper.createBusyTable(100L);
		Table savedBusyTable = TableSaver.create(tableService).save(createdBusyTable);
		BookingTableDTO bookingTableDTO = TestHelper.createBookingTableDTO(savedBusyTable.getId(), 10L);
		
		Reservation reservation = TestHelper
									.createReservation(bookingTableDTO, ReservationStatusType.DURING);
		
		reservation.setOwner(savedOwner);
		
		savedReservation = reservationService.save(reservation);
	}
	
	@Test
	public void shouldUpdatePeopleInReservation() {
		
		Table createdBusyTable = TestHelper.createBusyTable(100L);
		Table savedBusyTable = TableSaver.create(tableService).save(createdBusyTable);
		BookingTableDTO bookingTableDTO = TestHelper.createBookingTableDTO(savedBusyTable.getId(), 10L);
		
		List<Reservation> allActiveReservations = reservationService.getAllActiveReservations();
		
		allActiveReservations = reservationService.getAllActiveReservations();
		assertTrue(" Reservations're empty. ",CollectionUtils.isNotEmpty(allActiveReservations));
		assertEquals(bookingTableDTO.getNumberOfPeople(), savedReservation.getNumberOfPeople());
		
		ReservationUpdater reservationUpdater = ReservationUpdater
													.createByTableId(reservationService, 
																	 createdBusyTable.getId());
		
		ResponseEntity<EditReservationResponse> updatePeople = null;
		EditReservationResponse editReservationResponse = null;
		
		//wrong number of people
		{
			updatePeople = reservationUpdater.updatePeople(-1L);
			assertNotNull(updatePeople);
			assertEquals(HttpStatus.BAD_REQUEST, updatePeople.getStatusCode());
			
			editReservationResponse = updatePeople.getBody();
			assertEquals(savedReservation.getId(), editReservationResponse.getReservationId());
			assertEquals(Integer.valueOf(0), editReservationResponse.getUpdatedRows());
		}
		
		//correct number of people
		{
			updatePeople = reservationUpdater.updatePeople(999L);
			
			assertNotNull(updatePeople);
			assertEquals(HttpStatus.OK, updatePeople.getStatusCode());
			
			editReservationResponse = updatePeople.getBody();
			assertEquals(savedReservation.getId(), editReservationResponse.getReservationId());
			assertEquals(Integer.valueOf(1), editReservationResponse.getUpdatedRows());
		}
	}
	
	@Test
	public void shouldUpdateDatesInReservation() {
		
		Date startDate = savedReservation.getStartDate();
		Date added2MonthsToDate = TestHelper.changeDate(startDate, Calendar.MONTH, 2);
		
		ReservationUpdater reservationUpdater = ReservationUpdater
													.create(reservationService, 
															savedReservation.getId());
		
		
		ResponseEntity<EditReservationResponse> updateReservationDate = reservationUpdater
																			.updateReservationDate(added2MonthsToDate, null);
		
		TestHelper.assertOKEditReservationResponse(updateReservationDate, 1);
		
		Optional<Reservation> obtainedReservation = reservationRepository
														.findById(savedReservation.getId());
		
		assertTrue(obtainedReservation.isPresent());
		assertEquals(added2MonthsToDate, obtainedReservation.get().getStartDate());
		
		//start date after end date
		{
			Date nowDate = new Date();
			Date endDate = nowDate;
			
			updateReservationDate = reservationUpdater
										.updateReservationDate(added2MonthsToDate, endDate);
			
			TestHelper.assertOKEditReservationResponse(updateReservationDate, 1);

			Optional<Reservation> findById = reservationRepository
												.findById(savedReservation.getId());
			
			assertNotNull(findById);
			assertTrue(findById.isPresent());
			assertEquals(added2MonthsToDate, findById.get().getStartDate());
			assertEquals(endDate, findById.get().getEndDate());
		}
	}

}
