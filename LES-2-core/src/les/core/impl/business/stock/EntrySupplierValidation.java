package les.core.impl.business.stock;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.stock.Entry;

public class EntrySupplierValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Entry){
			Entry entry = (Entry)entity;
			
			if(entry.getSupplier() == null) {
				return "A entrada deve estar associada a um fornecedor";
			}
		}
		return null;
	}
}

