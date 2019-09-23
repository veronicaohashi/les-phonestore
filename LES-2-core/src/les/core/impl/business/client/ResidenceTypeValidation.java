package les.core.impl.business.client;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.client.Address;

public class ResidenceTypeValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Address){
			
			Address address = (Address)entity;			
			if(address.getResidenceType() == null) {
				return "O endereço deve estar associado a um tipo de residência";
			}
		}
		return null;
	}

}
