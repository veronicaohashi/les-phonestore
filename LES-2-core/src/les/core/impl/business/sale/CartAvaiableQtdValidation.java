package les.core.impl.business.sale;

import java.sql.SQLException;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.stock.StockDAO;
import les.domain.DomainEntity;
import les.domain.product.Reference;
import les.domain.sale.Cart;
import les.domain.stock.Stock;

public class CartAvaiableQtdValidation implements IStrategy {
	
	public String process(DomainEntity entity) {
		if(entity instanceof Cart) {
			Cart cart = (Cart)entity;
			IDAO dao = new StockDAO();
			
			Reference reference = cart.getStorageItem().getReference();
			if(reference != null) {								
				try {
					DomainEntity stock = dao.consult(new Stock(reference)).get(0);
					Stock s = (Stock)stock;
					if(s.getQuantity() - s.getReserved() < cart.getStorageItem().getQuantity()) {
						return "Produto não está mais disponível em estoque";
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}	
		}
		return null;
	}
}
