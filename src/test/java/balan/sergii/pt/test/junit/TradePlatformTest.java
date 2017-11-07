package balan.sergii.pt.test.junit;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.money.Money;
import org.junit.Test;

import balan.sergii.pt.TradePlatform;
import balan.sergii.pt.common.Deal;
import balan.sergii.pt.common.Order;
import balan.sergii.pt.common.OrderType;
import balan.sergii.pt.common.ResultPrinter;

public class TradePlatformTest {
	
	@Test
	public void test() {
        TradePlatform classUnderTest = new TradePlatform();
        
        classUnderTest.sendToMarket(OrderType.BID, "A", 1, Money.parse("USD 10.00"));
        classUnderTest.sendToMarket(OrderType.BID, "B", 1, Money.parse("USD 11.00"));
        classUnderTest.sendToMarket(OrderType.ASK, "C", 1, Money.parse("USD 15.00"));
        classUnderTest.sendToMarket(OrderType.ASK, "D", 1, Money.parse("USD 09.00"));
        classUnderTest.sendToMarket(OrderType.BID, "E", 1, Money.parse("USD 10.00"));
        classUnderTest.sendToMarket(OrderType.ASK, "F", 1, Money.parse("USD 10.00"));
        classUnderTest.sendToMarket(OrderType.BID, "G", 1, Money.parse("USD 100.00"));
        
        final List<Deal> deals = classUnderTest.getDeals();
        
        assertTrue("classUnderTest.getDeals() should 3 elements", deals.size() == 3);
       
        assertTrue("D sold a pumpkin to B for USD 9.00", dealTest(deals.get(0), "D", "USD 09.00", "B", "USD 11.00"));
        assertTrue("F sold a pumpkin to A for USD 10.00", dealTest(deals.get(1), "F", "USD 10.00", "A", "USD 10.00"));
        assertTrue("G bought a pumpkin from C for USD 100.00", dealTest(deals.get(2), "G", "USD 100.00", "C", "USD 15.00"));
        
        ResultPrinter.printResult(deals);
	}
	
	private boolean dealTest(Deal deal, String name1, String price1, String name2, String price2) {
		if (deal == null || deal.getOrder1() == null || deal.getOrder2() == null) {
			return false;
		}
		
		final Order o1 = deal.getOrder1();
		final Order o2 = deal.getOrder2();
		
		boolean o1Ok = o1.geName().equalsIgnoreCase(name1) && o1.getPrice().isEqual(Money.parse(price1));
		boolean o2Ok = o2.geName().equalsIgnoreCase(name2) && o2.getPrice().isEqual(Money.parse(price2));
		
		return o1Ok && o2Ok;
	}
	
}
