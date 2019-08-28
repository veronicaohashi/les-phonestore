package les.domain.stock;

import les.domain.DomainEntity;
import les.domain.product.Reference;

public class Entryi extends DomainEntity{
	private double price;
	private Integer quantity;
	private Entry entry;
	private Reference reference;
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Entry getEntry() {
		return entry;
	}
	public void setEntry(Entry entry) {
		this.entry = entry;
	}
	public Reference getReference() {
		return reference;
	}
	public void setReference(Reference reference) {
		this.reference = reference;
	}
}