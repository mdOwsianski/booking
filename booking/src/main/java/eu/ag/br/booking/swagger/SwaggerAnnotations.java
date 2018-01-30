package eu.ag.br.booking.swagger;

/**
 * Class used to store swagger descriptions
 * 
 * @author MOwsians
 */
public final class SwaggerAnnotations {

	public static final String VALIDATE_PATH_STATUS_200_MESSAGE = "Request was corrected";
	public static final String VALIDATE_PATH_STATUS_500_MESSAGE = "Something went wrong. Look at logs, please.";

	public static final String ROOT_PATH_SUMMARY = "API welcome message";
	public static final String ROOT_PATH_DESCRIPTION = "Base API path to test if service is online";
	public static final String ROOT_PATH_STATUS_200_MESSAGE = "Service is up";
	
	
	/**  CREATE BOOKING OF TABLE **/
	public static final String CREATE_BOOKING_OF_TABLE_CONTROLLER = "Controller do tworzenia rezerwacji";
	
	public static final String BOOK_FIRST_EMPTY_TABLE_FOR_PEOPLE ="First empty table will be reserve.";
	public static final String BOOK_FIRST_EMPTY_TABLE_FOR_PEOPLE_DESCRIPTION ="Table has been created for given number of people.";
	
	public static final String BOOK_TABLE_FOR_PEOPLE ="The table'll be reserved for the given number. ";
	public static final String BOOK_TABLE_FOR_PEOPLE_DESCRIPTION ="The table'll be reserved for the given number and number of people. "
																	+ "If table not exist then it will be created a new one.";
	
	public static final String BOOK_TABLE_DATE ="The table'll be reserved for the given number and number of people  "
												+ "between given parameters start and end dates.";
	public static final String BOOK_TABLE_DATE_DESCRIPTION =" It creates a table for the number of people. "
															+ "When the table isn't or it's busy, it creates a new one";
	
	/** DELETE RESERVATION **/
	
	public static final String DELETE_RESERVATION ="The reservation'll be deleted.";
	public static final String DELETE_RESERVATION_DESCRIPTION ="If reservation exist then and only then status will be changed.";
	
	/** EDIT RESERVATION **/
	
	public static final String ADD_PEOPLE_TO_RESERVATION = "Adds up number of new people to reservation. ";
	public static final String ADD_PEOPLE_TO_RESERVATION_DESCRIPTION = "Will be obtained reservation from given reservation id." + 
																		" If reservation exist then will  get number of people." + 
																		" And right after will be added up. ";

	public static final String CHANGE_OWNER = "Owner'll be changed in reservation.";
	public static final String CHANGE_OWNER_DESCRIPTION = "Will be obtained reservation from given reservation id. "
														+ " If reservation exist then will  get number of people. "
														+ " And right after will be added up. ";
	
	public static final String CHANGE_TABLE = "The number of table'll be changed";
	public static final String CHANGE_TABLE_DESCRIPTION = "If reservation exist then number of table'll be changed."
														+ "If table not exist or table's busy then will be created a new one.";
	
	public static final String CHANGE_DATE_RESERVATION = "dates'll be changed";
	public static final String CHANGE_DATE_RESERVATION_DESCRIPTION = "If reservation exist then dates'll be changed.";
	
	/** FIND RESERVATION **/
	
	public static final String FIND_ALL_RESERVATIONS ="all reservationsi'll be returned";
	public static final String FIND_ALL_RESERVATIONS_DESCRIPTION ="All reservations'll be to broken down into statuses as during and delete.";
	
	public static final String FIND_ALL_DEL_RESERVATIONS ="Delete reservations be returned.";
	public static final String FIND_ALL_DEL_RESERVATIONS_DESCRIPTION ="The delete reservations be returned.";
	
	public static final String FIND_ALL_ACTIVE_RESERVATIONS ="Active reservations be returned.";
	public static final String FIND_ALL_ACTIVE_RESERVATIONS_DESCRIPTION ="Active reservations be returned. "
																		+ "Active reservations have the during status";
	
}
