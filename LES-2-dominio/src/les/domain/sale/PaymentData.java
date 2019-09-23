package les.domain.sale;

import les.domain.DomainEntity;
import les.domain.client.CreditCard;

public class PaymentData extends DomainEntity {
	private CreditCard card;
	private Integer quantity;
	private double price;
	
	public CreditCard getCard() {
		return card;
	}
	public void setCard(CreditCard card) {
		this.card = card;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}	
}
