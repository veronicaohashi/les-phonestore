package les.core.impl.business.stock;

import java.util.ArrayList;
import java.util.List;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.stock.Entry;
import les.domain.stock.Entryi;

public class EntryTotalPriceValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Entry){
			Entry entry = (Entry)entity;
			List<Entryi> items = new ArrayList<Entryi>();
			items = entry.getItems();
			Double sum = 0.0;
			
			for(Entryi e : items) {
				sum = sum + e.getPrice();
			}
			
			sum = sum * entry.getQuantity();			
			entry.setPrice(sum);
		}
		return null;
	}
}
