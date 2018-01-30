package eu.ag.br.booking.ws.rest.controller.booking.common;

import eu.ag.br.booking.common.ICommonVariables;

/**
 * 
 * @author MOwsians
 *
 */
public interface IRequestMappings extends ICommonVariables{

	String API_VERSION = "api/v1/";
	String DEL = "del";
	String ADD = "add";
	String EDIT = "edit";
	String FIND = "find";
	String RESERVATION = "reservation";
	String OWNER ="owner";
	String TABLE ="table";
	
	String API_VERSION_DELETE = API_VERSION + DEL;
	String API_VERSION_ADD = API_VERSION + ADD;
	
	String API_VERSION_EDIT = API_VERSION + EDIT;
	String API_VERSION_FIND = API_VERSION + FIND;
	String API_VERSION_FIND_RESERVATION = API_VERSION_FIND + SLASH + RESERVATION ;
	String API_VERSION_FIND_OWNER = API_VERSION_FIND + SLASH + OWNER;
	String API_VERSION_FIND_TABLE = API_VERSION_FIND + SLASH + TABLE;
}
