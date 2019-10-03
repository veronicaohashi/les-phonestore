package les.core.impl.business.stock;

import java.sql.SQLException;
import java.util.List;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.stock.StockDAO;
import les.domain.DomainEntity;
import les.domain.sale.Order;
import les.domain.sale.Orderi;
import les.domain.stock.Stock;

public class OrderStockValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Order){
			Order order = (Order)entity;
			IDAO dao = new StockDAO();
			
			List<Orderi> items = order.getItems();
			
			if (! items.isEmpty()) {				
				for(Orderi i : items) {					
					Stock stock = new Stock();
					stock.setReference(i.getReference());
					stock.setQuantity(i.getQuantity());
										
					try {
						List<DomainEntity> stocks = dao.consult(stock);
						
						Stock s = (Stock)stocks.get(0);

						stock.setId(s.getId());
						stock.setQuantity(s.getQuantity() - i.getQuantity());
						stock.setReserved(s.getReserved() - i.getQuantity());
						
						dao.update(stock);							
					
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}				
			}
		}
		
		return null;
	}
}

