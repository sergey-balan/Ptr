package balan.sergii.pt.common;

import org.joda.money.Money;

import balan.sergii.pt.common.Order;
import balan.sergii.pt.common.OrderType;

public abstract class AbstractOrder implements Order,  Comparable<Order>{

	final private OrderType orderType;
	final private String name;
	final private int quantity;
	final private Money price;	


	public AbstractOrder(OrderType orderType, String name, int quantity, Money price) {
		super();
		this.orderType = orderType;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	

	@Override
	public OrderType geOrderType() {
		return orderType;
	}

	@Override
	public String geName() {
		return name;
	}	
	
	@Override
	public int getQuantity() {
		return quantity;
	}	
	
	@Override
	public Money getPrice() {
		return price;
	}


	@Override
	public int compareTo(Order order) {
		if (order == null) {
			return 1;
		}
		
		int res = this.price.compareTo(order.getPrice());
		
		if (res == 0) {
			res = this.geOrderType().compareTo(order.geOrderType());
		}
		return res;
	}

}
