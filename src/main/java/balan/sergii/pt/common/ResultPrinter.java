package balan.sergii.pt.common;

import java.util.List;

public class ResultPrinter {
	public static void printResult(List<Deal> deals) {
		for (Deal deal : deals) {
			System.out.println(dealPrintString(deal));
		}
	}
	
	public static String dealPrintString(Deal deal) {
		final String sold = "%s sold a pumpkin to %s for %s";
		final String bought = "%s bought a pumpkin from %s for %s";
		
		if (deal.getOrder1().geOrderType().equals(OrderType.ASK)) {
			return  String.format(sold, deal.getOrder1().geName(), deal.getOrder2().geName(), deal.getOrder1().getPrice().toString());
		}
		else {
			return  String.format(bought, deal.getOrder1().geName(), deal.getOrder2().geName(), deal.getOrder1().getPrice().toString());
		}
	}
}
