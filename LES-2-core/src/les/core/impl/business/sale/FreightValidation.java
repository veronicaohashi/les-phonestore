package les.core.impl.business.sale;

import java.sql.SQLException;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.sale.FreightDAO;
import les.domain.DomainEntity;
import les.domain.sale.Freight;
import les.domain.sale.OrderAddress;

public class FreightValidation implements IStrategy {
	
	public String process(DomainEntity entity) {
		if(entity instanceof OrderAddress){
			OrderAddress orderAddress = (OrderAddress)entity;	
			Freight freight = orderAddress.getFreight();
			IDAO dao = new FreightDAO();
			
			if(freight == null)
				return "O frete deve ser preenchido!";
			else
				try {
					Freight f = (Freight)dao.consult(freight).get(0);
					orderAddress.getFreight().setPrice(f.getPrice());
					orderAddress.getFreight().setDescription(f.getDescription());
				} catch(SQLException e) {
					e.printStackTrace();
				}
				
		
		}
		return null;
	}
}
