package codeClan1.shoppingBasket1.unitTesting;

import static codeClan1.shoppingBasket1.factory.ShoppingItemFactory.getShoppingItemInstanceDifferent;
import static codeClan1.shoppingBasket1.factory.ShoppingItemFactory.getShoppingItemInstanceDifferent2;
import static codeClan1.shoppingBasket1.factory.ShoppingItemFactory.getTwinShoppingItemInstanceA;
import static codeClan1.shoppingBasket1.factory.ShoppingItemFactory.getTwinShoppingItemInstanceB;
import static java.lang.Math.signum;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import codeClan1.shoppingBasket1.entities.shopping.ShoppingItem;
/**
 * @author Lucas Toledo
 * </br>
 * 11 Oct 2016
 * </br>
 * test class to run compareTo functionality for all domain objects
 * Areas to test
 * <ul>
 * <li> The implementor must ensure sgn(x.compareTo(y)) == -sgn(y.compareTo(x)) for all x and y. (This implies that x.compareTo(y) must throw an exception iff y.compareTo(x) throws an exception.) </li>
 * <li> comparator should be consistent with equals if and only if e1.compareTo(e2) == 0 has the same boolean value as e1.equals(e2) for every e1 and e2 of class C</li>
 * <li> e.compareTo(null) should throw a NullPointerException</li>
 * <li> The implementor must also ensure that the relation is transitive: (x.compareTo(y)>0 && y.compareTo(z)>0) implies x.compareTo(z)>0. </li>
 * <li> the implementor must ensure that x.compareTo(y)==0 implies that sgn(x.compareTo(z)) == sgn(y.compareTo(z)), for all z. </li>
 * </ul>
 */
public class CompareToContractTest
{
	/**
	 * ensure sgn(x.compareTo(y)) == -sgn(y.compareTo(x)) for all x and y. 
	 * (This implies that x.compareTo(y) must throw an exception iff y.compareTo(x) throws an exception.) </li>
	 * @param <T>
	 * 
	 * @param o1
	 * @param o2
	 */
	private <T extends Comparable<T>> void assertComparisonReversal(T o1, T o2)
	{
		assertTrue("Comparison reversal should apply: sgn(o1.compareTo(o2)) == -sgn(o2.compareTo(o1)). ", signum(o1.compareTo( o2)) == -signum(o2.compareTo((T) o1)) ); 
	}
	/**
	 * comparator should be consistent with equals if and only if e1.compareTo(e2) == 0 has the same boolean value as 
	 * e1.equals(e2) for every e1 and e2 of class C
	 * @param o1
	 * @param o2
	 */
	private <T extends Comparable<T>> void assertConsistencyWithEqual(T o1, T o2)
	{
		assertEquals("o1 and o2 should be equal. Before testing comparison. ",o1, o2);
		
		assertTrue("since o1 and o2 are equals, o1.compareTo(o2) should return zero!", o1.compareTo(o2) == 0);
	}
	/**
	 * e.compareTo(null) should throw a NullPointerException
	 * @param o1
	 */
	private <T extends Comparable<T>> void assertNullPointerException(T o1)
	{
		try
		{
			o1.compareTo(null);
			fail();
		}
		catch(NullPointerException e) { }
	}
	
	/**
	 * (o3.compareTo(o2)>0 && o2.compareTo(o1)>0) implies o3.compareTo(o1)>0.
	 * 
	 * @param o1
	 * @param o2
	 * @param o3
	 */
	private <T extends Comparable<T>> void assertTransitivity(T o1, T o2, T o3)
	{
		assertTrue("("+o3+".compareTo("+o2+") > 0) && ("+o2+".compareTo("+o1+") > 0 ) && ( "+o3+".compareTo("+o1+") > 0 )", (o3.compareTo(o2) > 0) && (o2.compareTo(o1) > 0 ) && ( o3.compareTo(o1) > 0 ));
	}
	/**
	 * ensure that twinO1.compareTo(twinO2)==0 implies that sgn(twinO1.compareTo(differentO3)) == sgn(twinO2.compareTo(differentO3)), for all z.
	 * @param twinO1
	 * @param twinO2
	 * @param differentO3
	 */
	private <T extends Comparable<T>> void assertConsistency(T twinO1, T twinO2, T differentO3)
	{
		assertTrue( (twinO1.compareTo(twinO2)==0) && (signum(twinO1.compareTo(differentO3)) == signum(twinO2.compareTo(differentO3)))  );
	}
	
	@Test
	public void assertShoppingItemCompareTo()
	{
		ShoppingItem shoppingItemA = getTwinShoppingItemInstanceA();
		ShoppingItem shoppingItemB = getTwinShoppingItemInstanceB();
		ShoppingItem shoppingItemDifferent = getShoppingItemInstanceDifferent();
		ShoppingItem shoppingItemDifferent2 = getShoppingItemInstanceDifferent2();
		
		assertComparisonReversal(shoppingItemA, shoppingItemDifferent);
		
		assertConsistencyWithEqual(shoppingItemA, shoppingItemB);
		
		assertNullPointerException(shoppingItemA);
		
		assertTransitivity(shoppingItemA, shoppingItemDifferent, shoppingItemDifferent2);
		
		assertConsistency(shoppingItemA, shoppingItemB, shoppingItemDifferent);
	}
}
