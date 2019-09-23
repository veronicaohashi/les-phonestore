package les.core.impl.business.sale;

import java.sql.SQLException;
import java.util.List;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.product.PhoneDAO;
import les.domain.DomainEntity;
import les.domain.product.Phone;
import les.domain.stock.Entry;
import les.domain.stock.Entryi;

public class GeneratePriceValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Entry){
			Entry entry = (Entry)entity;						
			IDAO dao = new PhoneDAO();
			double highestPrice = 0.0;			
			
			List<Entryi> items = entry.getItems();
			
			if (! items.isEmpty()) {
				for(Entryi entryi : items) {
					if(entryi.getPrice() > highestPrice) {
						highestPrice = entryi.getPrice();
					}
				}
				
				Entryi i = (Entryi)items.get(0);				
				Phone phone = new Phone();
				phone.setId(i.getReference().getPhone().getId());
				try {
					List<DomainEntity> phones = dao.consult(phone);
					Phone p = (Phone)phones.get(0);
					
					if(p.getCostPrice() < i.getPrice()) {
						p.setCostPrice(highestPrice);
						p.setSalePrice(highestPrice + Math.round(p.getPricingGroup().getPercentage()/100 * highestPrice));

						dao.update(p);					
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}				
		
		}
		
		return null;
	}
}

