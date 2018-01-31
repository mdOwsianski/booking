package eu.ag.br.booking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import eu.ag.br.booking.common.ReservationStatusType;
import eu.ag.br.booking.common.StatusType;
import eu.ag.br.booking.data.dto.BookingTableDTO;
import eu.ag.br.booking.entities.Owner;
import eu.ag.br.booking.entities.Reservation;
import eu.ag.br.booking.entities.Table;

public class TestHelper {

	
	public static Date changeDate(Date date, int field, int value) {
		
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.add(field,value);
		
		return instance.getTime();
	}
	
	public static List<Reservation> createReservations(List<BookingTableDTO> tableDTOs, ReservationStatusType reservationStatus) {
		
		return  tableDTOs.stream()
							.map(new Function<BookingTableDTO, Reservation>() {
					
								@Override
								public Reservation apply(BookingTableDTO t) {
									return createReservation(t, reservationStatus);
								}
							}).collect(Collectors.toList());
	}
	
	public static Reservation createReservation(BookingTableDTO dto, ReservationStatusType reservationStatus) {
		
		Reservation reservation = new Reservation();
		reservation.setEndDate(dto.getEndDate());
		
		reservation.setOwner(createOwner(dto.getOwnerName()));
		reservation.setNumberOfPeople(dto.getNumberOfPeople());
		reservation.setTableId(dto.getNumberOfTable());
		reservation.setReservationStatus(reservationStatus);
		reservation.setStartDate(dto.getStartDate());
		
		return reservation;
	}
	
	public static BookingTableDTO createFirstEmptyBookingTableDTO(Long numberOfPeople) {
		return  BookingTableDTO.createForFirstEmptyTable(numberOfPeople);
	}
	
	public static BookingTableDTO createBookingTableDTO(Long tableId, Long numberOfPeople) {
		return BookingTableDTO.createForPeople(numberOfPeople, tableId);
	}
	
	public static Owner createOwner(String ownerName) {
		return createOwner(ownerName, null);
	}
	
	public static Owner createOwner(String ownerName, List<Reservation> reservations) {
		Owner owner = new Owner();
		owner.setName(ownerName);
		owner.setReservations(CollectionUtils.isNotEmpty(reservations) ? reservations : Lists.newArrayList());
		
		return owner;
	}
	
	
	public static List<Table> createTables(int limit, StatusType status) {

		return LongStream.range(0, limit).boxed().map(new Function<Long, Table>() {

			@Override
			public Table apply(Long t) {
				return createTable(t, status);
			}
		})
		.collect(Collectors.toList());
	}
	
	public static Table createBusyTable(Long id) {
		return createTable(id, StatusType.BUSY);
	}

	public static Table createEmptyTable(Long id) {
		return createTable(id, StatusType.EMPTY);
	}

	public static Table createReservedTable(Long id) {
		return createTable(id, StatusType.RESERVED);
	}

	public static Table createTable(Long id, StatusType status) {
		
		Table table = new Table();
		table.setId(id);
		table.setStatus(status);
		
		return table;
	}
	
	public static List<BookingTableDTO> createBookingTableDTOs(List<Table> tables) {
		
		return  tables.stream().mapToLong(Table::getId).boxed().map(new Function<Long, BookingTableDTO>() {

			@Override
			public BookingTableDTO apply(Long t) {
				return TestHelper.createBookingTableDTO(t, t);
			}
		}).collect(Collectors.toList());
	}
	
	public static List<Long> obtainReservationIds(List<Reservation> reservations) {
		
		return reservations.stream()
								.mapToLong(Reservation::getId).boxed()
								 .collect(Collectors.toList());
	}
	
	public static void assertReservation(Reservation parent, Reservation child) {
		
		assertTrue(Objects.nonNull(parent));
		assertTrue(Objects.nonNull(child));
		assertEquals(parent.getId(), child.getId());
		assertEquals(parent.getNumberOfPeople(), child.getNumberOfPeople());

		/** Owner test **/
		{
			assertOwer(parent.getOwner(), child.getOwner());
		}
		
		assertEquals(parent.getReservationStatus(), child.getReservationStatus());
		assertEquals(parent.getStartDate(), child.getStartDate());
		assertEquals(parent.getTableId(), child.getTableId());
	}
	
	public static void assertOwer(Owner parent, Owner child) {
		assertEquals(parent != null, child != null);
		assertEquals(parent.getId(), child.getId());
		assertEquals(parent.getName(), child.getName());
		assertTrue(CollectionUtils.isEqualCollection(parent.getReservations(), child.getReservations()));
	}
	
}
