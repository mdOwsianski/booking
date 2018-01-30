/**
 * 
 */
package eu.ag.br.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eu.ag.br.booking.entities.Owner;

/**
 * @author MOwsians
 *
 */
public interface OwnerRepository extends JpaRepository<Owner, Long> {

	@Query("select o from Owner o where o.name =:ownerName")
	Owner findByName(@Param("ownerName") String ownerName);
}
