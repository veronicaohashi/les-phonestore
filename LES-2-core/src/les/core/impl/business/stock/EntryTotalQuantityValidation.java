package les.core.impl.business.stock;

import java.util.ArrayList;
import java.util.List;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.stock.Entry;
import les.domain.stock.Entryi;

public class EntryTotalQuantityValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Entry){
			Entry entry = (Entry)entity;
			
			List<Entryi> items = new ArrayList<Entryi>();
			items = entry.getItems();
			Integer sum = 0;
			
			for(Entryi e : items) {
				sum = sum + e.getQuantity();
			}
			
			entry.setQuantity(sum);
		}
		return null;
	}
}
