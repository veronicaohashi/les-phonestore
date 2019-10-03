package les.core.impl.business.sale;

import java.sql.SQLException;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.product.ReferenceDAO;
import les.domain.DomainEntity;
import les.domain.product.Reference;
import les.domain.sale.Cart;
import les.domain.sale.Orderi;

public class CartNewItemValidation implements IStrategy {
	
	public String process(DomainEntity entity) {
		if(entity instanceof Cart) {	
			Cart cart = (Cart)entity;
			Orderi orderi = cart.getStorageItem();			
			IDAO referenceDAO = new ReferenceDAO();
			
			if(orderi.getReference() != null) {					
				try {					
					DomainEntity reference = referenceDAO.consult(orderi.getReference()).get(0);
					Reference r = (Reference)reference;
					
					orderi.setReference(r);			
					orderi.setPrice(r.getPhone().getSalePrice());		
					Orderi itemCart = cart.getItem(orderi);
					
					if(itemCart.getQuantity() != null) {
						orderi.setQuantity(orderi.getQuantity() + itemCart.getQuantity());
						cart.removeItem(itemCart);
					}
					cart.addItem(orderi);				
					
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}	
		}
		return null;
	}
}
