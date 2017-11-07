package balan.sergii.pt;

import java.util.Queue;
import org.joda.money.Money;

import balan.sergii.pt.common.AbstractMarketPlayers;
import balan.sergii.pt.common.Order;

@SuppressWarnings("serial")
public class MarketSellers extends AbstractMarketPlayers {

	@Override
	protected Entry<Money, Queue<Order>> getAvailableOrder(Order offer) {
		Entry<Money, Queue<Order>> entry = firstEntry();
		if ( sutisfied(entry, offer) ) {
			return entry;
		}
		
		entry = floorEntry(offer.getPrice());
		if ( sutisfied(entry, offer) ) {
			return entry;
		}
		
		return null;
	}	
	
	private boolean sutisfied(Entry<Money, Queue<Order>> entry, Order offer) {
		return (entry != null) && !entry.getKey().isGreaterThan(offer.getPrice());
	}
}
