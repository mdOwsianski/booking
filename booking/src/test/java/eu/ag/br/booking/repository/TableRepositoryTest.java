package eu.ag.br.booking.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Iterables;

import eu.ag.br.booking.TestHelper;
import eu.ag.br.booking.common.StatusType;
import eu.ag.br.booking.entities.Owner;
import eu.ag.br.booking.entities.Table;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class TableRepositoryTest {

	@Autowired
	TestEntityManager testEntityManager;
	
	@Autowired
	TableRepository tableRepository;
	
	@Autowired
	OwnerRepository ownerRepository;
	
	private Owner savedOwner;
	private Table savedBusyTable;
	
	@Before
	public void setUp() {
		
		ownerRepository.saveAndFlush(TestHelper.createOwner("admin"));
		
		savedOwner = ownerRepository.findByName("admin");
		assertNotNull(savedOwner);
		
		savedBusyTable = tableRepository.save(TestHelper.createTable(1L, StatusType.BUSY));
	}
	
	
	@Test
	public void shouldFindAllIds() {
		
		List<Long> tableIds = Lists.newArrayList();
		List<Long> allIds = tableRepository.getAllIds();
		
		assertTrue(" List of table ids is empty. ",CollectionUtils.isNotEmpty(allIds));
		assertEquals(" List of table ids is empty. ",allIds.size(), 1);
		
		Table savedTable1 = tableRepository.save(TestHelper.createTable(2L, StatusType.RESERVED));
		assertNotNull(savedTable1);
		tableIds.add(savedTable1.getId());
		
		allIds = tableRepository.getAllIds();
		assertEquals(" List of table ids is not equal 2", 2, allIds.size());
		
		Table savedTable2 = tableRepository.save(TestHelper.createTable(3L, StatusType.EMPTY));
		assertNotNull(savedTable2);
		tableIds.add(savedTable2.getId());
		
		allIds = tableRepository.getAllIds();
		assertEquals(" List of table ids is not equal 3. ", 3, allIds.size());
	}
	
	
	@Test
	public void shouldFindMaxId() {
		
		List<Long> tableIds = Lists.newArrayList();
		
		Table savedTable1 = tableRepository.save(TestHelper.createTable(2L, StatusType.RESERVED));
		assertNotNull(savedTable1);
		tableIds.add(savedTable1.getId());
		
		Table savedTable2 = tableRepository.save(TestHelper.createTable(3L, StatusType.EMPTY));
		assertNotNull(savedTable2);
		tableIds.add(savedTable2.getId());

		Long maxId = Iterables.getLast(tableIds);
		assertNotNull(maxId);
		
		Long maxId2 = tableRepository.getMaxId();
		assertNotNull(maxId2);
		assertEquals(maxId, maxId2);
		
		List<Long> findAllIds = tableRepository.findAllIds();
		Long lastFromFindAllIds = Iterables.getLast(findAllIds);
		
		assertNotNull(lastFromFindAllIds);
		assertEquals(maxId, lastFromFindAllIds);
	}
	
	
	@Test
	public void shouldFindReservedTables() {
		
		List<Table> reservedTables = Lists.newArrayList();
		Table savedTable1 = tableRepository.save(TestHelper.createTable(2L, StatusType.RESERVED));
		assertNotNull(savedTable1);
		reservedTables.add(savedTable1);
		
		Table savedTable2 = tableRepository.save(TestHelper.createTable(3L, StatusType.EMPTY));
		assertNotNull(savedTable2);
		
		Table savedTable3 = tableRepository.save(TestHelper.createTable(5L, StatusType.RESERVED));
		assertNotNull(savedTable3);
		reservedTables.add(savedTable3);
		
		Table savedTable4 = tableRepository.save(TestHelper.createTable(15L, StatusType.RESERVED));
		assertNotNull(savedTable4);
		reservedTables.add(savedTable4);
		
		Table savedTable5 = tableRepository.save(TestHelper.createTable(13L, StatusType.BUSY));
		assertNotNull(savedTable5);
		
		Table savedTable6 = tableRepository.save(TestHelper.createTable(13L, StatusType.EMPTY));
		assertNotNull(savedTable6);
		
		List<Table> findReservedTables = tableRepository.findReservedTables();
		assertTrue(CollectionUtils.isNotEmpty(findReservedTables));
		assertEquals(reservedTables.size(), findReservedTables.size());
		assertTrue(CollectionUtils.isEqualCollection(reservedTables, findReservedTables));
	}
	
	@Test
	public void shouldFindBusyTables() {
		
		List<Table> busyTables = Lists.newArrayList(savedBusyTable);
		
		Table savedBusyTable1 = tableRepository.save(TestHelper.createTable(2L, StatusType.BUSY));
		Table savedBusyTable3 = tableRepository.save(TestHelper.createTable(5L, StatusType.BUSY));
		Table savedBusyTable4 = tableRepository.save(TestHelper.createTable(15L, StatusType.BUSY));
		Table savedBusyTable5 = tableRepository.save(TestHelper.createTable(133L, StatusType.BUSY));
		
		{
			assertNotNull(savedBusyTable1);
			assertNotNull(savedBusyTable3);
			assertNotNull(savedBusyTable4);
			assertNotNull(savedBusyTable5);
			
			busyTables.add(savedBusyTable1);
			busyTables.add(savedBusyTable3);
			busyTables.add(savedBusyTable4);
			busyTables.add(savedBusyTable5);
		}
		
		Table savedTable2 = tableRepository.save(TestHelper.createTable(3L, StatusType.EMPTY));
		assertNotNull(savedTable2);
		
		Table savedTable6 = tableRepository.save(TestHelper.createTable(23L, StatusType.EMPTY));
		assertNotNull(savedTable6);
		
		Table savedTable7 = tableRepository.save(TestHelper.createTable(33L, StatusType.RESERVED));
		assertNotNull(savedTable7);
		
		List<Table> findBusyTables = tableRepository.findBusyTables();
		assertTrue(CollectionUtils.isNotEmpty(findBusyTables));
		assertEquals(busyTables.size(), findBusyTables.size());
		assertTrue(CollectionUtils.isEqualCollection(busyTables, findBusyTables));
	}
	
	@Test
	public void shouldFindEmptyTables() {
		
		List<Table> emptyTables = Lists.newArrayList();
		
		Table savedEmptyTable1 = tableRepository.save(TestHelper.createTable(2L, StatusType.EMPTY));
		Table savedEmptyTable2 = tableRepository.save(TestHelper.createTable(3L, StatusType.EMPTY));
		Table savedEmptyTable3 = tableRepository.save(TestHelper.createTable(5L, StatusType.EMPTY));
		Table savedEmptyTable4 = tableRepository.save(TestHelper.createTable(15L, StatusType.EMPTY));
		Table savedEmptyTable5 = tableRepository.save(TestHelper.createTable(133L, StatusType.EMPTY));
		Table savedEmptyTable6 = tableRepository.save(TestHelper.createTable(23L, StatusType.EMPTY));
		
		{
			assertNotNull(savedEmptyTable1);
			assertNotNull(savedEmptyTable2);
			assertNotNull(savedEmptyTable3);
			assertNotNull(savedEmptyTable4);
			assertNotNull(savedEmptyTable5);
			assertNotNull(savedEmptyTable6);
			
			emptyTables.add(savedEmptyTable1);
			emptyTables.add(savedEmptyTable2);
			emptyTables.add(savedEmptyTable3);
			emptyTables.add(savedEmptyTable4);
			emptyTables.add(savedEmptyTable5);
		}
		
		
		Table savedTable7 = tableRepository.save(TestHelper.createTable(33L, StatusType.RESERVED));
		assertNotNull(savedTable7);
		
		Table savedTable8 = tableRepository.save(TestHelper.createTable(433L, StatusType.RESERVED));
		assertNotNull(savedTable8);
		
		List<Table> findEmptyTables = tableRepository.findEmptyTables();
		assertTrue(CollectionUtils.isNotEmpty(findEmptyTables));
		assertEquals(emptyTables.size(), findEmptyTables.size());
		assertTrue(CollectionUtils.isEqualCollection(emptyTables, findEmptyTables));
	}

}
