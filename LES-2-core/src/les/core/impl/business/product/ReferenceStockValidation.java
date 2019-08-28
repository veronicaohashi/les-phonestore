package les.core.impl.business.product;

import java.sql.SQLException;
import java.util.List;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.stock.StockDAO;
import les.domain.DomainEntity;
import les.domain.product.Reference;
import les.domain.stock.Stock;

public class ReferenceStockValidation implements IStrategy {

	public String process(DomainEntity entity) {
		Reference reference = (Reference)entity;			
		IDAO dao = new StockDAO();
		
		try {
			Stock stock = new Stock();
			stock.setReference(reference);
			
			List<DomainEntity> stocks = dao.consult(stock);
			
			if(stocks.size() > 0) {
				
				return "Referência em estoque não pode ser deletada!";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}
}
