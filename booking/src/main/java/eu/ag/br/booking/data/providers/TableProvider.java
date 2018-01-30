/**
 * 
 */
package eu.ag.br.booking.data.providers;

import java.util.Date;

import eu.ag.br.booking.common.ActionType;
import eu.ag.br.booking.common.StatusType;
import eu.ag.br.booking.data.dto.BookingTableDTO;
import eu.ag.br.booking.data.dto.ObtainedTable;
import eu.ag.br.booking.data.obatiners.TableObatiner;
import eu.ag.br.booking.data.savers.TableSaver;
import eu.ag.br.booking.data.updaters.TableUpdater;
import eu.ag.br.booking.entities.Table;
import eu.ag.br.booking.service.TableService;

/**
 * @author MOwsians
 *
 */
public class TableProvider {

	public static TableProvider create(TableService tableService, BookingTableDTO addBookingTableTO) {
		return new TableProvider(tableService, addBookingTableTO);
	}

	private Table table;
	private BookingTableDTO addBookingTableTO;
	private TableService tableService;
	private TableObatiner tableObtainer;

	private TableProvider(TableService tableService, BookingTableDTO addBookingTableTO) {
		this.tableService = tableService;
		this.addBookingTableTO = addBookingTableTO;
		this.tableObtainer = TableObatiner.create(tableService, addBookingTableTO.getNumberOfTable());
	}

	public boolean isTableUsed() {
		return StatusType.isUsed(table.getStatus());
	}

	public StatusType obtainTableStatus() {

		boolean fromDateIsInFuture = addBookingTableTO.getStartDate().after(new Date());

		return fromDateIsInFuture ? StatusType.RESERVED : StatusType.BUSY;
	}

	public void saveTable() {

		ObtainedTable obtainedTableTO = tableObtainer.getOrCreateTableIfNotExist();
		Table obtainedTable = obtainedTableTO.getTable();

		if (obtainedTableTO.isUseTable()) {
			obtainedTable = tableObtainer.createNewBusyTable();
		} else {
			changeStatusForTable(obtainedTable);
		}

		persistTableChangesAndUpdateInfoAboutTable(obtainedTable, obtainedTableTO.getAction());
	}
	
	public Table getTable() {
		return table;
	}
	
	public BookingTableDTO getAddBookingTableTO() {
		return addBookingTableTO;
	}

	private void persistTableChangesAndUpdateInfoAboutTable(Table tableToPersist, ActionType action) {

		this.table = persistChanges(tableToPersist, action);
		updateInfoAboutTable();
	}

	private Table persistChanges(Table table, ActionType action) {

		table = ActionType.wasCreated(action) ? TableSaver.create(tableService).save(table)
											  : TableUpdater.create(tableService).update(table);

		return table;
	}

	private void updateInfoAboutTable() {

		tableObtainer.setTableId(table.getId());
		addBookingTableTO.setNumberOfTable(table.getId());
	}

	private void changeStatusForTable(Table table) {

		StatusType status = obtainTableStatus();
		table.setStatus(status);
	}

}
