package les.core.impl.business.client;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.client.Address;
import les.domain.sale.OrderAddress;

public class AddressRequiredFieldValidation implements IStrategy {

	public String process(DomainEntity entity) {
		String name = "";
		String postalCode = "";
		String street = "";
		String number = "";
		String district = "";		
		
		if(entity instanceof Address){
			Address address = (Address)entity;			
			name = address.getName();
			postalCode = address.getPostalCode();
			street = address.getStreet();
			number = address.getNumber();
			district = address.getDistrict();
			
		} else if(entity instanceof OrderAddress){
			OrderAddress orderAddress = (OrderAddress)entity;			
			name = orderAddress.getAddress().getName();
			postalCode = orderAddress.getAddress().getPostalCode();
			street = orderAddress.getAddress().getStreet();
			number = orderAddress.getAddress().getNumber();
			district = orderAddress.getAddress().getDistrict();
		}
			
		
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
		else 
			return null;
	}

}
