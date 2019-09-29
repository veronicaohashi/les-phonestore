package les.core.impl.business.product;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.product.Phone;

public class ProductSOValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Phone){
			Phone phone = (Phone)entity;

			if(phone.getSo() == null) {
				return "O celular deve estar associado a um sistema operacional";
			}			
		}		
		return null;
	}
}
