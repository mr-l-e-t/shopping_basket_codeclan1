package codeClan1.shoppingBasket1.factory;

import static codeClan1.shoppingBasket1.factory.ShoppingItemFactory.getShoppingItemInstance;
import static codeClan1.shoppingBasket1.factory.ShoppingItemFactory.getShoppingItemLemonNoOffer;
import static codeClan1.shoppingBasket1.factory.ShoppingItemFactory.*;

import codeClan1.shoppingBasket1.entities.offers.ShoppingBasketOffer;
import codeClan1.shoppingBasket1.entities.shopping.ShoppingBasket;

/**
 * @author Lucas Toledo
 *
 *
 * 11 Oct 2016
 * 
 * simple factory class for creating a basket for testing purposes.
 * non extendable since it is for testing purposes
 */
public final class ShoppingBasketFactory
{
	/**
	 * factory class for returning populated objects
	 */
	private ShoppingBasketFactory() { }
	
	public static ShoppingBasket getShoppingBasketInstance(boolean isLoyaltyCardPresent)
	{
		return new ShoppingBasket.Builder().isLoyaltyCardPresent(isLoyaltyCardPresent).build();
	}
	public static ShoppingBasket getShoppingBasketInstanceAndOffers(boolean isLoyaltyCardPresent)
	{
		return new ShoppingBasket.Builder().isLoyaltyCardPresent(isLoyaltyCardPresent).offers(ShoppingBasketOffer.values()).build();
	}
	
	public static ShoppingBasket getShoppingBasketWithSingleItem()
	{
		ShoppingBasket shoppingBasketWithSingleItem = getShoppingBasketInstance(true);
		
		shoppingBasketWithSingleItem.add(getShoppingItemInstance());
		
		return shoppingBasketWithSingleItem;
	}
	
	public static ShoppingBasket getShoppingBasketWithTwoOfSameItemItem()
	{
		ShoppingBasket shoppingBasketWithSingleItem = getShoppingBasketInstance(false);
		
		shoppingBasketWithSingleItem.add(getShoppingItemInstance(), getShoppingItemInstance());
		
		return shoppingBasketWithSingleItem;
	}
	
	public static ShoppingBasket getShoppingBasketWithLemonAndMangoNoLoyaltyCard()
	{
		ShoppingBasket shoppingBasketWithLemonAndMangoNoLoyaltyCard = getShoppingBasketInstance(false);
		shoppingBasketWithLemonAndMangoNoLoyaltyCard.add(getShoppingItemLemonNoOffer(), getShoppingItemMango());
		
		
		return shoppingBasketWithLemonAndMangoNoLoyaltyCard;
		
	}
	public static ShoppingBasket getShoppingBasketWithLemonsThatHaveBOGOFOffer(boolean isLoyaltyCard)
	{
		ShoppingBasket shoppingBasketWithMango = getShoppingBasketInstance(isLoyaltyCard);
		shoppingBasketWithMango.add(getShoppingItemMangoBOGOFOffer(), getShoppingItemMangoBOGOFOffer());
		
		return shoppingBasketWithMango;
	}
	
	public static ShoppingBasket getShoppingBasketWithTotalItemsWorthOverTwentyPounds(boolean isLoyaltyCard)
	{
		ShoppingBasket shoppingBasketWithTotalItemsWorthOverTwentyPounds = getShoppingBasketInstanceAndOffers(isLoyaltyCard);
		shoppingBasketWithTotalItemsWorthOverTwentyPounds.add(getShoppingItemLemonNoOffer(), getShoppingItemLemonNoOffer());
		return shoppingBasketWithTotalItemsWorthOverTwentyPounds;
	}
	/**
	 * get shopping basket with three mangoes, three lemons, loyalty card, whose value is over £20.
	 * @return shopping basket with expected total value of £36.9117
	 */
	public static ShoppingBasket getShoppingBasketWithBOGOFAndOverTwentyPoundWorthOfItemsAndCustomerCardDiscount()
	{
		ShoppingBasket shoppingBasket = getShoppingBasketInstance(true);
		shoppingBasket.add( getShoppingItemMangoBOGOFOffer()
						   ,getShoppingItemMangoBOGOFOffer()
						   ,getShoppingItemMangoBOGOFOffer()
						   ,getShoppingItemLemonNoOffer() 
						   ,getShoppingItemLemonNoOffer()
						   ,getShoppingItemLemonNoOffer() );
		
		return shoppingBasket;
	}
}

