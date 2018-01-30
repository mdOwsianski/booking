/**
 * 
 */
package eu.ag.br.booking.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Iterables;

import eu.ag.br.booking.common.ObjectConverters;
import eu.ag.br.booking.common.StatusType;
import eu.ag.br.booking.entities.Table;
import eu.ag.br.booking.repository.TableRepository;
import eu.ag.br.booking.repository.TableUpdateRepository;
import eu.ag.br.booking.service.TableService;
import eu.ag.br.booking.ws.rest.response.dto.find.FindTableDTO;

/**
 * @author MOwsians
 *
 */
@Service
public class TableServiceImpl implements TableService{

	@Autowired
	TableRepository tableRepository;
	
	@Autowired
	TableUpdateRepository tableUpdaterRepository;
	
	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.TableService#getLastId()
	 */
	@Override
	public Long getNextId() {
		
		List<Long> allIds = tableRepository.getAllIds();
		Long generatedId = 1L;
		
		if(CollectionUtils.isNotEmpty(allIds)) {
			
			Long lastId = Iterables.getLast(allIds);
			Optional<Long> findAny = LongStream.range(allIds.get(0), lastId)
													.boxed().filter(longValue -> !allIds.contains(longValue)).findAny();
			
			generatedId = findAny.isPresent() ? findAny.get() : lastId + 1  ;
		}
		
		return generatedId;
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.TableService#save()
	 */
	@Override
	public Table save(Table table) {
		return tableRepository.save(table);
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.TableService#findById(java.lang.Long)
	 */
	@Override
	public Optional<Table> findById(Long id) {
		return tableRepository.findById(id);
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.TableService#existsById(java.lang.Long)
	 */
	@Override
	public boolean existsById(Long id) {
		return tableRepository.existsById(id);
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.TableService#changeStatus(java.lang.Long, eu.ag.br.booking.common.StatusType)
	 */
	@Override
	public Integer changeStatus(Long tableId, StatusType status) {
		
		Integer rows = 0;
		switch (status) {
		case BUSY:
			rows = tableUpdaterRepository.markBusy(tableId);
			break;
		case EMPTY:
			rows = tableUpdaterRepository.markEmpty(tableId);
			break;
		case RESERVED:
			rows = tableUpdaterRepository.markReserved(tableId);
			break;
		case UKNOW:
			rows = tableUpdaterRepository.markUknow(tableId);
			break;
		}
		
		return rows;
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.TableService#findAllIds()
	 */
	@Override
	public List<Long> findAllIds() {
		return tableRepository.findAllIds();
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.TableService#findTablesReservedAll()
	 */
	@Override
	public List<FindTableDTO> findReservedTables() {
		List<Table> findTablesReservedAll = tableRepository.findReservedTables();
		return ObjectConverters.toFindTableBookingDTOs(findTablesReservedAll);
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.TableService#findTablesEmptyAll()
	 */
	@Override
	public List<FindTableDTO> findEmptyTables() {
		
		List<Table> findTablesEmptyAll = tableRepository.findEmptyTables();
		return ObjectConverters.toFindTableBookingDTOs(findTablesEmptyAll);
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.TableService#findTablesBusyAll()
	 */
	@Override
	public List<FindTableDTO> findBusyTables() {
		
		List<Table> findTablesBusyAll = tableRepository.findBusyTables();
		return ObjectConverters.toFindTableBookingDTOs(findTablesBusyAll);
	}

}
