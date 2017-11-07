package balan.sergii.pt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.joda.money.Money;

import balan.sergii.pt.common.AbstractOrder;
import balan.sergii.pt.common.Deal;
import balan.sergii.pt.common.MarketPlayers;
import balan.sergii.pt.common.Order;
import balan.sergii.pt.common.OrderType;

public class TradePlatform {
	
	final private MarketPlayers sellers = new MarketSellers(); 
	final private MarketPlayers buyers = new MarketBuyers();
	final private Queue<Deal> deals = new ConcurrentLinkedQueue<Deal>();
	
	final private Queue<Order> invalidOrders = new ConcurrentLinkedQueue<Order>();

	public List<Deal> getDeals() {
		return new ArrayList<Deal>(deals);
	}
	
	public void sendToMarket(final OrderType orderType, final String name, final int quantity, final Money price) {
		if (!orderParamsIsCorrect(orderType, name, quantity, price)) {
			return;
		}
		
		final List<Order> ordersList = splitOrder(orderType, name, quantity, price);
		
		List<Deal> currentDeals = orderType.equals(OrderType.BID) ?
				askForDeals(ordersList, buyers, sellers) : askForDeals(ordersList, sellers, buyers);
		
		deals.addAll(currentDeals);
	}
	
	private List<Deal> askForDeals(List<Order> offers, MarketPlayers agent, MarketPlayers contrAgent) {
		if (offers == null) {
			return Collections.emptyList();
		}
		
		List<Deal> currentDeals = new ArrayList<Deal>();
		for (Order offer: offers) {
			Deal deal = contrAgent.propose(offer);
			if (deal != null) {
				currentDeals.add(deal);
			}
			else {
				agent.placeToMarket(offer);
			}
		}
		return currentDeals;
	}	
	
	private List<Order> splitOrder(final OrderType orderType, final String name, final int quantity, final Money price) {
		final List<Order> ordersList = new ArrayList<Order>();
		for (int q = 0; q < quantity; q++) {
			final Order order = new AbstractOrder(orderType, name, 1, price) {};
			ordersList.add(order);
		}
		return ordersList;
	}
	
	private boolean orderParamsIsCorrect(final OrderType orderType, final String name, final int quantity, final Money price) {
		boolean valid = (quantity >= 1) && (price != null) && price.isPositive();
		if (!valid) {
			invalidOrders.add(new AbstractOrder(orderType, name, quantity, price) {});
		}
		return valid;
	}

}
