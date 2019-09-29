package les.core.impl.business.stock;

import java.sql.SQLException;
import java.util.List;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.stock.MovstockDAO;
import les.domain.DomainEntity;
import les.domain.sale.Order;
import les.domain.sale.Orderi;
import les.domain.stock.Movstock;
import les.domain.stock.MovstockType;

public class OrderMovstockValidation implements IStrategy {

	public String process(DomainEntity entity) {
//		if(entity instanceof Order){
//			Order order = (Order)entity;
//						
//			IDAO dao = new MovstockDAO();
//			MovstockType movstockType = new MovstockType();
//			
//			movstockType.setId(2);
//			
//			List<Orderi> items = order.getItems();
//			
//			if (! items.isEmpty()) {
//				
//				for(Orderi o : items) {
//					Movstock mov = new Movstock();
//					
//					mov.setPrice(o.getPrice());
//					mov.setQuantity(o.getQuantity());
//					mov.setDate(order.getDate());
//					mov.setReference(o.getReference());
//					mov.setMovstockType(movstockType);
//										
//					try {
//						dao.save(mov);
//						
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}				
//			}
//		} 
		
		return null;
	}
}

