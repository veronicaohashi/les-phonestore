package les.core.impl.business.product;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.product.Phone;

public class ProductPricingGroupValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Phone){
			Phone phone = (Phone)entity;

			if(phone.getPricingGroup() == null) {
				return "O celular deve estar associado a um grupo de precificação";
			}			
		}		
		return null;
	}
}
