package les.core.impl.business.client;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.client.Address;

public class RequiredFieldAddressValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Address){
			Address address = (Address)entity;
			
			String name = address.getName();
			String postalCode = address.getPostalCode();
			String street = address.getStreet();
			String number = address.getNumber();
			String district = address.getDistrict();
			
			StringBuilder msg = new StringBuilder();
			if(name.equals("") ) 
				msg.append("O nome deve ser preenchido!");
			
			if(postalCode.equals("") ) 
				msg.append("O CEP deve ser preenchido!");
				
			if(street.equals("") ) 
				msg.append("O logradouro deve ser preenchido!");

			if(number.equals("") ) 
				msg.append("O número deve ser preenchido!");
									
			if(district.equals("") ) 
				msg.append("O bairro deve ser preenchido!");
			
			if(msg.length() > 0)
				return msg.toString();
		}		
		return null;
	}

}
