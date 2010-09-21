package org.digitalstain.datrie.store;

/**
 * Simple interface defining the behaviour of something like but not
 * quite, of an ArrayList but without generic support, just enough for
 * storing integers. There. In other words, this is the small, retarded
 * cousin of List&lt;int&gt; (note the primitive int).
 * 
 * @author Chris Gioran
 *
 */
public interface IntegerList {

	/**
	 * Returns the number of values in this list.
	 * 
	 * @return the number of values in this list
	 */
	public abstract int size();

	/**
	 * Returns <tt>true</tt> if this list contains no values.
	 * 
	 * @return <tt>true</tt> if this list contains no values
	 */
	public abstract boolean isEmpty();

	/**
	 * Returns the value at the specified position in this list.
	 * 
	 * @param index
	 *            index of the value to return
	 * @return the value at the specified position in this list
	 * @throws IndexOutOfBoundsException
	 */
	public abstract int get(int index);

	/**
	 * Replaces the value at the specified position in this list with the
	 * provided value.
	 * 
	 * @param index
	 *            index of the value to replace
	 * @param value
	 *            value to be stored at the specified position
	 * @return the value previously at the specified position
	 * @throws IndexOutOfBoundsException
	 */
	public abstract int set(int index, int value);

	/**
	 * Appends the specified value to the end of this list.
	 * 
	 * @param value value to be appended to this list
	 * @return <tt>true</tt> always
	 */
	public abstract boolean add(int value);

	/**
	 * Inserts the specified value at the specified position in this list.
	 * 
	 * @param index
	 *            index at which the specified value is to be inserted
	 * @param value
	 *            value to be inserted
	 * @throws IndexOutOfBoundsException
	 */
	public abstract void add(int index, int value);

	/**
	 * Removes the value at the specified position in this list.
	 * 
	 * @param index the index of the value to be removed
	 * @return the value that was removed from the list
	 * @throws IndexOutOfBoundsException
	 */
	public abstract int remove(int index);

}