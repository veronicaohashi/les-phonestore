package les.core.impl.business.sale;

import java.sql.SQLException;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.sale.ReservedStockDAO;
import les.core.impl.dao.stock.StockDAO;
import les.domain.DomainEntity;
import les.domain.sale.Cart;
import les.domain.sale.Orderi;
import les.domain.stock.Stock;

public class ReserveItemValidation implements IStrategy {
	
	public String process(DomainEntity entity) {
		if(entity instanceof Cart) {			
			Cart cart = (Cart)entity;
			Orderi item = cart.getStorageItem();
			Stock stock = new Stock();
			stock.setReference(item.getReference());
			
			IDAO dao = new ReservedStockDAO();		
			IDAO stockDAO = new StockDAO();	
			
			if(item.getReference() != null) {					
				try {
					DomainEntity stockItem = stockDAO.consult(stock).get(0);
					Stock s = (Stock)stockItem;					
					stock.setReserved(s.getReserved() + item.getQuantity());
					dao.update(stock);		
					
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}	
		}
		return null;
	}
}
