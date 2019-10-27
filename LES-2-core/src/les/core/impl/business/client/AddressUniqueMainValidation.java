package les.core.impl.business.client;

import java.sql.SQLException;
import java.util.List;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.client.AddressDAO;
import les.domain.DomainEntity;
import les.domain.client.Address;

public class AddressUniqueMainValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Address){
			Address address = (Address)entity;
			IDAO dao = new AddressDAO();
			
			if(address.getClient() != null) {
				try {
					List<DomainEntity> addresses = dao.consult(new Address(address.getClient(), address.getLmain()));
					if(addresses.size() > 0){
						if(addresses.get(0).getId() != address.getId() && address.getLmain()) {
							Address a = (Address)addresses.get(0);
							a.setLmain(false);
							
						} else if(addresses.get(0).getId() == address.getId() && ! address.getLmain()) {
							return "Você deve possuir apenas um endereço principal!";					
						}
					} else {
						address.setLmain(true);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
		}
		
		return null;
	}

}
