package les.domain.sale;

import les.domain.DomainEntity;
import les.domain.product.Reference;

public class Orderi extends DomainEntity {
	private double price;
	private Integer quantity;
	private Reference reference;
	private Order sale;
	private Status status;
	public Orderi() {
		
	}
	public Orderi(Integer id, Double price, Integer quantity, Status status, Reference reference) {
		super(id);
		this.price = price;
		this.quantity = quantity;
		this.status = status;
		this.reference = reference;
	}
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
	public Reference getReference() {
		return reference;
	}
	public void setReference(Reference reference) {
		this.reference = reference;
	}
	public Order getSale() {
		return sale;
	}
	public void setSale(Order sale) {
		this.sale = sale;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
