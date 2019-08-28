package les.domain.stock;

import java.util.List;

import les.domain.DomainEntity;
import les.domain.stock.Entryi;

public class Entry extends DomainEntity {
	private double price;
	private Integer quantity;
	private String date;
	private Supplier supplier;
	private List<Entryi> items;
	
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
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public List<Entryi> getItems() {
		return items;
	}
	public void setItems(List<Entryi> items) {
		this.items = items;
	}
	
}