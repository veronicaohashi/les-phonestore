package les.core.impl.business.stock;

import java.sql.SQLException;
import java.util.List;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.sale.OrderiDAO;
import les.core.impl.dao.stock.StockDAO;
import les.domain.DomainEntity;
import les.domain.sale.Orderi;
import les.domain.stock.Stock;

public class ExchangeStockValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Orderi){
			Orderi orderi = (Orderi)entity;			
			IDAO dao = new OrderiDAO();
			IDAO stockDAO = new StockDAO();
			
			if (!orderi.getOrderiIds().isEmpty() && orderi.getStatus().getId() == 8) {
				try {
					List<DomainEntity> items = dao.consult(orderi);	
					
					for(DomainEntity o : items) {
						List<DomainEntity> stocks = stockDAO.consult(new Stock(((Orderi)o).getReference()));
						for(DomainEntity s : stocks) {	
							Stock stock = new Stock();

							if(((Orderi)o).getExchangeCategory().getId() != 1) {
								

								stock.setId(s.getId());
								stock.setReference(((Orderi)o).getReference());
								stock.setQuantity(((Stock)s).getQuantity() + ((Orderi)o).getQuantity());
								stock.setReserved(((Stock)s).getReserved());
									
								stockDAO.update(stock);		
							}
												
							
						}							
					}									
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}		
		}		
		return null;
	}
}

