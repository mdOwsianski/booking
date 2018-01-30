/**
 * 
 */
package eu.ag.br.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import eu.ag.br.booking.entities.Table;

/**
 * @author MOwsians
 *
 */
public interface TableUpdateRepository extends JpaRepository<Table, Integer> {

	@Transactional
	@Modifying
	@Query("update Table t set t.status=eu.ag.br.booking.common.StatusType.EMPTY where t.id= :id")
	Integer markEmpty(@Param("id") Long tableId);
	
	@Transactional
	@Modifying
	@Query("update Table t set t.status=eu.ag.br.booking.common.StatusType.RESERVED where t.id= :id")
	Integer markReserved(@Param("id") Long tableId);

	/**
	 * @param tableId
	 */
	@Transactional
	@Modifying
	@Query("update Table t set t.status=eu.ag.br.booking.common.StatusType.BUSY where t.id= :id")
	Integer markBusy(@Param("id") Long tableId);

	/**
	 * @param tableId
	 */
	@Transactional
	@Modifying
	@Query("update Table t set t.status=eu.ag.br.booking.common.StatusType.UKNOW where t.id= :id")
	Integer markUknow(@Param("id") Long tableId);
	
}
