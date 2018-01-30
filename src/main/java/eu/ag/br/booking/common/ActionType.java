/**
 * 
 */
package eu.ag.br.booking.common;

/**
 * @author MOwsians
 *
 */
public enum ActionType {

	UKNOW,
	CREATED,
	GET
	;
	
	public static boolean wasCreated(ActionType type) {
		return CREATED.equals(type);
	}
}
