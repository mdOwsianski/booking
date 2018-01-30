/**
 * 
 */
package eu.ag.br.booking.service;

import java.util.List;
import java.util.Optional;

import eu.ag.br.booking.common.StatusType;
import eu.ag.br.booking.entities.Table;
import eu.ag.br.booking.ws.rest.response.dto.find.FindTableDTO;

/**
 * @author MOwsians
 *
 */
public interface TableService {

	Long getNextId();
	Table save(Table table);
	Optional<Table> findById(Long id);
	boolean existsById(Long id);
	Integer changeStatus(Long tableId, StatusType status);
	
	List<Long> findAllIds();
	List<FindTableDTO> findReservedTables();
	List<FindTableDTO> findEmptyTables();
	List<FindTableDTO> findBusyTables();
}
