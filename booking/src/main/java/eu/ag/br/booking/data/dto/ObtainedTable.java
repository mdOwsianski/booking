/**
 * 
 */
package eu.ag.br.booking.data.dto;

import eu.ag.br.booking.common.ActionType;
import eu.ag.br.booking.common.StatusType;
import eu.ag.br.booking.entities.Table;

/**
 * @author MOwsians
 *
 */
public class ObtainedTable {
	
	
	private Table table;
	private ActionType action;

	public ObtainedTable(Table table, ActionType action) {
		super();
		this.table = table;
		this.action = action;
	}

	public Table getTable() {
		return table;
	}
	
	public boolean isUseTable() {
		return StatusType.isUsed(table.getStatus());
	}

	public ActionType getAction() {
		return action;
	}
	
}
