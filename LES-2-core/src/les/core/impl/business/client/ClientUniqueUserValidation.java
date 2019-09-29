package les.core.impl.business.client;

import java.sql.SQLException;
import java.util.List;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.client.UserDAO;
import les.domain.DomainEntity;
import les.domain.client.Client;
import les.domain.client.User;

public class ClientUniqueUserValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Client){
			Client client = (Client)entity;
			IDAO dao = new UserDAO();
			
			try {
				List<DomainEntity> user = dao.consult(new User(client.getUser().getEmail()));				
				if(! user.isEmpty() && user.get(0).getId() != client.getUser().getId()) {
					return "E-mail já cadastrado!";
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}				
		}		
		return null;
	}

}
