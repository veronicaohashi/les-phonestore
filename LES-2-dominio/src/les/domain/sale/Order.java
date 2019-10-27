package les.domain.sale;

import java.util.ArrayList;
import java.util.List;

import les.domain.DomainEntity;
import les.domain.client.Client;

public class Order extends DomainEntity {
	private Double price;
	private String orderDate;
	private String deliveryDate;
	private Integer quantity;
	private Payment payment;
	private OrderAddress orderAddress;
	private Client client;
	private Status status;
	private List<Orderi> items;
	private List<Integer> orderIds;
	private OrderCoupons orderCoupons;
	private double totalItemsPrice;
	private double totalDiscountPrice;
	
	public Order() {
		this.items = new ArrayList<Orderi>();
		this.orderIds = new ArrayList<Integer>();
	}
	public Order(Integer id, Client client, String orderDate) {
		super(id);
		this.client = client;
		this.orderDate = orderDate;
	}
	public Order(Integer id) {
		super(id);
	}
	public Order(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Order(OrderAddress orderAddress, Payment payment, Client client, List<Orderi> items, double price,
			Integer quantity, double totalItemsPrice, double totalDiscountPrice, String orderDate, OrderCoupons orderCoupons) {
		this.orderAddress = orderAddress;
		this.payment = payment;
		this.client = client;
		this.items = items;
		this.price = price;
		this.quantity = quantity;
		this.totalItemsPrice = totalItemsPrice;
		this.totalDiscountPrice = totalDiscountPrice;
		this.orderDate = orderDate;
		this.orderCoupons = orderCoupons;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public List<Orderi> getItems() {
		return items;
	}
	public void setItems(List<Orderi> items) {
		this.items = items;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public OrderAddress getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}
	public void addItem(Orderi item){
		this.items.add(item);
	}
	public List<Integer> getOrderIds() {
		return orderIds;
	}
	public void setOrderIds(List<Integer> orderIds) {
		this.orderIds = orderIds;
	}
	public void addIds(Integer id){
		this.orderIds.add(id);
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public OrderCoupons getOrderCoupons() {
		return orderCoupons;
	}
	public void setOrderCoupons(OrderCoupons orderCoupons) {
		this.orderCoupons = orderCoupons;
	}
	public double getTotalItemsPrice() {
		return totalItemsPrice;
	}
	public void setTotalItemsPrice(double totalItemsPrice) {
		this.totalItemsPrice = totalItemsPrice;
	}
	public double getTotalDiscountPrice() {
		return totalDiscountPrice;
	}
	public void setTotalDiscountPrice(double totalDiscountPrice) {
		this.totalDiscountPrice = totalDiscountPrice;
	}
}
