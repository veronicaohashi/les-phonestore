package les.domain.stock;

import les.domain.DomainEntity;
import les.domain.product.Reference;

public class Movstock extends DomainEntity{
	private double price;
	private Integer quantity;
	private String date;
	private MovstockType movstockType;
	private Supplier supplier;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public MovstockType getMovstockType() {
		return movstockType;
	}
	public void setMovstockType(MovstockType movstockType) {
		this.movstockType = movstockType;
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
}