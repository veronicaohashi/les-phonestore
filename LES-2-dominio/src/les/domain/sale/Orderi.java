package les.domain.sale;

import java.util.ArrayList;
import java.util.List;

import les.domain.DomainEntity;
import les.domain.product.Reference;

public class Orderi extends DomainEntity {
	private double price;
	private Integer quantity;
	private Reference reference;
	private Order order;
	private Status status;
	private ExchangeCategory exchangeCategory;
	private List<Integer> orderiIds;
	private List<Integer> orderIds;
	
	public Orderi() {
		this.orderiIds = new ArrayList<Integer>();
		this.orderIds = new ArrayList<Integer>();
		
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
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public ExchangeCategory getExchangeCategory() {
		return exchangeCategory;
	}
	public void setExchangeCategory(ExchangeCategory exchangeCategory) {
		this.exchangeCategory = exchangeCategory;
	}
	public List<Integer> getOrderiIds() {
		return orderiIds;
	}
	public void setOrderiIds(List<Integer> orderiIds) {
		this.orderiIds = orderiIds;
	}
	public void addIds(Integer id){
		this.orderiIds.add(id);
	}
	public List<Integer> getOrderIds() {
		return orderIds;
	}
	public void setOrderIds(List<Integer> orderIds) {
		this.orderIds = orderIds;
	}
	public void addOrderIds(Integer id){
		this.orderIds.add(id);
	}
	
}
