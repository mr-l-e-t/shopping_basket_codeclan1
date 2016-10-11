package codeClan1.shoppingBasket1.factory;

import java.math.BigDecimal;

import codeClan1.shoppingBasket1.entities.offers.ShoppingItemOffer;
import codeClan1.shoppingBasket1.entities.shopping.ShoppingItem;

/**
 * @author Lucas Toledo
 * </br>
 * 11 Oct 2016
 * </br>
 * factory class for generating items for testing purpose
 */
public final class ShoppingItemFactory
{
	public static final String SINGLE_ITEM_NAME = "lemon";
	public static final BigDecimal SINGLE_ITEM_PRICE = new BigDecimal(5.40);
	public static final BigDecimal MORE_EXPENSIVE_SINGLE_ITEM_PRICE = new BigDecimal(10.35);
	/**
	 *  making this class non instatiable since it is a factory class
	 */
	private ShoppingItemFactory() { }
	
	public static ShoppingItem getShoppingItemInstance()
	{
		return new ShoppingItem.Builder(SINGLE_ITEM_NAME, SINGLE_ITEM_PRICE, ShoppingItemOffer.NO_OFFER).build();
	}
	
	public static ShoppingItem getTwinShoppingItemInstanceA()
	{
		return new ShoppingItem.Builder(SINGLE_ITEM_NAME, SINGLE_ITEM_PRICE, ShoppingItemOffer.NO_OFFER).build();
	}
	public static ShoppingItem getTwinShoppingItemInstanceB()
	{
		return new ShoppingItem.Builder(SINGLE_ITEM_NAME, SINGLE_ITEM_PRICE, ShoppingItemOffer.NO_OFFER).build();
	}
	public static ShoppingItem getTwinShoppingItemInstanceC()
	{
		return new ShoppingItem.Builder(SINGLE_ITEM_NAME, SINGLE_ITEM_PRICE, ShoppingItemOffer.NO_OFFER).build();
	}
	public static ShoppingItem getShoppingItemInstanceDifferent()
	{
		return new ShoppingItem.Builder("mango", SINGLE_ITEM_PRICE, ShoppingItemOffer.NO_OFFER).build();
	}
	public static ShoppingItem getShoppingItemInstanceDifferent2()
	{
		return new ShoppingItem.Builder("nectarine", MORE_EXPENSIVE_SINGLE_ITEM_PRICE, ShoppingItemOffer.NO_OFFER).build();
	}
	
	public static ShoppingItem getShoppingItemLemonNoOffer()
	{
		return new ShoppingItem.Builder("lemon", MORE_EXPENSIVE_SINGLE_ITEM_PRICE, ShoppingItemOffer.NO_OFFER).build();
	}
	public static ShoppingItem getShoppingItemMango()
	{
		return new ShoppingItem.Builder("mango", SINGLE_ITEM_PRICE, ShoppingItemOffer.NO_OFFER).build();
	}
	
	public static ShoppingItem getShoppingItemMangoBOGOFOffer()
	{
		return new ShoppingItem.Builder("mango", SINGLE_ITEM_PRICE, ShoppingItemOffer.BUY_ONE_GET_ONE_FREE).build();
	}
}
