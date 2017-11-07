package balan.sergii.pt.common;

import java.util.Queue;
import java.util.concurrent.ConcurrentNavigableMap;

import org.joda.money.Money;

public interface MarketPlayers extends ConcurrentNavigableMap<Money, Queue<Order>>{
	boolean placeToMarket(Order order);
	Deal propose(Order offer);
}
