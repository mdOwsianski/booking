package eu.ag.br.booking;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BookingOfTablesApplicationTests.class , 
				eu.ag.br.booking.data.savers.AllTests.class,
				eu.ag.br.booking.repository.AllTests.class})
public class AllTests {

}
