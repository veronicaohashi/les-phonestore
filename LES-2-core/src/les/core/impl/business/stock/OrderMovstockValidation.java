package les.core.impl.business.stock;

import java.sql.SQLException;
import java.util.ArrayList;
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
		if(entity instanceof Order){
			Order order = (Order)entity;
						
			IDAO dao = new MovstockDAO();
			
			List<Orderi> items = order.getItems();
			List<Movstock> newMovs = new ArrayList<Movstock>();
			
			if (! items.isEmpty()) {
				for(Orderi o : items) {
					try {					
						List<DomainEntity> movs = dao.consult(new Movstock(o.getReference(), new MovstockType(1)));
						Integer quantity = o.getQuantity();
						
						for(DomainEntity m : movs) {							
							if(quantity == 0) {
								break;
							} else {
								Movstock mov = new Movstock();
								
								if(((Movstock)m).getQuantity() < quantity) {
									mov.setQuantity(((Movstock)m).getQuantity());	
								} else {
									mov.setQuantity(quantity);
								}
								
								quantity = quantity - mov.getQuantity();
								
								mov.setPrice(o.getPrice());
								mov.setDate(order.getDate());
								mov.setReference(o.getReference());
								mov.setMovstockType(new MovstockType(2));
								mov.setSupplier(((Movstock)m).getSupplier());
								mov.setOrigin(order.getId());
								
								newMovs.add(mov);
							}							
						}					
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
						
				}
				

				
				if(newMovs.isEmpty()) 
					return "Erro ao gerar movimentação no estoque";
				else {
					for(Movstock m : newMovs) {
						try {			
							dao.save(m);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}							
				}				
			}
		} 
		
		return null;
	}
}

