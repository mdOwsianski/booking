/**
 * 
 */
package eu.ag.br.booking.data.savers;

import eu.ag.br.booking.entities.Table;
import eu.ag.br.booking.service.TableService;

/**
 * @author MOwsians
 *
 */
public class TableSaver {

	public static TableSaver create(TableService tableService) {
		return new TableSaver(tableService);
	}

	private TableService tableService;

	private TableSaver(TableService tableService) {
		this.tableService = tableService;
	}
	
	public Table save(Table table) {
		return tableService.save(table);
	}
}
