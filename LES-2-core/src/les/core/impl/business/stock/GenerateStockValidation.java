package les.core.impl.business.stock;

import java.sql.SQLException;
import java.util.List;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.stock.StockDAO;
import les.domain.DomainEntity;
import les.domain.stock.Entry;
import les.domain.stock.Entryi;
import les.domain.stock.Stock;

public class GenerateStockValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Entry){
			Entry entry = (Entry)entity;
			IDAO dao = new StockDAO();
			
			List<Entryi> items = entry.getItems();
			
			if (! items.isEmpty()) {				
				for(Entryi i : items) {					
					Stock stock = new Stock();
					stock.setReference(i.getReference());
					stock.setQuantity(i.getQuantity());
					stock.setSupplier(entry.getSupplier());
										
					try {
						List<DomainEntity> stocks = dao.consult(stock);
						
						if(stocks.isEmpty()) {							
							dao.save(stock);
						} else {	
							Stock s = (Stock)stocks.get(0);

							Integer newQuantity = s.getQuantity() + i.getQuantity();
							stock.setQuantity(newQuantity);
							stock.setId(s.getId());
							
							dao.update(stock);							
						}
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}				
			}
		}
		
		return null;
	}
}

