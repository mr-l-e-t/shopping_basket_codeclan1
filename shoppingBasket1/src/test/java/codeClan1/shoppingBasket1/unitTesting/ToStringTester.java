package codeClan1.shoppingBasket1.unitTesting;

import static codeClan1.shoppingBasket1.factory.ShoppingBasketFactory.*;
import static codeClan1.shoppingBasket1.factory.ShoppingItemFactory.SINGLE_ITEM_NAME;
import static codeClan1.shoppingBasket1.factory.ShoppingItemFactory.SINGLE_ITEM_PRICE;
import static codeClan1.shoppingBasket1.factory.ShoppingItemFactory.getShoppingItemInstance;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import codeClan1.shoppingBasket1.entities.offers.ShoppingBasketOffer;
import codeClan1.shoppingBasket1.entities.offers.ShoppingItemOffer;
import codeClan1.shoppingBasket1.entities.shopping.ShoppingBasket;
import codeClan1.shoppingBasket1.entities.shopping.ShoppingItem;


/**
 * @author Lucas Toledo
 * </br>
 * 11 Oct 2016
 * </br>
 * Simple unit tester to assert toString functionality of Objects
 */
public class ToStringTester
{
	
	@Test
	public void assertShoppingItemToString()
	{
		ShoppingItem shoppingItem = getShoppingItemInstance();
		String shoppingItemToStringContent = shoppingItem.toString();
		
		
		assertTrue("shopping item should have name", shoppingItemToStringContent.contains(SINGLE_ITEM_NAME));
		assertTrue("shopping item should have value", shoppingItemToStringContent.contains(ShoppingItem.GBP.format(SINGLE_ITEM_PRICE)));
		assertTrue("shopping item should have offer", shoppingItemToStringContent.contains(ShoppingItemOffer.NO_OFFER.getDescription()));
	}
	
	@Test
	public void assertShoppingBasketToString()
	{
		ShoppingBasket shoppingBasket = getShoppingBasketInstanceAndOffers(true);
		shoppingBasket.add(getShoppingItemInstance());
		String shoppingItemToStringContent = shoppingBasket.toString();
		
		assertTrue("shopping basket should have isLoyaltyCardPresent", shoppingItemToStringContent.contains("isLoyaltyCardPresent"));
		assertTrue("shopping basket should have items", shoppingItemToStringContent.contains("items"));
		assertTrue("shopping basket should have single item", shoppingItemToStringContent.contains(SINGLE_ITEM_NAME));
		assertTrue("shopping basket should have offer", shoppingItemToStringContent.contains(ShoppingBasketOffer.TEN_PERSCENT_OFF_OVER_TWENTY_POUNDS.getDescription()));
	}
	
}
