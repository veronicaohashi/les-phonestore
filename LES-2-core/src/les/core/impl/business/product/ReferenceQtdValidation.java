package les.core.impl.business.product;

import java.sql.SQLException;
import java.util.List;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.product.ReferenceDAO;
import les.domain.DomainEntity;
import les.domain.product.Reference;

public class ReferenceQtdValidation implements IStrategy {

	public String process(DomainEntity entity) {
		Reference reference = (Reference)entity;			
		IDAO dao = new ReferenceDAO();
		
		try {
			List<DomainEntity> references = dao.consult(reference);
			
			if(references.size() == 1) {
				
				return "O celular deve possuir uma ou mais referências!";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}
}
