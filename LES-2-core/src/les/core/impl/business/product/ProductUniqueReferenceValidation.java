package les.core.impl.business.product;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.product.ReferenceDAO;
import les.domain.DomainEntity;
import les.domain.product.Capacity;
import les.domain.product.Color;
import les.domain.product.Phone;
import les.domain.product.Reference;

public class ProductUniqueReferenceValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Phone){
			Phone phone = (Phone)entity;
			IDAO dao = new ReferenceDAO();
			
			if(phone.getActivationCategory() == null) {			
				List<Reference> references = phone.getReference();
				if(!references.isEmpty()) {
					
					Map<Color, List<Capacity>> cc = phone.getReference().get(0).getColorCapacity();	
					StringBuilder msg = new StringBuilder();
					
					for (Map.Entry<Color, List<Capacity>> entry : cc.entrySet()) {
						
						for(Capacity c : entry.getValue()) {
							String name = "REF-" + phone.getId() + "-" + entry.getKey().getId() + "-" + c.getId();
	
							Reference r = new Reference();
							r.setName(name);
							r.setColor(entry.getKey());
							r.setCapacity(c);
							r.setPhone(phone);
							
							try {
								List<DomainEntity> reference = dao.consult(r);
								if(reference.size() == 0) {
									try {
										dao.save(r);
									} catch (SQLException e) {
										e.printStackTrace();
									}
								} else {
									msg.append("Referência " + name + " não foi gerada pois já existe!"+ System.lineSeparator());
	
								}						
							} catch (SQLException e) {
								e.printStackTrace();
							}					
						}
					}
					
					if(msg.length() != 0) {
						return msg.toString();
					}
				}				
			}
		}		
		return null;
	}
}
