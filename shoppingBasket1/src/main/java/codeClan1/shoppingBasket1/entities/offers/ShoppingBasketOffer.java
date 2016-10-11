package codeClan1.shoppingBasket1.entities.offers;

/**
 * @author Lucas Toledo
 * </br>
 * 11 Oct 2016
 * </br>
 * list and describe the offers applicable for each item
 */
public enum ShoppingBasketOffer
{

	TEN_PERSCENT_OFF_OVER_TWENTY_POUNDS("10% off when total is more than £20");
			
	
	private final String description;
	
	
	
	private ShoppingBasketOffer(String description)
	{
		this.description = description;
		
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
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
