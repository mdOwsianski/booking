package eu.ag.br.booking.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Lists;

import eu.ag.br.booking.TestHelper;
import eu.ag.br.booking.entities.Owner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class OwnerRepositoryTest {

	@Autowired
	TestEntityManager testEntityManager;
	
	@Autowired
	OwnerRepository ownerRepository;
	
	@Test
	public void shouldFoundOwner() {
		
		String ownerName = "OwnerNameTest";
		Owner createOwner = TestHelper.createOwner(ownerName);
		testEntityManager.persist(createOwner);
		testEntityManager.flush();
		
		Owner findByName = ownerRepository.findByName(ownerName);
		
		assertTrue(Objects.nonNull(findByName));
		assertTrue(ownerName.equals(findByName.getName()));
	}
	
	@Test
	public void shouldNotFoundOwner() {
		
		String ownerName = "OwnerNameTest_";
		Owner createOwner = TestHelper.createOwner(ownerName+UUID.randomUUID());
		
		testEntityManager.persist(createOwner);
		testEntityManager.flush();
		
		Owner findByName = ownerRepository.findByName(ownerName);
		assertTrue(Objects.isNull(findByName));
	}
	
	
	@Test
	public void shouldFoundOwners() {
		
		String ownerName = "OwnerNameTest_";
		
		List<Owner> owners = Lists.newArrayList();
		owners.add(TestHelper.createOwner(ownerName+UUID.randomUUID()));
		owners.add(TestHelper.createOwner(ownerName+UUID.randomUUID()));
		owners.add(TestHelper.createOwner(ownerName+UUID.randomUUID()));
		owners.add(TestHelper.createOwner(ownerName+UUID.randomUUID()));
		owners.add(TestHelper.createOwner(ownerName+UUID.randomUUID()));
		owners.add(TestHelper.createOwner(ownerName+UUID.randomUUID()));
		
		ownerRepository.saveAll(owners);
		
		List<Owner> findAll = ownerRepository.findAll();
		assertTrue(CollectionUtils.isNotEmpty(findAll));
		assertEquals(owners.size(), findAll.size());
		assertTrue(CollectionUtils.isEqualCollection(findAll, owners));
	}

}
