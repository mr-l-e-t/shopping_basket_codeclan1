package codeClan1.shoppingBasket1.entities.shopping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;

import codeClan1.shoppingBasket1.entities.offers.ShoppingBasketOffer;

/**
 * @author Lucas Toledo
 *
 * </br> 11 Oct 2016 </br>
 * 
 * shopping basket class which holds a number of items to buy in cart.
 * </br>
 * 
 * one can 
 * <ul>
 * 	<li>add items</li>
 * 	<li>remove items</li>
 * 	<li>apply offers to items</li>
 * 	<li>calculate totals</li>
 * 
 */
public class ShoppingBasket 
{
	/**
	 * default count value for newly added items
	 */
	private static final Integer INITIAL_ITEM_COUNT = 1;
	/**
	 * incrementor function for adding items
	 */
	private static final BiFunction<Integer, Integer, Integer> INCREMENT_ITEM_COUNT =  (oldValue, newValue) -> oldValue + newValue;
	
	/**
	 * decrement function for removing items from shopping basket
	 */
	private static final BiFunction<ShoppingItem, Integer, Integer> DECREMENT_COUNT_OR_REMOVE_ITEM = 
			(item, currentValue) -> (currentValue==INITIAL_ITEM_COUNT) ? null :  currentValue - INITIAL_ITEM_COUNT;
	
	/**
	 * if total is above this threshold, apply ten percent discount
	 */
	private static final BigDecimal TEN_PERCENT_THRESHOLD = new BigDecimal(20);
	
	/**
	 * does this shopping basket have a loyalty card
	 */
	private boolean isLoyaltyCardPresent;
	/**
	 * contents of shopping basket
	 */
	private Map<ShoppingItem, Integer> items = new HashMap<>();
	/**
	 * list of offers applicable to this basket 
	 */
	private List<ShoppingBasketOffer> offers = new ArrayList<>();
	
	
	public static class Builder 
	{
		private boolean isLoyaltyCardPresent;
		
		private List<ShoppingBasketOffer> offers = new ArrayList<>();
		
		public Builder  isLoyaltyCardPresent( boolean isLoyaltyCardPresent)
		{
			this.isLoyaltyCardPresent = isLoyaltyCardPresent;
			return this;
		}
		public Builder offers( ShoppingBasketOffer... offers )
		{
			this.offers.addAll(Arrays.asList(offers));
			return this;
		}
		
		public Builder() {}
		
		public ShoppingBasket build() 
		{
            return new ShoppingBasket( this );
		}
	}
	
	/**
	 * simple builder constructor to help prepulate a basket.
	 * @param builder
	 */
	private ShoppingBasket(Builder builder)
	{
		this.isLoyaltyCardPresent = builder.isLoyaltyCardPresent;
		this.offers.addAll(builder.offers);
	}
	
	/**
	 * @return the isCustomerLoyaltyCardPresent
	 */
	public boolean isLoyaltyCardPresent() 
	{
		return isLoyaltyCardPresent;
	}

	/**
	 * @param isLoyaltyCardPresent the isCustomerLoyaltyCardPresent to set
	 */
	public void setLoyaltyCardPresent(boolean isLoyaltyCardPresent) 
	{
		this.isLoyaltyCardPresent = isLoyaltyCardPresent;
	}

	/**
	 * @return copy of content of the basket. This is only a snapshopt of content
	 */
	public Map<ShoppingItem, Integer> getCopyOfBasketContent()
	{
		return new HashMap<>(items);
	}
	/**
	 * add items to the basket, setting the intitial count of one per item. Increment item count by one, if already exists
	 * @param itemList
	 */
	public void add(ShoppingItem... itemList)
	{
		if( isEmpty( itemList ) ) return ;
		
		Arrays.stream(itemList).forEach( itemKey ->
			items.merge(itemKey, INITIAL_ITEM_COUNT, INCREMENT_ITEM_COUNT )
		);

	}
	private boolean isEmpty(ShoppingItem... itemList)
	{
		return itemList == null || itemList.length ==0;
	}
	
	
	/**
	 * remove ShoppingItem in itemList from the basket. 
	 * If ShoppingItem present, decrease count by one, if no more item left, remove ShoppingItem completely from basket 
	 * 
	 * @param itemList
	 */
	public void remove(ShoppingItem... itemList)
	{
		if( isEmpty( itemList ) ) return ;
		
		Arrays.stream(itemList).forEach(  itemKey ->			
				items.computeIfPresent(itemKey, DECREMENT_COUNT_OR_REMOVE_ITEM)
		);
	}
	
	/**
	 * 
	 * @return true if no items on the basket, false otherwise
	 */
	public boolean isEmpty()
	{
		return items.isEmpty();
	}
	/**
	 * return list of offers
	 * @return
	 */
	public List<ShoppingBasketOffer> getCurrentOffers()
	{
		return new ArrayList<>(offers);
	}
	
	@Override
	public String toString()
	{
		StringBuilder toStringBuilder= new StringBuilder(this.getClass().getName());
		toStringBuilder.append("[");
		toStringBuilder.append("isLoyaltyCardPresent: "); toStringBuilder.append(isLoyaltyCardPresent);
		toStringBuilder.append(", ");
		toStringBuilder.append("offers: "); toStringBuilder.append(offers);
		toStringBuilder.append(", ");
		toStringBuilder.append("items: "); toStringBuilder.append(items);
		toStringBuilder.append("]");
		return toStringBuilder.toString();
	}

	/**
	 * This function empties/removes all shopping items from the basket. 
	 */
	public void clear()
	{
		items.clear();
	}

	/**
	 * @return
	 */
	public BigDecimal calculateTotal()
	{
		BigDecimal total = getTotalPriceFor( items );
		
		if ( isTenPercentApplicableFor(total) )
		{
			total = ShoppingBasketOffer.TEN_PERSCENT_OFF.calculateReducedValueFor(total);
		}
		if ( isLoyaltyCardPresent() )
		{
			total = ShoppingBasketOffer.TWO_PERCENT_OFF.calculateReducedValueFor(total);
		}
		
		return total;
	}
	
	
	/**
	 * work out the total price for items. if applicable, use offers to discount price
	 * @param items
	 * @return
	 */
	private BigDecimal getTotalPriceFor(Map<ShoppingItem, Integer> items)
	{
		BigDecimal total = new BigDecimal(0);
		
		for(Entry<ShoppingItem, Integer> shoppingItemEntry : items.entrySet())
		{
			ShoppingItem currentItem = shoppingItemEntry.getKey();
			Integer numberOfItems = shoppingItemEntry.getValue();
			
			total = total.add( currentItem.getOfferOnThisItem().calculateTotalForOffer(currentItem, numberOfItems) );
		}
		return total;
	}
	
	private boolean isTenPercentApplicableFor(BigDecimal total)
	{
		return total.compareTo(TEN_PERCENT_THRESHOLD) > 0;
	}
}
