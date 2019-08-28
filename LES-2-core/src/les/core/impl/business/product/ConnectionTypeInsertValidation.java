package les.core.impl.business.product;

import java.sql.SQLException;
import java.util.List;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.product.ConnectionTypeDAO;
import les.domain.DomainEntity;
import les.domain.product.ConnectionType;
import les.domain.product.Phone;

public class ConnectionTypeInsertValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Phone){
			Phone phone = (Phone)entity;
			IDAO dao = new ConnectionTypeDAO();
			
			List<ConnectionType> connections = phone.getConnectionType();

			
			for(ConnectionType c : connections) {
				ConnectionType ct = new ConnectionType();
				
				ct.setId(c.getId());
				ct.setPhone(phone);
				
				try {
					dao.save(ct);
				} catch (SQLException e) {
					e.printStackTrace();
				}						
			}				
		}		
		return null;
	}
}
