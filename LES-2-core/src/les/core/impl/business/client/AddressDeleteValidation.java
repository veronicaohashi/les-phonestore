package les.core.impl.business.client;

import java.sql.SQLException;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.client.AddressDAO;
import les.domain.DomainEntity;
import les.domain.client.Address;

public class AddressDeleteValidation implements IStrategy {

	public String process(DomainEntity entity) {				
		if(entity instanceof Address){	
			Address address = (Address)entity;	
			IDAO dao = new AddressDAO();
			
			try {
				DomainEntity addresses = dao.consult(new Address(address.getClient(), true)).get(0);	
				if(addresses.getId() == address.getId()) {
					return "Você não pode excluir o endereço principal!";		
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} 		
		
		return null;
	}

}
