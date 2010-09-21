package org.digitalstain.datrie.store;

/**
 * Simple abstraction for a factory that produces <tt>IntegerList</tt>s.
 * 
 * @author Chris Gioran
 */
public interface IntegerListFactory {

	/**
	 * Creates and returns an <tt>IntegerList</tt> with the implementation and
	 * options this factory was created to hold.
	 * 
	 * @return An <tt>IntegerList</tt> configured as per this factory object
	 */
	public IntegerList getNewIntegerList();
}
