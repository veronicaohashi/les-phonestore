package les.core.impl.business.client;

import java.sql.SQLException;
import java.util.List;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.client.ClientDAO;
import les.domain.DomainEntity;
import les.domain.client.Client;

public class ClientCpfUniqueValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Client){
			Client client = (Client)entity;
			IDAO dao = new ClientDAO();
			
			try {
				List<DomainEntity> c = dao.consult(new Client(client.getCpf()));
				if(! c.isEmpty()) {
					return "Este CPF já está vinculado a um usuário!";
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}				
		}		
		return null;
	}

}
