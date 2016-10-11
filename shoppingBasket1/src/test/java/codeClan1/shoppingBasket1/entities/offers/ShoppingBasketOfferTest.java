package codeClan1.shoppingBasket1.entities.offers;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Map.Entry;
import java.util.function.BiFunction;

import static codeClan1.shoppingBasket1.factory.ShoppingBasketFactory.*;

import org.junit.Test;

import codeClan1.shoppingBasket1.entities.shopping.ShoppingBasket;
import codeClan1.shoppingBasket1.entities.shopping.ShoppingItem;


/**
 * @author Lucas Toledo
 * </br>
 * 11 Oct 2016
 * </br>
 */
public class ShoppingBasketOfferTest
{

	@Test
	public void assertTenPercentOffOnTotalOverTwentyPounds()
	{
		ShoppingBasket shoppingBasketWithTotalItemsWorthOverTwentyPounds = getShoppingBasketWithTotalItemsWorthOverTwentyPounds();
		
		assertTrue("shopping basket should have 10% off offer" , shoppingBasketWithTotalItemsWorthOverTwentyPounds.getCurrentOffers().contains(ShoppingBasketOffer.TEN_PERSCENT_OFF_OVER_TWENTY_POUNDS));
		
		BigDecimal totalAfterDiscount = shoppingBasketWithTotalItemsWorthOverTwentyPounds.calculateTotal();

		BigDecimal expectedTotal = getExpectedTotalAfterTenPercentDiscountFor(shoppingBasketWithTotalItemsWorthOverTwentyPounds);
		
		assertEquals("total after discount is not as expected", expectedTotal,  totalAfterDiscount);
	}
	private BigDecimal getExpectedTotalAfterTenPercentDiscountFor(ShoppingBasket shoppingBasketWithTotalItemsWorthOverTwentyPounds)
	{
		BigDecimal expectedTotal = new BigDecimal(0);
		BiFunction<BigDecimal, Entry<ShoppingItem, Integer>, BigDecimal> priceAccumulator  = 
				(currentPrice, shoppingItemEntry) -> {
					System.out.println("lucas: currentPrice: "+currentPrice);
					System.out.println("lucas: shoppingItemEntry: "+shoppingItemEntry);
					return shoppingItemEntry.getKey().getPrice().multiply(new BigDecimal(shoppingItemEntry.getValue()));
				};
				
		
			expectedTotal = shoppingBasketWithTotalItemsWorthOverTwentyPounds.getCopyOfBasketContent().entrySet().stream().
			reduce(new BigDecimal(0), priceAccumulator, (a, b) -> a.add(b));
			System.out.println(ShoppingItem.GBP.format(expectedTotal));
			BigDecimal tenPercentOff= expectedTotal.multiply(new BigDecimal(0.10));
			System.out.println(ShoppingItem.GBP.format(tenPercentOff));
			expectedTotal = expectedTotal.subtract(tenPercentOff);
			System.out.println(ShoppingItem.GBP.format(expectedTotal));
		return expectedTotal;
	}

}
