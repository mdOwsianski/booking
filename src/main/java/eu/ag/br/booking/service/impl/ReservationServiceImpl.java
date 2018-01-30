/**
 * 
 */
package eu.ag.br.booking.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import eu.ag.br.booking.common.ObjectConverters;
import eu.ag.br.booking.common.ReservationStatusType;
import eu.ag.br.booking.common.StatusType;
import eu.ag.br.booking.data.dto.UpdatedReservation;
import eu.ag.br.booking.entities.Owner;
import eu.ag.br.booking.entities.Reservation;
import eu.ag.br.booking.entities.Table;
import eu.ag.br.booking.repository.OwnerRepository;
import eu.ag.br.booking.repository.ReservationRepository;
import eu.ag.br.booking.repository.ReservationUpdateRepository;
import eu.ag.br.booking.repository.TableRepository;
import eu.ag.br.booking.service.ReservationService;

/**
 * @author MOwsians
 *
 */
@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	ReservationUpdateRepository reservationUpdateRepository;
	
	@Autowired
	OwnerRepository ownerRepository;
	
	@Autowired
	TableRepository tableRepository;

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.ReservationService#findReservationByTableId(java.lang.Long)
	 */
	@Override
	public List<Reservation> findReservationsByTableId(Long id) {
		
		List<Reservation> foundReservations = reservationRepository.findReservationsByTableId(id);
		
		return CollectionUtils.isNotEmpty(foundReservations) ? foundReservations : Lists.newArrayList();
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.ReservationService#save(eu.ag.br.booking.entities.Reservation)
	 */
	@Override
	public Reservation save(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.ReservationService#getAllIds()
	 */
	@Override
	public List<Long> getAllIds() {
		return reservationRepository.getAllIds();
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.ReservationService#markReservationDeletedByTableId(java.lang.Long)
	 */
	@Override
	public Integer markReservationDeletedByTableId(Long tableId) {
		Integer markReservationDeletedByTableId = reservationUpdateRepository.markReservationDeletedByTableId(tableId);
		
		return markReservationDeletedByTableId;
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.ReservationService#markReservationDeleted(java.lang.Long)
	 */
	@Override
	public Integer markReservationDeleted(Long reservationId) {
		
		Integer updatedRows = 0;
		try
		{
			updatedRows = reservationUpdateRepository.markReservationDeleted(reservationId);
			
		}catch (Exception e) {

			Optional<Reservation> findById = reservationRepository.findById(reservationId);
			if(findById.isPresent()) {
				
				Reservation reservation = findById.get();
				reservation.setReservationStatus(ReservationStatusType.DELETED);
				reservationRepository.save(reservation);
				updatedRows = 1;
			}
		}
		
		return updatedRows;
	}

	@Override
	public List<Reservation> getAllReservations() {
		return reservationRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.ReservationService#changeStatus(java.lang.Long, eu.ag.br.booking.common.ReservationStatusType)
	 */
	@Override
	public Integer changeStatus(Long reservationId, ReservationStatusType status) {
		return changeStatusReservation(reservationId, null, status);
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.ReservationService#changeStatusByTable(java.lang.Long, eu.ag.br.booking.common.ReservationStatusType)
	 */
	@Override
	public Integer changeStatusByTable(Long tableId, ReservationStatusType status) {
		return changeStatusReservation(null, tableId, status);
	}
	
	@SuppressWarnings("incomplete-switch")
	private int changeStatusReservation(Long reservationId, Long tableId, ReservationStatusType status) {
		
		boolean reservation = Objects.nonNull(reservationId);
		boolean table = Objects.nonNull(tableId);
		int updatedRows = 0;
		
		switch (status) {
		case DELETED:
			
			if (reservation) updatedRows += reservationUpdateRepository.markReservationDeleted(reservationId);
			if(table) updatedRows += reservationUpdateRepository.markReservationDeletedByTableId(tableId);
			break;
			
		case DURING:
			
			if(reservation)	updatedRows += reservationUpdateRepository.markReservationDuring(reservationId);
			if(table) updatedRows += reservationUpdateRepository.markReservationDuringByTableId(tableId);
			break;
		}
		
		return updatedRows;
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.ReservationService#getAllActiveReservations()
	 */
	@Override
	public List<Reservation> getAllActiveReservations() {
		return reservationRepository.getActiveReservations();
	}

	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.ReservationService#getAllDeletedReservations()
	 */
	@Override
	public List<Reservation> getAllDeletedReservations() {
		return reservationRepository.getDeletedReservations();
	}

	@Override
	public Long findDurningReservationIdByTableId(Long tableId) {

		Optional<Long> optionalReservationId = reservationRepository.findActiveReservationIdByTableId(tableId);
		return optionalReservationId.isPresent() ? optionalReservationId.get() : null;
	}

	@Override
	public Long findTableIdByReservationId(Long reservationId) {
		Optional<Long> foundTableId = reservationRepository.findTableIdByReservationId(reservationId);
		
		return foundTableId.isPresent() ? foundTableId.get() : null;
	}

	@Override
	public UpdatedReservation changeOwner(String ownerName, Long reservationId) {
		
		UpdatedReservation updatedReservation = new UpdatedReservation();
		Owner owner = ownerRepository.findByName(ownerName);
		Long tableId = null;
		
		if(reservationRepository.existsById(reservationId)) {
			
			if (Objects.isNull(owner)) {

				Optional<Reservation> findById = reservationRepository.findById(reservationId);

				Reservation reservation = findById.get();
				tableId = reservation.getTableId();

				Owner createdOwner = ObjectConverters.createOwner(ownerName, reservation);
				owner = ownerRepository.save(createdOwner);

			}
			
			Integer updatedRows = reservationUpdateRepository.updateOwnerReservation(reservationId, owner);
			updatedReservation = ObjectConverters.toUpdatedReservation(tableId, reservationId, updatedRows);
			
		}
		
		return updatedReservation;
	}

	@Override
	public UpdatedReservation updatePeople(Long reservationId, Long numberOfNewPeople) {
		
		Optional<Reservation> findById = reservationRepository.findById(reservationId);
		UpdatedReservation updatedReservation = null;
		Long tableId = null;
		
		if(findById.isPresent()) {
			Reservation reservation = findById.get();
			numberOfNewPeople +=reservation.getNumberOfPeople();
			tableId = reservation.getTableId();
			
			Integer updatedRows = reservationUpdateRepository.updatePeopleToReservation(reservationId, numberOfNewPeople);
			updatedReservation = ObjectConverters.toUpdatedReservation(tableId,reservationId, updatedRows);
		}
		
		return updatedReservation;
	}

	@Override
	public Integer changeStartDateReservation(Long reservationId, Date changedStartDate) {
		Integer updatedRows = reservationUpdateRepository.updateStartDateReservation(reservationId, changedStartDate);
		return updatedRows;
	}

	@Override
	public Integer changeEndDateReservation(Long reservationId, Date changedEndDate) {
		Integer updatedRows = reservationUpdateRepository.updateEndDateReservation(reservationId, changedEndDate);
		return updatedRows;
	}

	@Override
	public UpdatedReservation changeTableForReservation(Long reservationId, Long numberOfTable) {

		boolean exist = tableRepository.existsById(numberOfTable); 
		if(exist) {
			
			Optional<Reservation> findById = reservationRepository.findById(reservationId);
			if(findById.isPresent()) {
				Reservation reservation = findById.get();
				ReservationStatusType reservationStatus = reservation.getReservationStatus();
				StatusType status = StatusType.mappedFrom(reservationStatus);
				
				Table table = null;
				
				if(!exist) 
				{
					table = createTable(status);
					tableRepository.save(table);
				}
				else 
				{
					table = tableRepository.findById(numberOfTable).get();
					if(StatusType.isUsed(table.getStatus())) {
						table = createTable(StatusType.BUSY);
						tableRepository.save(table);
					}
				}
				
				numberOfTable = table.getId();
			}
		}
		
		Integer updatedRows = reservationUpdateRepository.updateTableForReservation(reservationId, numberOfTable);
		UpdatedReservation updatedReservation = ObjectConverters.toUpdatedReservation(numberOfTable, 
																					  reservationId, updatedRows);
		
		return updatedReservation;
	}
	
	private Table createTable(StatusType status) {
		
		Table table = new Table();
		
		Long maxId = tableRepository.getMaxId();
		table.setId(maxId + 1);
		
		table.setStatus(status);
		
		return table;
	}

	@Override
	public Integer updateReservationDate(Long reservationId, Date changedStartDate, Date changedEndDate) {
		Integer updatedRows = reservationUpdateRepository.updateDateReservation(reservationId, changedStartDate, changedEndDate);

		return updatedRows;
	}

}
