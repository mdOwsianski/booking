/**
 * 
 */
package eu.ag.br.booking.service;

import java.util.List;

import eu.ag.br.booking.entities.Owner;
import eu.ag.br.booking.ws.rest.response.dto.find.FindOwnerDTO;

/**
 * @author MOwsians
 *
 */
public interface OwnerService {

	/**
	 * @param ownerName
	 * @return
	 */
	Owner findByName(String ownerName);
	Owner save(Owner owner);
	List<FindOwnerDTO> findAll();

}
