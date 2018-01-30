package eu.ag.br.booking.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import eu.ag.br.booking.TestHelper;
import eu.ag.br.booking.common.ReservationStatusType;
import eu.ag.br.booking.common.StatusType;
import eu.ag.br.booking.data.dto.BookingTableDTO;
import eu.ag.br.booking.entities.Owner;
import eu.ag.br.booking.entities.Reservation;
import eu.ag.br.booking.entities.Table;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class ReservationRepositoryTest {
	
	
	@Autowired
	TestEntityManager testEntityManager;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	TableRepository tableRepository;
	
	@Autowired
	OwnerRepository ownerRepository;
	
	private Reservation savedReservation;
	private Table savedTable;
	private Owner savedOwner;
	
	@Before
	public void setUp() {
		
		ownerRepository.saveAndFlush(TestHelper.createOwner("admin"));
		
		savedOwner = ownerRepository.findByName("admin");
		assertNotNull(savedOwner);
		
		savedTable = tableRepository.save(TestHelper.createTable(1L, StatusType.BUSY));
		BookingTableDTO tableDTO = BookingTableDTO.createForPeople(20L, savedTable.getId());
		
		Reservation reservation = TestHelper.createReservation(tableDTO, ReservationStatusType.DURING);
		reservation.setOwner(savedOwner);
		
		savedReservation = reservationRepository.save(reservation);
	}

	@Test
	public void shouldFindActiveReservationByTableId() {
		
		Optional<Reservation> opReservation = 
				reservationRepository.findActiveReservationByTableId(savedTable.getId());
		
		assertTrue(opReservation.isPresent());
		TestHelper.assertReservation(savedReservation, opReservation.get());
	}
	
	@Test
	public void shouldFindActiveReservationIdByTableId() {
		
		Optional<Long> opReservationId = reservationRepository
				.findActiveReservationIdByTableId(savedTable.getId());
		
		assertTrue("Id wasn't get from repository. Optional's empty. ",opReservationId.isPresent());
		assertEquals(" Reservation id is not same. ",savedReservation.getId(), opReservationId.get());
	}
	
	@Test
	public void shouldBeSameTableId() {
		
		Long id = savedReservation.getId();
		Long tableId = savedReservation.getTableId();
		
		Optional<Long> findTableIdByReservationId = reservationRepository.findTableIdByReservationId(id);
		assertTrue("table id wasn't get from repository. Optional's empty. ",findTableIdByReservationId.isPresent());
		assertEquals("Table id isn't same. ",tableId, findTableIdByReservationId.get());
	}
	
	@Test
	public void shouldFindDeleteReservation() {

		Owner owner = ownerRepository.findByName("admin");
		assertNotNull(owner);
		
		Table saveTable = tableRepository.save(TestHelper.createTable(1L, StatusType.BUSY));
		BookingTableDTO tableDTO = BookingTableDTO.createForPeople(20L, saveTable.getId());
		
		Reservation reservation = TestHelper.createReservation(tableDTO, ReservationStatusType.DELETED);
		reservation.setOwner(owner);
		
		Reservation save = reservationRepository.save(reservation);
		
		List<Reservation> obtainedDeletedReservations = reservationRepository.getDeletedReservations();
		assertTrue(CollectionUtils.isNotEmpty(obtainedDeletedReservations));
		assertEquals(1,obtainedDeletedReservations.size());
		assertEquals(save.getId(), obtainedDeletedReservations.get(0).getId());
	}
	
	@Test
	public void shouldFindActiveReservation() {

		BookingTableDTO tableDTO = BookingTableDTO.createForPeople(20L, savedTable.getId());
		Reservation reservation = TestHelper.createReservation(tableDTO, ReservationStatusType.DURING);
		reservation.setOwner(savedOwner);
		
		savedReservation = reservationRepository.save(reservation);
		
		List<Reservation> obtainedDeletedReservations = reservationRepository.getActiveReservations();
		assertTrue(CollectionUtils.isNotEmpty(obtainedDeletedReservations));
		assertEquals(2, obtainedDeletedReservations.size());
		assertEquals(savedReservation.getId(), obtainedDeletedReservations.get(1).getId());
	}
	
	@Test
	public void shouldBeSameReservationIds() {
		
		List<Long> obtainedIds = reservationRepository.getAllIds();
		assertNotNull(savedReservation.getId());
		
		assertTrue("List of reservation ids is empty. ",CollectionUtils.isNotEmpty(obtainedIds));
		assertEquals("Lists of ids are not equals. ", savedReservation.getId(), obtainedIds.get(0));
	}

}
