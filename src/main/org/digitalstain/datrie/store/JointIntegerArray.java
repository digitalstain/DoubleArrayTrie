package org.digitalstain.datrie.store;

/**
 * Operations that are supposed to be present on a store
 * supported by two arrays that are parallel. That means
 * they are of the same size and in general the elements
 * that correspond to the same index are accessed together.
 * Supports only <tt>int</tt> storage.
 * 
 * @author Chris Gioran
 *
 */
public interface JointIntegerArray {

	/**
	 * Returns the size of the arrays.
	 * @return The size of the arrays.
	 */
	public int size();
	
	/**
	 * Returns the element that is at position <code>index</code>
	 * at the first array.
	 * 
	 * @param index The index requested
	 * @return The element present at the requested index at the first
	 * 			array
	 */
	public int getFirst(int index);
	
	/**
	 * Returns the element that is at position <code>index</code>
	 * at the second array.
	 * 
	 * @param index The index requested
	 * @return The element present at the requested index at the second
	 * 			array
	 */
	public int getSecond(int index);
	
	/**
	 * Sets the value at <code>index</code> of the first array
	 *  to the value <code>element</code>
	 * @param index The position to set.
	 * @param element The value to set.
	 */
	public void setFirst(int index, int element);
	
	/**
	 * Sets the value at <code>index</code> of the second array
	 *  to the value <code>element</code>
	 * @param index The position to set.
	 * @param element The value to set.
	 */
	public void setSecond(int index, int element);
	
	/**
	 * Appends to the list, increasing its size if necessary.
	 * @param first The element to append to the first array
	 * @param second The element to append to the second array
	 */
	public void add(int first, int second);
	
	/**
	 * Removes the element at <code>index</code> from both arrays
	 * @param index The index to remove
	 */
	public void remove(int index);
}
