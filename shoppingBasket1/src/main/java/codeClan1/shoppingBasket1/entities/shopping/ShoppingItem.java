package codeClan1.shoppingBasket1.entities.shopping;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import codeClan1.shoppingBasket1.entities.offers.ShoppingItemOffer;

/**
 * @author Lucas Toledo
 * </br>
 * 11 Oct 2016
 * </br>
 * 
 * Shopping item which is to be added to the shopping basket
 * 
 * The price of the item is assumed to be in British Sterling Pounds, with decimal value of 2. eg. 0.35
 * 
 * This class has a natural sorting order of shopping item name
 */
public class ShoppingItem implements Comparable<ShoppingItem>
{
	
	/**
	 * format of the price of items, in this case it's a decimal value with two trailing digits.
	 * </br>
	 * E.g: £1.34
	 */
	public static final NumberFormat GBP = NumberFormat.getCurrencyInstance(Locale.UK); 
	/**
	 * name of the shopping item. This should be unique as it is used
	 * to identify the object.
	 */
	private String name;
	/**
	 * price of the item, represented in big decimal
	 */
	private BigDecimal price;
	
	/**
	 * the offer on this item
	 */
	private ShoppingItemOffer offerOnThisItem;
	
	
	public static class Builder 
	{
		private String name;
		
		private BigDecimal price;
		
		private ShoppingItemOffer offerOnThisItem;
		
		public Builder(String name, BigDecimal price, ShoppingItemOffer offerOnThisItem)
		{
			this.name = name;
			this.price = price;
			this.offerOnThisItem = offerOnThisItem;
		}
		
		public ShoppingItem build() 
		{
            return new ShoppingItem( this );
		}
	}
	
	private ShoppingItem( Builder builder)
	{
		this.name = builder.name;
		this.price = builder.price;
		this.offerOnThisItem = builder.offerOnThisItem;
	}
	
	
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}


	/**
	 * @return the price
	 */
	public BigDecimal getPrice()
	{
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	/**
	 * @return the offerOnThisItem
	 */
	public ShoppingItemOffer getOfferOnThisItem()
	{
		return offerOnThisItem;
	}



	@Override
	public String toString()
	{
		StringBuilder toStringBuilder= new StringBuilder(this.getClass().getName());
		toStringBuilder.append("[");
		toStringBuilder.append("name: "); toStringBuilder.append(name);
		toStringBuilder.append(", ");
		toStringBuilder.append("price: "); toStringBuilder.append( GBP.format(price));
		toStringBuilder.append(", ");
		toStringBuilder.append("offerOnThisItem: "); toStringBuilder.append( offerOnThisItem );
		toStringBuilder.append("]");
		return toStringBuilder.toString();
	}

	
	/**
	 * equality of object based on name
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		
		if ( !(obj instanceof ShoppingItem )) { return false; }
		
		ShoppingItem rhs = (ShoppingItem) obj;
		
		return getName().equalsIgnoreCase(rhs.getName());
	}
	
	/**
	 * hashcode value based on item name
	 */
	@Override
	public int hashCode()
	{
		final int HASH_CODE_INITIAL_NO = 7;
		final int HASH_CODE_MULTIPLIER = 9;
		
		return (HASH_CODE_INITIAL_NO * HASH_CODE_MULTIPLIER) + name.hashCode();
	}
	/**
	 * natural comparison based on name
	 */
	@Override
	public int compareTo(ShoppingItem rhs)
	{
		return getName().compareTo(rhs.getName());
	}
}
