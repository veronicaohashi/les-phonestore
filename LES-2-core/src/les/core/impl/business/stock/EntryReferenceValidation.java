package les.core.impl.business.stock;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.stock.Entry;
import les.domain.stock.Entryi;

public class EntryReferenceValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Entry){
			Entry entry = (Entry)entity;
			
			if(entry.getItems() == null) {
				return "A entrada deve possuir um ou mais itens";
			} else {
				for(Entryi i : entry.getItems()) {
					if(i.getQuantity() !=  0 || i.getPrice() != 0) {
						if(i.getQuantity() <= 0)
							return "Não é possivel dar entrada com quantidade menor ou igual a zero!";
						if(i.getPrice() <= 0)
							return "Não é possivel dar entrada com preço menor ou igual a zero!";
					}
				}
			}
				
		}
		return null;
	}
}

