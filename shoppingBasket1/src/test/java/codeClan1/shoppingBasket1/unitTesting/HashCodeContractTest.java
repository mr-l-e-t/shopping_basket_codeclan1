package codeClan1.shoppingBasket1.unitTesting;
import static codeClan1.shoppingBasket1.factory.ShoppingItemFactory.getTwinShoppingItemInstanceA;
import static codeClan1.shoppingBasket1.factory.ShoppingItemFactory.getTwinShoppingItemInstanceB;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import codeClan1.shoppingBasket1.entities.shopping.ShoppingItem;

/**
 * @author Lucas Toledo
 * </br>
 * 11 Oct 2016
 * </br>
 *
 * Areas to test for hashCode()
 * <ul>
 * 	<li>whenever it is invoked on the same object, more than once during execution, hashcode must consistently return the same integer, provided that no information used in equals comparisosns on the object is modified. </li>
 * 	<li>if two objects are equals, then hashCode must produce the same integer result</li>
 * </ul>
 */
public class HashCodeContractTest
{
	/**
	 * Whenever it is invoked on the same object more than once during an execution of a Java application, the hashCode method must consistently return the same integer
	 */
	private void assertHashCodeConsistency(Object o1)
	{
		assertEquals("object hashcode consistency with itself failed! Weird. ", o1.hashCode(), o1.hashCode());
	}
	private void assertHashCodeEquality(Object o1, Object o2)
	{
		if (o1.equals(o2))
		{
			assertEquals("if o1 and o2 are equals, then they should have the same hashcode!", o1.hashCode(), o2.hashCode());
		}
	}
	
	@Test
	public void testShoppingItemHashCode()
	{
		ShoppingItem shoppingItemA = getTwinShoppingItemInstanceA();
		ShoppingItem shoppingItemB = getTwinShoppingItemInstanceB();
		
		assertHashCodeConsistency( shoppingItemA );
		assertHashCodeEquality( shoppingItemA, shoppingItemB );
	}
}
