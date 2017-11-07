package balan.sergii.pt.test.mockito;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.List;
import org.joda.money.Money;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import balan.sergii.pt.TradePlatform;
import balan.sergii.pt.common.Order;
import balan.sergii.pt.common.OrderType;

public class MockitoTest {

	@Mock
	List<Order> orderList;
	
	@InjectMocks
    private TradePlatform testClass;
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Test
	public void test1()  {
		testClass.sendToMarket(OrderType.BID, "A", 1, Money.parse("USD 10.00"));
	}
	
}
