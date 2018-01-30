/**
 * 
 */
package eu.ag.br.booking.data.obatiners;

import java.util.Objects;
import java.util.Optional;

import eu.ag.br.booking.common.ActionType;
import eu.ag.br.booking.common.StatusType;
import eu.ag.br.booking.data.dto.ObtainedTable;
import eu.ag.br.booking.entities.Table;
import eu.ag.br.booking.service.TableService;

/**
 * @author MOwsians
 *
 */
public class TableObatiner {

	public static TableObatiner create(TableService tableService, Long tableId) {
		return new TableObatiner(tableService, tableId);
	}

	private TableService tableService;
	private Long tableId;
	private boolean isTableIdNotNull;
	private ObtainedTable obtainedTable;
	private ActionType action;

	private TableObatiner(TableService tableService, Long tableId) {
		this.tableService = tableService;
		this.tableId = tableId;
		isTableIdNotNull = Objects.nonNull(tableId) && tableId >= 0;
		
		action = ActionType.UKNOW;
	}

	public ObtainedTable getOrCreateTableIfNotExist() {
		
		Table table = null;
		Table tableIfExist = getTableIfExist();
		
		table = Objects.isNull(tableIfExist) ? createTable() : tableIfExist;
		obtainedTable = new ObtainedTable(table, action);
		
		return obtainedTable;
	}
	
	public Table getTableIfExist() {

		Table table = null;

		if (isTableIdNotNull) {
			Optional<Table> findById = findById();
			if (findById.isPresent()) {
				table = findById.get();
				setGetActionType();
			}
		}

		return table;
	}

	public boolean existTable() {

		boolean existsById = tableService.existsById(tableId);
		return existsById;
	}

	public Long obtainNextId() {
		Long id = tableService.getNextId();
		return id;
	}

	public Optional<Table> findById() {
		return tableService.findById(tableId);
	}

	public Table createTable() {

		boolean isTableIdNull = !isTableIdNotNull;
		if(isTableIdNull) tableId = obtainNextId();
		
		Table table = new Table();
		table.setId(tableId);
		table.setStatus(StatusType.EMPTY);
		
		setCreatedActionType();

		return table;
	}
	
	public Table createNewEmptyTable() {
		return createNewTable(StatusType.EMPTY);
	}
	
	public Table createNewBusyTable() {
		return createNewTable(StatusType.BUSY);
	}
	
	private Table createNewTable(StatusType status) {
		
		Table table = new Table();
		table.setId(obtainNextId());
		table.setStatus(status);

		setCreatedActionType();
		return table;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}
	
	private void setCreatedActionType() {
		action = ActionType.CREATED;
	}
	
	private void setGetActionType() {
		action = ActionType.CREATED;
	}

}
