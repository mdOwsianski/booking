/**
 * 
 */
package eu.ag.br.booking.common;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.common.collect.Lists;

import eu.ag.br.booking.data.dto.BookingTableDTO;
import eu.ag.br.booking.data.dto.ReservationDTO;
import eu.ag.br.booking.data.dto.UpdatedReservation;
import eu.ag.br.booking.ws.rest.response.CreateReservationResponse;
import eu.ag.br.booking.ws.rest.response.DeleteReservationResponse;
import eu.ag.br.booking.ws.rest.response.EditReservationResponse;
import eu.ag.br.booking.ws.rest.response.FindOwnerResponse;
import eu.ag.br.booking.ws.rest.response.FindReservationResponse;
import eu.ag.br.booking.ws.rest.response.FindTableResponse;
import eu.ag.br.booking.ws.rest.response.dto.find.FindOwnerDTO;
import eu.ag.br.booking.ws.rest.response.dto.find.FindReservationDTO;
import eu.ag.br.booking.ws.rest.response.dto.find.FindTableDTO;
import eu.ag.br.booking.ws.rest.response.exceptions.BaseActionBookingException;
import eu.ag.br.booking.ws.rest.response.exceptions.BaseActionResponseExceptionType;

/**
 * @author MOwsians
 *
 */
public class ObjectResponseConverters {


	public static List<HttpStatus> HTTP_STATUES_EMPTY_RESPONSE = Arrays.asList(HttpStatus.NO_CONTENT, HttpStatus.NOT_FOUND);
	
	public static ResponseEntity<CreateReservationResponse> toResponseEntity(ReservationDTO reservationTO) {
		
		
		ResponseEntity<CreateReservationResponse> responseEntity = null;
		
		CreateReservationResponse response = new CreateReservationResponse();
		response.setEndBookingDate(reservationTO.getEndDate());
		response.setNumberOfPeople(reservationTO.getNumberOfPeople());
		response.setReservationId(reservationTO.getId());
		response.setStartBookingDate(reservationTO.getStartDate());
		response.setTableId(reservationTO.getTable().getId());
		response.setStatus(reservationTO.getStatus());
		
		responseEntity = ResponseEntity.ok(response);;
		
		return responseEntity;
	}
	
	public static ResponseEntity<FindReservationResponse> toFindReservationResponse(List<FindReservationDTO> deletedReservations, 
																		  List<FindReservationDTO> durningReservations){
		
		FindReservationResponse dto = new FindReservationResponse();
		
		dto.setDeletedReservations(CollectionUtils.isNotEmpty(deletedReservations) ?  deletedReservations : Lists.newArrayList());
		dto.setDurningReservations(CollectionUtils.isNotEmpty(durningReservations) ? durningReservations : Lists.newArrayList());
		dto.setResponseMessage(HttpStatus.OK.name());
		
		return ResponseEntity.ok(dto);
	}
	
	public static ResponseEntity<EditReservationResponse> toEditReservationResponse(UpdatedReservation updatedReservation){
		
		ResponseEntity<EditReservationResponse> editReservationExceptionResponse = null;
		EditReservationResponse editBookingResponse = new EditReservationResponse();
		HttpStatus status = HttpStatus.NO_CONTENT;
		
		if(Objects.nonNull(updatedReservation)) {
			
			status = obtainStatus(updatedReservation);
			editBookingResponse.setReservationId(updatedReservation.getReservationId());
			editBookingResponse.setTableId(updatedReservation.getTableId());
			editBookingResponse.setUpdatedRows(updatedReservation.getUpdatedRows());
			
			if(HTTP_STATUES_EMPTY_RESPONSE.contains(status)){
				editBookingResponse.setResponseMessage("Wasn't updated.");
			}
			
			editReservationExceptionResponse = new ResponseEntity<EditReservationResponse>(editBookingResponse, status);
		}
		else {
			
			editReservationExceptionResponse = toEditReservationExceptionResponse(updatedReservation, 
												BaseActionResponseExceptionType.NOT_FOUND, 
												Optional.of("Resource has  been not found"));
		}
		
		return editReservationExceptionResponse;
	}

	public static ResponseEntity<DeleteReservationResponse> toDeleteReservationResponse(UpdatedReservation updatedReservation) {
		
		DeleteReservationResponse deleteBookingResponse = new DeleteReservationResponse();
		HttpStatus status = obtainStatus(updatedReservation);
		
		if(Objects.nonNull(updatedReservation)) {
			
			status = obtainStatus(updatedReservation);
			deleteBookingResponse.setReservationId(updatedReservation.getReservationId());
			deleteBookingResponse.setTableId(updatedReservation.getTableId());
			deleteBookingResponse.setUpdatedRows(updatedReservation.getUpdatedRows());
			
			if(HTTP_STATUES_EMPTY_RESPONSE.contains(status)){
				deleteBookingResponse.setResponseMessage("Wasn't updated.");
			}
		}
		
		return new ResponseEntity<DeleteReservationResponse>(deleteBookingResponse, status);
	}
	
