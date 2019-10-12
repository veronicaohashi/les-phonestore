package les.domain.sale;

import java.util.ArrayList;
import java.util.List;

import les.domain.DomainEntity;

public class Cart extends DomainEntity {
	private List<Orderi> items;
	private double price;
	private Integer quantity;
	private Orderi storageItem;
	private Double totalItemsPrice;
	private Double totalDiscountPrice;
	
	public Cart() {
		this.totalItemsPrice = 0.0;
		this.totalDiscountPrice = 0.0;
		this.price = 0.0;
		this.quantity = 0;
		this.items = new ArrayList<Orderi>();
	}
	public List<Orderi> getItems() {
		return items;
	}
	public void setItems(List<Orderi> items) {
		this.items = items;
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
	public Orderi getStorageItem() {
		return storageItem;
	}
	public void setStorageItem(Orderi storageItem) {
		this.storageItem = storageItem;
	}
	public Orderi getItem(Orderi salei) {
		Orderi item = new Orderi();
		
		for(Orderi i : this.items) {
			if(i.getReference().getId().equals(salei.getReference().getId()))
				item = i;	
		}		
		return item;
	}	
	public void removeItem(Orderi salei) {
		this.items.remove(salei);	
		updateCart();
	}	
	public void addItem(Orderi salei) {
		this.items.add(salei);		
		updateCart();
	}
	private void updateCart() {
		this.price = 0.0;
		this.quantity = 0;
		this.totalItemsPrice = 0.0;
		
		if(! this.items.isEmpty()) {
			for(Orderi i : this.items) {
				this.totalItemsPrice += i.getPrice() * i.getQuantity();
				this.quantity += i.getQuantity();
			}
			this.price = this.totalItemsPrice;
		}
	}
	public Double getTotalItemsPrice() {
		return totalItemsPrice;
	}
	public void setTotalItemsPrice(Double totalItemsPrice) {
		this.totalItemsPrice = totalItemsPrice;
	}
	public Double getTotalDiscountPrice() {
		return totalDiscountPrice;
	}
	public void setTotalDiscountPrice(Double totalDiscountPrice) {
		this.totalDiscountPrice = totalDiscountPrice;
	}	
}
