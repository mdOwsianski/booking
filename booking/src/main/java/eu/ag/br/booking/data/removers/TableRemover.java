/**
 * 
 */
package eu.ag.br.booking.data.removers;

import eu.ag.br.booking.common.StatusType;
import eu.ag.br.booking.service.TableService;

/**
 * @author MOwsians
 *
 */
public class TableRemover {
	
	public static TableRemover create(TableService tableService) {
		return new TableRemover(tableService);
	}

	private TableService tableService;

	private TableRemover(TableService tableService) {
		this.tableService = tableService;
	}
	
	public void remove(Long tableId) {
		tableService.changeStatus(tableId, StatusType.EMPTY);
	}

}
