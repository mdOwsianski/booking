/**
 * 
 */
package eu.ag.br.booking.data.dto;

import eu.ag.br.booking.common.ActionType;
import eu.ag.br.booking.entities.Owner;

/**
 * @author MOwsians
 *
 */
public class ObtainedOwner {

	private Owner owner;
	private ActionType action;

	public ObtainedOwner(Owner owner, ActionType action) {
		super();
		this.owner = owner;
		this.action = action;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public ActionType getAction() {
		return action;
	}

	public void setAction(ActionType action) {
		this.action = action;
	}

}
