package eu.ag.br.booking.ws.rest.controller.booking.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.ag.br.booking.common.BRAGLogger;
import eu.ag.br.booking.common.ObjectResponseConverters;
import eu.ag.br.booking.service.OwnerService;
import eu.ag.br.booking.ws.rest.controller.booking.IFindOwnerController;
import eu.ag.br.booking.ws.rest.controller.booking.common.IRequestMappings;
import eu.ag.br.booking.ws.rest.response.FindOwnerResponse;
import eu.ag.br.booking.ws.rest.response.dto.find.FindOwnerDTO;

/**
 * 
 * @author MOwsians
 *
 */
@RestController
@RequestMapping(IRequestMappings.API_VERSION_FIND_OWNER)
public class FindOwnerController implements IFindOwnerController {

	@Autowired
	OwnerService ownerService;
	
	private static BRAGLogger logger = BRAGLogger.newInstance(FindOwnerController.class);
	
	@Override
	public ResponseEntity<FindOwnerResponse> findAll() {
		
		logger.info(" Find all owners ");
		List<FindOwnerDTO> findAll = ownerService.findAll();
		
		ResponseEntity<FindOwnerResponse> findOwnerResponse = ObjectResponseConverters.toFindOwnerResponse(findAll);
		logger.info(findOwnerResponse);
		
		return findOwnerResponse;
	}

}
