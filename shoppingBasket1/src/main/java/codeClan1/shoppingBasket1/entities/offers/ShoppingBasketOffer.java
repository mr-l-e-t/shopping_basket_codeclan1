package codeClan1.shoppingBasket1.entities.offers;

import java.math.BigDecimal;

/**
 * @author Lucas Toledo
 * </br>
 * 11 Oct 2016
 * </br>
 * list and describe the offers applicable for each item
 */
public enum ShoppingBasketOffer
{

	TEN_PERSCENT_OFF("10% off when total is more than £20", new BigDecimal(0.10)),
	TWO_PERCENT_OFF("2% off with loyalty card", new BigDecimal(0.02));
			
	
	private final String description;
	private final BigDecimal discountToReduce;
	
	
	private ShoppingBasketOffer(String description, BigDecimal discountToReduce)
	{
		this.description = description;
		this.discountToReduce = discountToReduce;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}
	
	public BigDecimal calculateReducedValueFor(BigDecimal totalWithoutDiscount)
	{
		
		BigDecimal priceToReduceFromTotalPrice= totalWithoutDiscount.multiply(discountToReduce);
		return totalWithoutDiscount.subtract(priceToReduceFromTotalPrice);
	}
	
	
	@Override
	public String toString()
	{
		StringBuilder toStringBuilder= new StringBuilder(this.getClass().getSimpleName());
		toStringBuilder.append("[");
		toStringBuilder.append("name: "); toStringBuilder.append(this.name());
		toStringBuilder.append(", ");
		toStringBuilder.append("description: "); toStringBuilder.append( description);
		toStringBuilder.append(", ");
		toStringBuilder.append("discountToReduce: "); toStringBuilder.append( discountToReduce);
		toStringBuilder.append("]");
		return toStringBuilder.toString();
	}
	
}
