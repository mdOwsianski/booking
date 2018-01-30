/**
 * 
 */
package eu.ag.br.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eu.ag.br.booking.entities.Table;

/**
 * @author MOwsians
 *
 */
public interface TableRepository extends JpaRepository<Table, Long> {
	
	@Query("select t.id from Table t")
	List<Long> getAllIds();
	
	@Query("select max(t.id) from Table t")
	Long getMaxId();
	
	@Query("select t.id from Table t")
	List<Long> findAllIds();
	
	@Query("select t from Table t where  t.status=eu.ag.br.booking.common.StatusType.RESERVED")
	List<Table> findReservedTables();
	
	@Query("select t from Table t where  t.status=eu.ag.br.booking.common.StatusType.EMPTY")
	List<Table> findEmptyTables();
	
	@Query("select  t from Table t where  t.status=eu.ag.br.booking.common.StatusType.BUSY")
	List<Table> findBusyTables();
}
