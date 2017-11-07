package balan.sergii.pt.test;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.money.Money;
import org.junit.Test;

import balan.sergii.pt.TradePlatform;
import balan.sergii.pt.common.Deal;
import balan.sergii.pt.common.OrderType;
import balan.sergii.pt.common.ResultPrinter;

public class TradePlatformTest {

//  D sold a pumpkin to B for USD 9.00
//  F sold a pumpkin to A for USD 10.00
//  G bought a pumpkin from C for USD 100.00
	
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
       
        ResultPrinter.printResult(deals);
	}

	@Test
	public void testMultithread() {
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
       
        ResultPrinter.printResult(deals);
	}	
	
}
