package les.core.impl.business.stock;

import java.sql.SQLException;
import java.util.List;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.stock.MovstockDAO;
import les.domain.DomainEntity;
import les.domain.stock.Entry;
import les.domain.stock.Entryi;
import les.domain.stock.Movstock;
import les.domain.stock.MovstockType;

public class EntryMovstockValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Entry){
			Entry entry = (Entry)entity;
						
			IDAO dao = new MovstockDAO();
			MovstockType movstockType = new MovstockType();
			
			movstockType.setId(1);
			
			List<Entryi> items = entry.getItems();
			
			if (! items.isEmpty()) {
				
				for(Entryi i : items) {
					Movstock mov = new Movstock();
					
					mov.setPrice(i.getPrice());
					mov.setQuantity(i.getQuantity());
					mov.setDate(entry.getDate());
					mov.setSupplier(entry.getSupplier());
					mov.setReference(i.getReference());
					mov.setMovstockType(movstockType);
										
					try {
						dao.save(mov);
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}				
			}
		} 
		
		return null;
	}
}

