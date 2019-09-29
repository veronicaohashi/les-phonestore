package les.core.impl.business.product;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.product.Phone;

public class ProductConnectionTypeValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Phone){
			Phone phone = (Phone)entity;			

			if(phone.getConnectionType() == null) {
				return "O celular deve estar associado a um, ou mais, tipos de conexão";
			}
		}		
		return null;
	}
}
