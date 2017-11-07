package balan.sergii.pt.common;

import org.joda.money.Money;

public interface Order {
	OrderType geOrderType();
	String geName();
	int getQuantity();
	Money getPrice();
}
