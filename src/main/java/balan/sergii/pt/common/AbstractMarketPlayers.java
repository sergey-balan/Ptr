package balan.sergii.pt.common;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import org.joda.money.Money;

import balan.sergii.pt.DealImpl;

@SuppressWarnings("serial")
public abstract class AbstractMarketPlayers extends ConcurrentSkipListMap<Money, Queue<Order>> implements MarketPlayers {
	
	@Override
	public boolean placeToMarket(Order order) {
		Queue<Order> ordersQueue = get(order.getPrice());
		if (ordersQueue == null) {
			synchronized(this) {
				if (ordersQueue == null) {
					ordersQueue = new ConcurrentLinkedQueue<Order>();
					put(order.getPrice(), ordersQueue);
				}
			}
		}
		return ordersQueue.add(order);
	}
	
	@Override
	public Deal propose(Order offer) {
		Entry<Money, Queue<Order>> marketEntry = getAvailableOrder(offer);
				
		if (marketEntry != null) {
			Deal deal = processDeal(offer, marketEntry); 
			removePriceOptionIfEmpty(marketEntry);
			return deal;
		}
		
		return null;
	}	
	
	protected abstract Entry<Money, Queue<Order>> getAvailableOrder(Order offer);
	
	private Deal processDeal(Order offer, Entry<Money, Queue<Order>> marketEntry) {
		Order secondOrder = marketEntry.getValue().poll();
		return secondOrder == null ? null : new DealImpl(offer, secondOrder);
	}	
	
	private void removePriceOptionIfEmpty(Entry<Money, Queue<Order>> marketEntry) {
		Queue<Order> ordersQueue = marketEntry.getValue();
		if ( (ordersQueue == null) || (ordersQueue.isEmpty()) ) {
			synchronized(this) {
				if ( (ordersQueue == null) || (ordersQueue.isEmpty()) ) {
					remove(marketEntry.getKey());
				}
			}
		}
	}	
	
}
