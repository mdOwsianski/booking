package eu.ag.br.booking.data.savers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import eu.ag.br.booking.TestHelper;
import eu.ag.br.booking.entities.Table;
import eu.ag.br.booking.service.TableService;
import eu.ag.br.booking.service.impl.TableServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TableSaverTest {

	@Bean
	public TableService tableService() {
		return new TableServiceImpl();
	}
	
	@Autowired
	TableService tableService;
	
	@Test
	public void shouldFindTable() {
		
		boolean existsById = tableService.existsById(1l);
		assertFalse(existsById);
		
		Table table = TestHelper.createBusyTable(1L);
		TableSaver.create(tableService).save(table);
		
		existsById = tableService.existsById(1l);
		assertTrue("Table is not exist. ", existsById);
	}
	
	@Test(expected= InvalidDataAccessApiUsageException.class)
	public void shouldBeExceptionSaveNull() {
		
		boolean existsById = tableService.existsById(2l);
		assertFalse(existsById);
		
		TableSaver.create(tableService).save(null);
		existsById = tableService.existsById(1l);
		
		assertFalse("Table is not exist. ", existsById);
	}

}
