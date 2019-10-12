package les.core.impl.business.product;

import java.sql.SQLException;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.product.PricingGroupDAO;
import les.domain.DomainEntity;
import les.domain.product.Phone;
import les.domain.product.PricingGroup;

public class ProductPriceValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Phone){
			Phone phone = (Phone)entity;
			IDAO pricingGroupDAO = new PricingGroupDAO();
			
			try {
				DomainEntity group = pricingGroupDAO.consult(new PricingGroup(phone.getPricingGroup().getId())).get(0);
				Double minPrice = phone.getCostPrice() + Math.round(((PricingGroup)group).getPercentage()/100 * phone.getCostPrice());
				
				if(minPrice > phone.getSalePrice())
					return "Apenas o gerente de vendas pode realizar definir um valor de venda menor que a margem de lucro"; 
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return null;
	}
}
