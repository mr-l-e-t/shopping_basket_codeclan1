package codeClan1.shoppingBasket1.unitTesting;

import static codeClan1.shoppingBasket1.factory.ShoppingItemFactory.getShoppingItemInstanceDifferent;
import static codeClan1.shoppingBasket1.factory.ShoppingItemFactory.getTwinShoppingItemInstanceA;
import static codeClan1.shoppingBasket1.factory.ShoppingItemFactory.getTwinShoppingItemInstanceB;
import static codeClan1.shoppingBasket1.factory.ShoppingItemFactory.getTwinShoppingItemInstanceC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import codeClan1.shoppingBasket1.entities.shopping.ShoppingItem;
/**
 * @author Lucas Toledo
 * </br>
 * 11 Oct 2016
 * </br>
 * Generic tester class for testing that equals complies with equals contract.
 * 
 * Areas to test for equals():
 * <ul>
 * <li>It is reflexive: for any non-null reference value x, x.equals(x) should return true.</li>
 * <li>It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.</li>
 * <li>It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true.</li>
 * <li>It is consistent: for any non-null reference values x and y, multiple invocations of x.equals(y) consistently return true or consistently return false, provided no information used in equals comparisons on the objects is modified.</li>
 * <li>For any non-null reference value x, x.equals(null) should return false.</li>
 * </ul>
 */
public class EqualsContractTest
{
	/**
	 * according to Object.equals
	 *for any non-null reference value x, x.equals(x) should return true. 
	 * @param o
	 */
	private void assertReflexive(Object o)
	{
		assertEquals("Object o should be reflexibly equal to itself.", o, o);
	}
	/**
	 * for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true. 
	 * @param o1
	 * @param o2
	 */
	private void assertSymmetric(Object o1, Object o2)
	{
		assertEquals("o1 and o2 should be symetrically equal to each other.", o1, o2);
		
		assertEquals("o2 and o1 should be symetrically equal to each other.",o2, o1);
	}
	/**
	 * for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true. 
	 * @param o1
	 * @param o2
	 * @param o3
	 */
	private void assertTransitive(Object o1, Object o2, Object o3)
	{
		assertEquals("o1 should transitively be equal to o2.", o1, o2);
		
		assertEquals("o2 should transitively be equal to o3.", o2, o3);
		
		assertEquals("o1 should transitively be equal to o3.", o1, o3);
	}
	/**
	 * For any non-null reference value x, x.equals(null) should return false. 
	 * @param o1
	 */
	private void assertNonNullity(Object o1)
	{
		assertFalse("o1 should not be equals to null!", o1.equals(null));
	}
	/**
	 * assert that o1 and o2 are different since they have different values
	 * @param o1
	 * @param o2
	 */
	private void assertDifferent(Object o1, Object o2)
	{
		assertFalse("o1 should not be equals to o2.", o1.equals(o2));
	}
	
	/**
	 * test
	 */
	@Test
	public void testShoppingItemEquals()
	{
		ShoppingItem shoppingItemA = getTwinShoppingItemInstanceA();
		ShoppingItem shoppingItemB = getTwinShoppingItemInstanceB();
		ShoppingItem shoppingItemC = getTwinShoppingItemInstanceC();
		ShoppingItem shoppingItemDifferent = getShoppingItemInstanceDifferent();
		
		assertReflexive( shoppingItemA );

		assertSymmetric( shoppingItemA, shoppingItemB);	

		assertTransitive( shoppingItemA, shoppingItemB, shoppingItemC);
		
		assertNonNullity( shoppingItemA);
		
		assertDifferent( shoppingItemA, shoppingItemDifferent);
		
	}
	
	
}
