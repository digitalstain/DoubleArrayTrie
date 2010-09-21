package org.digitalstain.datrie.store;

/**
 * Implementation of a factory class for IntegerArrayLists. Holds
 * the configuration for creating ArrayLists for <tt>int</tt>s with
 * specified initial size and growth characteristics.
 * 
 * @author Chris Gioran
 *
 */
public class IntegerArrayListFactory implements IntegerListFactory {

	private final int initialCapacity;
	private final int nominator;
	private final int denominator;
	private final int fixedInc;

	/**
	 * Private, for use by static factory methods.
	 */
	private IntegerArrayListFactory(int initialCapacity, int nominator, int denominator, int fixedInc) {
		this.initialCapacity = initialCapacity;
		this.nominator = nominator;
		this.denominator = denominator;
		this.fixedInc = fixedInc;
	}

	/**
	 * Creates and returns an <tt>IntegerListFactory</tt> that manufactures <tt>IntegerArrayList</tt>s
	 * with an initial capacity of <tt>initialCapacity</tt> and a growth factor of <p>
	 * <tt>nominator/denominator + fixedInc</tt>
	 * 
	 * @param initialCapacity The initialCapacity of the Array
	 * @param nominator
	 * @param denominator
	 * @param fixedInc
	 * @return
	 */
	public static IntegerArrayListFactory newInstance(int initialCapacity, int nominator, int denominator, int fixedInc) {
		return new IntegerArrayListFactory(initialCapacity, nominator, denominator, fixedInc);
	}

	/**
	 * Creates and returns an <tt>IntegerListFactory</tt> that manufactures <tt>IntegerArrayList</tt>s
	 * with an initial capacity of 16 and a growth factor of 5/4 + 10.
	 * 
	 * @return An IntegerArrayList with sensible defaults.
	 */
	public static IntegerArrayListFactory newInstance() {
		return newInstance(16, 5, 4, 10);
	}

	/**
	 * @see org.digitalstain.datrie.store.IntegerListFactory#getNewIntegerList()
	 */
	public IntegerList getNewIntegerList() {
		return new IntegerArrayList(initialCapacity, nominator, denominator, fixedInc);
	}
}
