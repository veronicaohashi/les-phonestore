package les.core.impl.business.product;

import java.util.List;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.product.Phone;
import les.domain.product.Reference;

public class ProductReferenceValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Phone){
			Phone phone = (Phone)entity;
			
			List<Reference> reference = phone.getReference();		

			if(reference.isEmpty()) {
				return "O celular deve possuir uma ou mais referências!";
			}			
		}		
		return null;
	}
}
