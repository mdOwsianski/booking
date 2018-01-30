/**
 * 
 */
package eu.ag.br.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.ag.br.booking.common.ObjectConverters;
import eu.ag.br.booking.entities.Owner;
import eu.ag.br.booking.repository.OwnerRepository;
import eu.ag.br.booking.service.OwnerService;
import eu.ag.br.booking.ws.rest.response.dto.find.FindOwnerDTO;

/**
 * @author MOwsians
 *
 */
@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	OwnerRepository ownerRepository;
	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.OwnerService#findByName(java.lang.String)
	 */
	@Override
	public Owner findByName(String ownerName) {
		Owner owner = ownerRepository.findByName(ownerName);
		
		return owner;
	}
	/* (non-Javadoc)
	 * @see eu.ag.br.booking.service.OwnerService#save(eu.ag.br.booking.entities.Owner)
	 */
	@Override
	public Owner save(Owner owner) {
		return ownerRepository.save(owner);
	}
	@Override
	public List<FindOwnerDTO> findAll() {
		
		List<Owner> findAll = ownerRepository.findAll();
		return ObjectConverters.toFindOwnerDTOs(findAll);
	}

}
