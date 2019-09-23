package les.core.impl.business.sale;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.sale.Cart;

public class CartValidation implements IStrategy {
	
	public String process(DomainEntity entity) {
		if(entity instanceof Cart){
			Cart cart = (Cart)entity;
			
			if(cart.getStorageItem().getReference() == null) 
				return "Selecione uma cor e capacidade para continuar!";
		}
		
		return null;
	}
}
