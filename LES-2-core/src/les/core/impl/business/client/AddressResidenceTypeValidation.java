package les.core.impl.business.client;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.client.Address;
import les.domain.client.ResidenceType;
import les.domain.sale.OrderAddress;

public class AddressResidenceTypeValidation implements IStrategy {

	public String process(DomainEntity entity) {
		ResidenceType residence = null;
		
		if(entity instanceof Address){			
			Address address = (Address)entity;
			residence = address.getResidenceType();
			
		} else if(entity instanceof OrderAddress){			
			OrderAddress orderAddress = (OrderAddress)entity;
			residence = orderAddress.getAddress().getResidenceType();	
		}
	
		if(residence == null)
			return "O endereço deve estar associado a um tipo de residência";
		else 
			return null;
	}

}
