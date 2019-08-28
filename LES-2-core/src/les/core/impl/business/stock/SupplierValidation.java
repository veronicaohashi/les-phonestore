package les.core.impl.business.stock;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.stock.Entry;

public class SupplierValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Entry){
			Entry entry = (Entry)entity;
			
			if(entry.getItems() == null) {
				return "A entrada deve possuir um ou mais itens";
			}
		}
		return null;
	}
}