	public static ResponseEntity<CreateReservationResponse> toReservationExceptionResponse(BookingTableDTO createdAddBookingTableTO, BaseActionResponseExceptionType responseExceptionType, Optional<String> message) {
		
		BaseActionBookingException baseActionResponseException = responseExceptionType.getBaseActionResponseException(message);
		CreateReservationResponse response = new CreateReservationResponse();
		response.setActionBookingException(baseActionResponseException);
		response.setNumberOfPeople(createdAddBookingTableTO.getNumberOfPeople());
		response.setEndBookingDate(createdAddBookingTableTO.getEndDate());
		response.setStartBookingDate(createdAddBookingTableTO.getStartDate());
		response.setResponseMessage(message.isPresent() ? message.get() : baseActionResponseException.getReason().name());
		response.setStatus(StatusType.UKNOW);
		response.setTableId(createdAddBookingTableTO.getNumberOfTable());
		
		return new ResponseEntity<CreateReservationResponse>(response, 
																	   baseActionResponseException.getReason());
	}
	
	public static ResponseEntity<EditReservationResponse> toEditReservationExceptionResponse(UpdatedReservation updatedReservation, 
																							 BaseActionResponseExceptionType responseExceptionType, 
																							 Optional<String> message) {
		
		if(Objects.isNull(updatedReservation)) {
			updatedReservation = new UpdatedReservation();
		}
		
		BaseActionBookingException baseActionResponseException = responseExceptionType.getBaseActionResponseException(message);
		EditReservationResponse response = new EditReservationResponse();
		response.setTableId(updatedReservation.getTableId());
		response.setUpdatedRows(updatedReservation.getUpdatedRows());
		response.setReservationId(updatedReservation.getReservationId());
		response.setActionBookingException(baseActionResponseException);
		response.setResponseMessage(message.isPresent() ? message.get() : baseActionResponseException.getReason().name());
		
		return new ResponseEntity<EditReservationResponse>(response,baseActionResponseException.getReason());
	}
	
	public static ResponseEntity<FindOwnerResponse> toFindOwnerResponse(List<FindOwnerDTO> ownerDTOs) {

		ResponseEntity<FindOwnerResponse> response = CollectionUtils.isNotEmpty(ownerDTOs)
																	? ResponseEntity.ok(new FindOwnerResponse(ownerDTOs))
																	: ResponseEntity.notFound().build();

		return response;
	}
	
	public static HttpStatus obtainStatus(UpdatedReservation updatedReservation) {
		return updatedReservation.getUpdatedRows() > 0 
				? HttpStatus.OK
				: HttpStatus.NOT_FOUND;
	}

	public static ResponseEntity<FindTableResponse> toFindReservedTablesResponse(List<FindTableDTO> reservedTables){
		return toFindTableResponse(Lists.newArrayList(), reservedTables, Lists.newArrayList());
	}
	
	public static ResponseEntity<FindTableResponse> toFindBusyTablesResponse(List<FindTableDTO> busyTables){
		return toFindTableResponse(Lists.newArrayList(), Lists.newArrayList(), busyTables);
	}
	
	public static ResponseEntity<FindTableResponse> toFindEmptyTablesResponse(List<FindTableDTO> emptyTables){
		return toFindTableResponse(emptyTables, Lists.newArrayList(), Lists.newArrayList());
	}
	
	public static ResponseEntity<FindTableResponse> toFindTableResponse(List<FindTableDTO> emptyTables,
																		List<FindTableDTO> reservedTables, 
																		List<FindTableDTO> busyTables) {

		ResponseEntity<FindTableResponse> response = ResponseEntity.noContent().build();

		boolean someListNotEmpty = Arrays.asList(CollectionUtils.isNotEmpty(emptyTables), 
												 CollectionUtils.isNotEmpty(reservedTables), 
												 CollectionUtils.isNotEmpty(busyTables)).contains(true);
		
		response =  someListNotEmpty 
							? ResponseEntity.ok(createFindTableResponse(emptyTables, reservedTables, busyTables))
							: createFindTableExceptionResponse();

		return response;
	}
	
	private static ResponseEntity<FindTableResponse> createFindTableExceptionResponse() {
		
		FindTableResponse findTableResponse = new FindTableResponse();
		BaseActionBookingException actionBookingException = BaseActionResponseExceptionType.NOT_FOUND
				.getBaseActionResponseException(Optional.of(" List of table are empty. "));

		findTableResponse.setActionBookingException(actionBookingException);
		findTableResponse.setResponseMessage(actionBookingException.getExceptionMessage());
		
		return new ResponseEntity<FindTableResponse>(findTableResponse, actionBookingException.getReason());
	}
	
	private static FindTableResponse createFindTableResponse(List<FindTableDTO> emptyTables,
										List<FindTableDTO> reservedTables, 
										List<FindTableDTO> busyTables) {
		
		FindTableResponse findTableResponse = new FindTableResponse();
		findTableResponse.setBusyTables(busyTables);
		findTableResponse.setEmptyTables(emptyTables);
		findTableResponse.setReservedTables(reservedTables);
		findTableResponse.setResponseMessage(HttpStatus.OK.name());
		
		return findTableResponse;
	}
	
}
