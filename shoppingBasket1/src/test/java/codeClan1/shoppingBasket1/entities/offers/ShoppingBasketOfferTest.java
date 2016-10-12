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
 * Test that ShoppingBasketOffer enum functions are working as expected
 */
public class ShoppingBasketOfferTest
{

	@Test
	public void assertTenPercentOffOnTotalOverTwentyPounds()
	{
		ShoppingBasket shoppingBasketWithTotalItemsWorthOverTwentyPounds = getShoppingBasketWithTotalItemsWorthOverTwentyPounds(false);
		
		BigDecimal totalAfterDiscount = shoppingBasketWithTotalItemsWorthOverTwentyPounds.calculateTotal();

		BigDecimal expectedTotal = getExpectedTotalAfterTenPercentDiscountFor(shoppingBasketWithTotalItemsWorthOverTwentyPounds);
		
		assertEquals("total after discount is not as expected", expectedTotal,  totalAfterDiscount);
	}
	
	private BigDecimal getExpectedTotalAfterTenPercentDiscountFor(ShoppingBasket shoppingBasketWithTotalItemsWorthOverTwentyPounds)
	{
		BigDecimal expectedTotal = new BigDecimal(0);
		BiFunction<BigDecimal, Entry<ShoppingItem, Integer>, BigDecimal> priceAccumulator  = 
				(currentPrice, shoppingItemEntry) -> {
					return shoppingItemEntry.getKey().getPrice().multiply(new BigDecimal(shoppingItemEntry.getValue()));
				};
			
			expectedTotal = shoppingBasketWithTotalItemsWorthOverTwentyPounds.getCopyOfBasketContent().entrySet().stream().
			reduce(new BigDecimal(0), priceAccumulator, (a, b) -> a.add(b));
			
			BigDecimal tenPercentOff= expectedTotal.multiply(new BigDecimal(0.10));
			
			expectedTotal = expectedTotal.subtract(tenPercentOff);
			
		return expectedTotal;
	}

}
