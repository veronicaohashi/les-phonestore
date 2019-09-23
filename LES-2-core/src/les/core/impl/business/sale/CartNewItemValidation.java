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
			Orderi salei = cart.getStorageItem();			
			IDAO referenceDAO = new ReferenceDAO();
			
			if(salei.getReference() != null) {					
				try {
					
					DomainEntity reference = referenceDAO.consult(salei.getReference()).get(0);
					Reference r = (Reference)reference;
					salei.setReference(r);			
					salei.setPrice(r.getPhone().getSalePrice());		
					Orderi itemCart = cart.getItem(salei);
					
					if(itemCart.getQuantity() != null) {
						salei.setQuantity(salei.getQuantity() + itemCart.getQuantity());
						cart.removeItem(itemCart);
					}
					cart.addItem(salei);				
					
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}	
		}
		return null;
	}
}
