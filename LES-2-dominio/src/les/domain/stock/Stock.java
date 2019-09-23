package les.domain.stock;

import les.domain.DomainEntity;
import les.domain.product.Reference;

public class Stock extends DomainEntity{
	private double price;
	private Integer quantity;
	private Integer reserved;
	private Supplier supplier;
	private Reference reference;
	private Integer avaiable;
	
	public Stock() {
		
	}
	
	public Stock(Reference reference) {
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
	public Integer getReserved() {
		return reserved;
	}
	public void setReserved(Integer reserved) {
		this.reserved = reserved;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Reference getReference() {
		return reference;
	}
	public void setReference(Reference reference) {
		this.reference = reference;
	}
	public Integer getAvaiable() {
		return avaiable;
	}
	public void setAvaiable(Integer avaiable) {
		this.avaiable = avaiable;
	}
}