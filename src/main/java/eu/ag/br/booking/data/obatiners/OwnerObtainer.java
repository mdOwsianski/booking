/**
 * 
 */
package eu.ag.br.booking.data.obatiners;

import java.util.Objects;

import eu.ag.br.booking.common.ActionType;
import eu.ag.br.booking.common.ObjectConverters;
import eu.ag.br.booking.data.dto.ObtainedOwner;
import eu.ag.br.booking.entities.Owner;
import eu.ag.br.booking.service.OwnerService;

/**
 * @author MOwsians
 *
 */
public class OwnerObtainer {
	
	public static OwnerObtainer create(OwnerService ownerService, String ownerName) {
		return new OwnerObtainer(ownerService, ownerName);
	}
	
	private OwnerService ownerService;
	private String ownerName;
	private ActionType action;
	
	private OwnerObtainer(OwnerService ownerService, String ownerName) {
		this.ownerService = ownerService;
		this.ownerName = ownerName;
	}
	
	public Owner findOwnerByName() {
		
		Owner owner = ownerService.findByName(ownerName);
		return owner;
	}
	
	public ObtainedOwner createOwner() {
		
		Owner owner = new Owner();
		owner.setName(ownerName);
		
		Owner savedOwner = save(owner);
		setCreatedAction();
		
		ObtainedOwner convertedOwner = convertOwnerToObtainedOwnerTO(savedOwner);
		
		return convertedOwner;
	}
	
	private Owner getOwnerIfExist(){
		Owner obtainedOwnerByName = ownerService.findByName(ownerName);
		
		setGetAction();
		
		return obtainedOwnerByName;
	}
	
	public Owner save(Owner owner) {
		
		return ownerService.save(owner);
	}
	
	private void setCreatedAction() {
		action = ActionType.CREATED;
	}
	
	private void setGetAction() {
		action = ActionType.GET;
	}
	
	public ActionType getAction() {
		return Objects.isNull(action) ? ActionType.UKNOW : action;
	}
	
	private ObtainedOwner convertOwnerToObtainedOwnerTO(Owner owner){
		return ObjectConverters.toObtainedOwnerTO(owner, getAction());
	}

	/**
	 * @param ownerName2
	 * @return 
	 */
	public ObtainedOwner getOrCreateIfNotExist() {
		
		Owner obtainedOwnerByName = getOwnerIfExist();
		ObtainedOwner obtainedOwnerTO = Objects.isNull(obtainedOwnerByName) 
												  ? createOwner() 
												  : convertOwnerToObtainedOwnerTO(obtainedOwnerByName);
		
		
		return obtainedOwnerTO;
	}

}