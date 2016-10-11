package codeClan1.shoppingBasket1;

import static codeClan1.shoppingBasket1.factory.ShoppingBasketFactory.getShoppingBasketInstance;
import static codeClan1.shoppingBasket1.factory.ShoppingBasketFactory.getShoppingBasketWithSingleItem;
import static codeClan1.shoppingBasket1.factory.ShoppingBasketFactory.*;
import static codeClan1.shoppingBasket1.factory.ShoppingItemFactory.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import codeClan1.shoppingBasket1.entities.shopping.ShoppingBasket;
import codeClan1.shoppingBasket1.entities.shopping.ShoppingItem;

/**
 * @author Lucas Toledo
 *
 * 11 Oct 2016
 * 
 * This is a BDD scenario where I'm testing first the requirements and 
 * letting the test lead the development 
 */
public class TestShoppingBasketTasks 
{

	private static final Integer SINGLE_ITEM = 1;
	private static final Integer DOUBLE_ITEM = 2;
	
	
	
	@Test
	public void assertBasketHasLoyaltyCard()
	{
		ShoppingBasket sb = getShoppingBasketInstance(true);
		
		assertTrue("basket should have loyalty card", sb.isLoyaltyCardPresent());
	}
	
	@Test
	public void assertBasketDoesNotHaveLoyaltyCard()
	{
		ShoppingBasket sb = getShoppingBasketInstance(false);
		
		assertFalse("basket should not have loyalty card", sb.isLoyaltyCardPresent());
	}
	
	@Test 
	public void assertAddSingleItemToBasket()
	{
		ShoppingBasket sb = getShoppingBasketInstance(false);
		
		ShoppingItem item = getShoppingItemInstance();
		
		sb.add(item);
		
		assertTrue("item should exist in the basket ", sb.getCopyOfBasketContent().containsKey(item));
		
		assertEquals("there should be a single item in basket ", SINGLE_ITEM, sb.getCopyOfBasketContent().get(item));
	}
	
	@Test 
	public void assertAddMultipleItemToBasket()
	{
		ShoppingBasket sb = getShoppingBasketInstance(false);
		
		ShoppingItem item = getShoppingItemInstance();
		
		sb.add(item, item);
		
		assertEquals("there should be two items in basket ", DOUBLE_ITEM, sb.getCopyOfBasketContent().get(item));
	}

	@Test
	public void assertAddingNoItemOrNullReturnsFalse()
	{
		ShoppingBasket sb = getShoppingBasketInstance(false);
		sb.add();
		assertTrue("basket should be empty", sb.isEmpty());
		
		sb.add(null);
		assertTrue("item should have not been able to been added", sb.isEmpty());
	}
	
	@Test
	public void assertRemoveSingleItemFromBasketWithOnlyOneItem()
	{
		ShoppingBasket sb = getShoppingBasketWithSingleItem();
		
		ShoppingItem itemToRemove = getShoppingItemInstance();
		
		sb.remove(itemToRemove);
		
		assertTrue("basket should be empty", sb.isEmpty());
	}
	
	@Test
	public void assertRemoveSingleItemFromBasketWithTwoItems()
	{
		ShoppingBasket sb = getShoppingBasketWithTwoOfSameItemItem();
		
		ShoppingItem itemToRemove = getShoppingItemInstance();
		
		sb.remove(itemToRemove);
		
		ShoppingItem itemRemaining = itemToRemove;
		
		assertFalse("basket should not be empty", sb.isEmpty());

		assertTrue("item should exist in the basket ", sb.getCopyOfBasketContent().containsKey(itemRemaining));
		
		assertEquals("there should be a single item in basket ", SINGLE_ITEM, sb.getCopyOfBasketContent().get(itemRemaining));
	}
	
	@Test
	public void assertEmptyBasket()
	{
		ShoppingBasket sb = getShoppingBasketWithTwoOfSameItemItem();
		
		sb.clear();
		
		assertTrue("basket should be empty", sb.isEmpty());
		
		assertTrue("there should be no items in the basket", sb.getCopyOfBasketContent().isEmpty());
	}
	//calculate total off shopping basket
	// items offers
		// BOGOF
		// buy two get third free
	//	total offers
		//10% off on totals > £20
		//2% off on loyalty card
	
	@Test
	public void assertCalculateTotalNoDiscounts()
	{
		ShoppingBasket sb = getShoppingBasketWithLemonAndMangoNoLoyaltyCard();
		
		BigDecimal total = sb.calculateTotal();
		
		assertNotNull("total should not be null", total);
		
		BigDecimal expectedTotal = SINGLE_ITEM_PRICE.add( MORE_EXPENSIVE_SINGLE_ITEM_PRICE );
		
		assertEquals("calculated total is not as expected", expectedTotal, total);
	}
	
	@Test
	public void assertCalculateTotalBOGOFOnly()
	{
		ShoppingBasket sb = getShoppingBasketWithLemonsThatHaveBOGOFOffer();
		
		BigDecimal total = sb.calculateTotal();
		
		BigDecimal expectedTotal = SINGLE_ITEM_PRICE;
		
		assertEquals("calculated total is not as expected", expectedTotal, total);
	}
	
}
