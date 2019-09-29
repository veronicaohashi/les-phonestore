package les.core.impl.business.stock;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.stock.Entry;

public class EntryRequiredFieldValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Entry){
			Entry entry = (Entry)entity;
			
			String date = entry.getDate();

			StringBuilder msg = new StringBuilder();
			
			if(date.equals("") ) 
				msg.append("A data deve ser preenchida!");
			
			if(msg.length() > 0)
				return msg.toString();
		}		
		return null;
	}
}
