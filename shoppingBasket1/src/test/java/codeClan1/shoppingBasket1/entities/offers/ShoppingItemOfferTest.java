package codeClan1.shoppingBasket1.entities.offers;

import static codeClan1.shoppingBasket1.factory.ShoppingItemFactory.*;
import static org.junit.Assert.*;
import java.math.BigDecimal;

import org.junit.Test;

import codeClan1.shoppingBasket1.entities.shopping.ShoppingItem;

/**
 * @author Lucas Toledo
 * </br>
 * 11 Oct 2016
 * </br>
 * test ShoppingItemOffer enum functionality works as expected
 */
public class ShoppingItemOfferTest
{

	@Test
	public void assertNoOffer()
	{
		ShoppingItem shoppingItemNoOffer = getShoppingItemLemonNoOffer();
		assertEquals("this shopping item should have no offer", ShoppingItemOffer.NO_OFFER, shoppingItemNoOffer.getOfferOnThisItem());
		
		BigDecimal totalForSingleItem = shoppingItemNoOffer.getOfferOnThisItem().applyDiscountTo(shoppingItemNoOffer, 1);
		assertEquals("total should be same as price of item", shoppingItemNoOffer.getPrice(), totalForSingleItem);
		
		BigDecimal expectedTotalForTwoItems = shoppingItemNoOffer.getPrice().add(shoppingItemNoOffer.getPrice());
		BigDecimal totalForTwoItems = shoppingItemNoOffer.getOfferOnThisItem().applyDiscountTo(shoppingItemNoOffer, 2);
		assertEquals("total should be same as price of two items", expectedTotalForTwoItems, totalForTwoItems);
	}
	
	@Test
	public void assertBOGOFOffer()
	{
		ShoppingItem shoppingItemBOGOF = getShoppingItemMangoBOGOFOffer();
		assertEquals("this shopping item should have no offer", ShoppingItemOffer.BUY_ONE_GET_ONE_FREE, shoppingItemBOGOF.getOfferOnThisItem());
		
		BigDecimal totalForSingleItem = shoppingItemBOGOF.getOfferOnThisItem().applyDiscountTo(shoppingItemBOGOF, 1);
		assertEquals("total should be same as price of item", shoppingItemBOGOF.getPrice(), totalForSingleItem);
		
		BigDecimal totalForDoubleItem = shoppingItemBOGOF.getOfferOnThisItem().applyDiscountTo(shoppingItemBOGOF, 2);
		assertEquals("total should be same as price of item", shoppingItemBOGOF.getPrice(), totalForDoubleItem);
		
		BigDecimal totalForThreeItems = shoppingItemBOGOF.getOfferOnThisItem().applyDiscountTo(shoppingItemBOGOF, 3);
		//total for three items is same as two items
		BigDecimal expectedTotalForThreeItems =  shoppingItemBOGOF.getPrice().multiply(new BigDecimal(2));
		assertEquals("total price not as expected", expectedTotalForThreeItems, totalForThreeItems);
		
		
	}
	

}
