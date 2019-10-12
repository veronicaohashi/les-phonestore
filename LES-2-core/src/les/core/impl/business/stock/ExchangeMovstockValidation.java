package les.core.impl.business.stock;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.sale.OrderiDAO;
import les.core.impl.dao.stock.MovstockDAO;
import les.domain.DomainEntity;
import les.domain.sale.Orderi;
import les.domain.stock.Movstock;
import les.domain.stock.MovstockType;

public class ExchangeMovstockValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Orderi){
			Orderi orderi = (Orderi)entity;		
			IDAO dao = new OrderiDAO();
			IDAO movstockDAO = new MovstockDAO();
			List<Movstock> newMovs = new ArrayList<Movstock>();
			
			if (!  orderi.getOrderiIds().isEmpty() && orderi.getStatus().getId() == 8) {
				try {
					List<DomainEntity> items = dao.consult(orderi);	
					
					for(DomainEntity o : items) {
						List<DomainEntity> movs = movstockDAO.consult(new Movstock(((Orderi)o).getReference(), ((Orderi)o).getOrder().getId()));
						for(DomainEntity m : movs) {	
							Movstock mov = new Movstock();

							if(((Orderi)o).getExchangeCategory().getId() == 1) {
								mov.setMovstockType(new MovstockType(3));								

							} else {
								mov.setMovstockType(new MovstockType(1));								

							}
							mov.setPrice(((Movstock)m).getPrice());
							mov.setQuantity(((Movstock)m).getQuantity());
							mov.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));						
							mov.setReference(((Movstock)m).getReference());
							mov.setSupplier(((Movstock)m).getSupplier());
							mov.setOrigin(((Movstock)m).getOrigin());
								
							newMovs.add(mov);
							
						}							
					}									
				} catch (SQLException e) {
					e.printStackTrace();
				}
								
				if(! newMovs.isEmpty()) {
					for(Movstock m : newMovs) {
						try {			
							movstockDAO.save(m);
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

