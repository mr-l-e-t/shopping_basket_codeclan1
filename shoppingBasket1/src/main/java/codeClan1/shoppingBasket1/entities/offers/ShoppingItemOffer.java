package codeClan1.shoppingBasket1.entities.offers;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import codeClan1.shoppingBasket1.entities.shopping.ShoppingItem;

/**
 * @author Lucas Toledo
 * </br>
 * 11 Oct 2016
 * </br>
 * list and describe the offers applicable for each item
 */
public enum ShoppingItemOffer
{
//	private static final int SINGLE_ITEM =1;
	NO_OFFER("no discounts applicable", (shoppingItem, numberOfItems) -> shoppingItem.getPrice().multiply(new BigDecimal(numberOfItems))),
	BUY_ONE_GET_ONE_FREE("buy one, get one free", (shoppingItem, numberOfItems) -> {
		if (numberOfItems == 1)
		{
			return shoppingItem.getPrice();
		}
		if (numberOfItems%2==0)
		{
			return shoppingItem.getPrice().multiply(new BigDecimal(numberOfItems/2));
		}
		int evenNumberOfItems = numberOfItems -1;
		//apply buy one get one free offer, and add remaining single price
		return shoppingItem.getPrice().multiply(new BigDecimal(evenNumberOfItems/2)).add(shoppingItem.getPrice());
	});
	
	
	private final String description;
	
	private final BiFunction<ShoppingItem, Integer, BigDecimal> offerCalculator;
	
	private ShoppingItemOffer(String description, BiFunction<ShoppingItem, Integer, BigDecimal> offerCalculator)
	{
		this.description = description;
		this.offerCalculator = offerCalculator;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}
	/**
	 * calculate the total price of items, according to price, number of items and offer applicable
	 * @param shoppingItem
	 * @param numberOfItems
	 * @return total price of items, deppending on offer
	 */
	public BigDecimal calculateTotalForOffer(ShoppingItem shoppingItem, Integer numberOfItems)
	{
		return offerCalculator.apply(shoppingItem, numberOfItems);
	}
	
	@Override
	public String toString()
	{
		StringBuilder toStringBuilder= new StringBuilder(this.getClass().getSimpleName());
		toStringBuilder.append("[");
		toStringBuilder.append("name: "); toStringBuilder.append(this.name());
		toStringBuilder.append(", ");
		toStringBuilder.append("description: "); toStringBuilder.append( description);
		
		toStringBuilder.append("]");
		return toStringBuilder.toString();
	}
	
}
