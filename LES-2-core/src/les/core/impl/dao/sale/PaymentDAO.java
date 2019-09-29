package les.core.impl.dao.sale;

import java.util.ArrayList;
import java.util.List;
import les.core.impl.dao.AbstractJdbcDAO;
import les.core.impl.dao.client.CreditCardDAO;
import les.domain.DomainEntity;
import les.domain.client.CreditCard;
import les.domain.sale.Payment;
import les.domain.sale.PaymentData;

public class PaymentDAO extends AbstractJdbcDAO{
    
	public PaymentDAO() {
		super("payments", "id");	
	}

	@Override
	public void save(DomainEntity entity) {	
		// Não executa nada pois vai salvar depois de finalizar o pedido	
	}
	
	@Override
	public void update(DomainEntity entity) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void delete(DomainEntity entity) {	
	}	

	@Override
	public List<DomainEntity> consult(DomainEntity entity) {
		Payment payment = (Payment)entity;
		CreditCardDAO cardDAO = new CreditCardDAO();
		List<DomainEntity> entities = new ArrayList<DomainEntity>();
		CreditCard creditCard;
		
		for(PaymentData p : payment.getPaymentDatas()) {
			// Consulta dos dados do(s) cartão(ões)
			creditCard = (CreditCard) cardDAO.consult(p.getCard()).get(0);
			p.setCard(creditCard);
		}
		
		entities.add(payment);
		return entities;
		
	}

}
