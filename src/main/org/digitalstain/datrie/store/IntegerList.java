/*
 * Copyright 2010 Christos Gioran
 *
 * This file is part of DoubleArrayTrie.
 *
 * DoubleArrayTrie is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DoubleArrayTrie is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DoubleArrayTrie.  If not, see <http://www.gnu.org/licenses/>.
 */
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