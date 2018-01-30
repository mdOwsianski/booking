/**
 * 
 */
package eu.ag.br.booking.data.updaters;

import eu.ag.br.booking.entities.Table;
import eu.ag.br.booking.service.TableService;

/**
 * @author MOwsians
 *
 */
public class TableUpdater {
	
	
	public static TableUpdater create(TableService tableService) {
		return new TableUpdater(tableService);
	}

	private TableService tableService;

	private TableUpdater(TableService tableService) {
		this.tableService = tableService;
	}
	
	public Table update(Table table) {
		tableService.changeStatus(table.getId(), table.getStatus());
		
		return table;
	}

}
