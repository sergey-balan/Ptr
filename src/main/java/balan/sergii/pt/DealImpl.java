package balan.sergii.pt;

import balan.sergii.pt.common.Deal;
import balan.sergii.pt.common.Order;

public class DealImpl implements Deal{

	final private Order order1;
	final private Order order2;

	public DealImpl(Order order1, Order order2) {
		super();
		
		if ( (order1 == null) || (order2 == null) || (order1.geOrderType().equals(order2.geOrderType()))) {
			new Exception("Wrong Deal parameters!"); 
		}
		
		this.order1 = order1;
		this.order2 = order2;
	}
	
	@Override
	public Order getOrder1() {
		return order1;
	}

	@Override
	public Order getOrder2() {
		return order2;
	}

}
