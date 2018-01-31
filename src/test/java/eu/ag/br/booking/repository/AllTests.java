package eu.ag.br.booking.repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ OwnerRepositoryTest.class, 
				ReservationRepositoryTest.class, 
				TableRepositoryTest.class })
public class AllTests {

}
