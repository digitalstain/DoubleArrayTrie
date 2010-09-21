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
