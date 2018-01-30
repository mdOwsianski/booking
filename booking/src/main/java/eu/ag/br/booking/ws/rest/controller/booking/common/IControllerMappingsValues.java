package eu.ag.br.booking.ws.rest.controller.booking.common;

import eu.ag.br.booking.common.ICommonVariables;

/**
 * 
 * @author MOwsians
 *
 */
public interface IControllerMappingsValues extends ICommonVariables {

	
	public static final String RESERVATION = "reservation";
	
	/** PARAMS **/
	public static final String PATH_RESERVATION_ID = "{reservationId}";
	public static final String PATH_TABLE_ID = "{tableId}";
	public static final String PATH_NUMBER_OF_PEOPLE = "{numberOfPeople}";
	public static final String PATH_NUMBER_OF_TABLE ="{numberOfTable}";
	
	/**
	 * reservation/{reservationId}}
	 */
	public static final String RESERVATION_WITH_PATH_RESERVATION_ID = RESERVATION + SLASH + PATH_RESERVATION_ID;
	
	public static final String RESERVATION_WITH_PATH_TABLE_ID = RESERVATION + SLASH + PATH_TABLE_ID;
	public static final String TABLE = "table";
	public static final String PEOPLE = "people";
	public static final String PERIOD = "period";
	public static final String FROM = "from";
	public static final String IDS ="ids";
	public static final String ALL ="all";
	public static final String DEL ="del";
	public static final String ACTIVE ="active";
	
	public static final String EDIT ="edit";
	public static final String ADD ="add";
	public static final String CHANGE = "change";
	public static final String DATE = "date";
	public static final String OWNER = "owner";
	public static final String RESERVED = "reserved";
	public static final String BUSY = "busy";
	public static final String EMPTY = "empty";
	
	/**
	 * people/{numberOfPeople}
	 */
	public static String PEOPLE_WITH_PATH_NUMBER_OF_PEOPLE =  PEOPLE + SLASH + PATH_NUMBER_OF_PEOPLE;
	
	/**
	 * table/{numberOfTable}
	 */
	public static String TABLE_WITH_PATH_NUMBER_OF_TABLE = TABLE + SLASH + PATH_NUMBER_OF_TABLE;
	
	/**
	 * table/{numberOfTable}/people/{numberOfPeople}
	 */
	public static String TABLE_WITH_PATH_NUMBER_OF_TABLE_PEOPLE_WITH_NUMBER_OF_PEOPLE = TABLE_WITH_PATH_NUMBER_OF_TABLE+ SLASH
																		+ PEOPLE_WITH_PATH_NUMBER_OF_PEOPLE;
	
	/**
	 * table/{numberOfTable}/people/{numberOfPeople}
	 */
	public static String TABLE_PATH_NUMBER_OF_TABLE_PEOPLE_PATH_NUMBER_OF_PEOPLE = TABLE_WITH_PATH_NUMBER_OF_TABLE
																				+ SLASH + PEOPLE_WITH_PATH_NUMBER_OF_PEOPLE;
	
	/**
	 * table/{numberOfTable}/people/{numberOfPeople}/date?start=11-01-18-21:23&end=11-01-18-22:23
	 */
	public static String TABLE_PATH_NUMBER_OF_TABLE_PEOPLE_PATH_NUMBER_OF_PEOPLE_DATE = 
																	TABLE_PATH_NUMBER_OF_TABLE_PEOPLE_PATH_NUMBER_OF_PEOPLE 
																	+ SLASH + DATE;
	
	/**
	 * table/{numberOfTable}/people/{numberOfPeople}/from?fromDate=11-01-18-21:23
	 */
	public static String TABLE_PATH_NUMBER_OF_TABLE_PEOPLE_PATH_NUMBER_OF_PEOPLE_FROM = TABLE_PATH_NUMBER_OF_TABLE_PEOPLE_PATH_NUMBER_OF_PEOPLE
																						+ SLASH   + FROM;
	
	/**
	 * add/people/{numberOfPeople}/reservation/{reservationId}
	 */
	public static String EDIT_ADD_PEOPLE_TO_RESERVATION = ADD + SLASH + PEOPLE 
														+ SLASH + PATH_NUMBER_OF_PEOPLE 
														+ SLASH + RESERVATION_WITH_PATH_RESERVATION_ID;
	
	/**
	 * change/reservation/{numberOfReservation}
	 */
	public static String CHANGE_RESERVATION = CHANGE + SLASH + RESERVATION_WITH_PATH_RESERVATION_ID;
	
	/**
	 * 
	 * change/reservation/{numberOfReservation}/date?start=21-01-18-21:40&?end=21-01-18-21:40
	 * change/reservation/{numberOfReservation}/date?start=21-01-18-21:40
	 * change/reservation/{numberOfReservation}/date?end=21-01-18-21:40
	 */
	public static String CHANGE_RESERVATION_DATE = CHANGE_RESERVATION + SLASH + DATE;
	
	
	/**
	 * 
	 * change/reservation/{numberOfReservation}/table?number=1
	 * 
	 */
	public static String CHANGE_RESERVATION_TABLE = CHANGE_RESERVATION + SLASH + TABLE;
	
	/**
	 * change/reservation/{numberOfReservation}/owner?name=NameTest
	 */
	public static String CHANGE_RESERVATION_OWNER = CHANGE_RESERVATION + SLASH + OWNER;
	
	public static String ALL_IDS = ALL + SLASH + IDS;
	
	public static String ALL_DEL = ALL + SLASH + DEL;
	
	public static String ALL_ACTIVE = ALL + SLASH + ACTIVE;
	
	
	public static String ALL_RESERVED = ALL + SLASH + RESERVED;
	public static String ALL_BUSY = ALL + SLASH + BUSY;
	public static String ALL_EMPTY = ALL + SLASH + EMPTY;
	
}
