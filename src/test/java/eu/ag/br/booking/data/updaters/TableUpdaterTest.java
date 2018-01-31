package eu.ag.br.booking.data.updaters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import eu.ag.br.booking.TestHelper;
import eu.ag.br.booking.common.StatusType;
import eu.ag.br.booking.data.obatiners.TableObatiner;
import eu.ag.br.booking.data.savers.TableSaver;
import eu.ag.br.booking.entities.Table;
import eu.ag.br.booking.service.TableService;
import eu.ag.br.booking.service.impl.TableServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TableUpdaterTest {
	
	@Bean
	public TableService tableService() {
		return new TableServiceImpl();
	}
	
	@Autowired
	TableService tableService;
	
	@Test
	public void shouldUpdateStatusInTable() {
		
		Table createBusyTable = TestHelper.createBusyTable(1L);
		TableSaver.create(tableService).save(createBusyTable);
		
		TableObatiner tableObatiner = TableObatiner .create(tableService, createBusyTable.getId());
		Optional<Table> opTable = tableObatiner.findById();
		
		assertTrue(opTable.isPresent());
		
		Table obtainedTable = opTable.get();
		assertEquals(StatusType.BUSY, obtainedTable.getStatus());
		
		obtainedTable.setStatus(StatusType.EMPTY);
		TableUpdater.create(tableService).update(obtainedTable);
		
		opTable = tableObatiner.findById();
		assertTrue(opTable.isPresent());
		assertEquals(StatusType.EMPTY, opTable.get().getStatus());
	}
	
	
	@Test(expected= InvalidDataAccessApiUsageException.class)
	public void shouldNotUpdateTable() {
		
		Table createBusyTable = TestHelper.createBusyTable(1L);
		
		TableSaver.create(tableService).save(createBusyTable);
		
		TableObatiner tableObatiner = TableObatiner .create(tableService, null);
		Optional<Table> opTable = tableObatiner.findById();
		
		assertTrue(opTable.isPresent());
	}

}
