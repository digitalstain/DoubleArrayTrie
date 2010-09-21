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

import java.util.Arrays;

/**
 * A bare bones Array List implementation specifically designed for storing
 * integer values. It has half the memory requirements of an ArrayList&lt;Integer&gt;,
 * no pointer dereferencing for fetching the values while maintaining the access speed
 * of an ArrayList. It is perfect for creating create-once-read-many large lists of
 * integers.
 * It does not implement any of the Collections Framework interfaces, since this is
 * not a library class but an underlying store for higher lever structures and the
 * added code complexity was not necessary. It should be simple however to extend and
 * add whatever methods you need. Obviously however, generics are out of the question.
 * 
 * Of course I have been influenced by the implementation in the Sun JDK but I have
 * tried to keep true to myself. However, there aren't many ways to implement something
 * as simple as an ArrayList.
 * 
 * @author Chris Gioran
 */
public class IntegerArrayList implements IntegerList {

	private final int INCREASE_RATIO_NOMINATOR;

	private final int INCREASE_RATIO_DENOMINATOR;
	
	private final int FIXED_INCREASE;

	private int[] data;

	/**
	 * The size of this ArrayList.
	 */
	private int size;
	
	/**
	 * Constructs an empty list with an default capacity
	 */
	public IntegerArrayList() {
		this(16);
	}

	/**
	 * Constructs an empty list with the specified initial capacity.
	 * 
	 * @param initialCapacity
	 *            the initial capacity of the list
	 * @exception IllegalArgumentException
	 *                if the specified initial capacity is negative
	 */
	public IntegerArrayList(int initialCapacity) {
		this(initialCapacity, 1, 1, 1000);
	}

	/**
	 * Package protected constructor for use by factories. Besides the initial capacity,
	 * this constructor allows for specifying the growth characteristics.
	 * Whenever an increase of the storing array is needed, its new size is calculated as 
	 * 
	 * <p>newCapacity = oldCapacity*(nominator/denominator) + fixed</p>
	 * 
	 * These values cannot be changed after construction.
	 * 
	 * @param initialCapacity The initial capacity of the list
	 * @param incRatioNom The nominator of the capacity increase fraction
	 * @param incRatioDenom The denominator of the capacity increase fraction
	 * @param fixedInc The fixed value added after the multiplication
	 */
	IntegerArrayList(int initialCapacity, int incRatioNom, int incRatioDenom, int fixedInc) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Negative capacity specified " + initialCapacity);
		this.data = new int[initialCapacity];
		this.INCREASE_RATIO_NOMINATOR = incRatioNom;
		this.INCREASE_RATIO_DENOMINATOR = incRatioDenom;
		this.FIXED_INCREASE = fixedInc;
	}

	/**
	 * Ensures that this instance has enough capacity to hold
	 * at least <tt>capacity</tt> values.
	 * Runs in O(n) worst case, O(1) amortized.
	 *  
	 * @param capacity the desired minimum capacity
	 */
	private void ensureCapacity(int capacity) {
		int oldCapacity = data.length;
		if (capacity > oldCapacity) {
			int newCapacity = (oldCapacity * INCREASE_RATIO_NOMINATOR) / INCREASE_RATIO_DENOMINATOR + FIXED_INCREASE;
			if (newCapacity < capacity)
				newCapacity = capacity;
			data = Arrays.copyOf(data, newCapacity);
		}
	}

	/**
	 * @see org.digitalstain.datrie.store.IntegerList#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * @see org.digitalstain.datrie.store.IntegerList#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * @see org.digitalstain.datrie.store.IntegerList#get(int)
	 */
	@Override
	public int get(int index) {
		checkValidIndex(index);
		return data[index];
	}

	/**
	 * @see org.digitalstain.datrie.store.IntegerList#set(int, int)
	 */
	@Override
	public int set(int index, int value) {
		checkValidIndex(index);

		int oldValue = data[index];
		data[index] = value;
		return oldValue;
	}

	/**
	 * @see org.digitalstain.datrie.store.IntegerList#add(int)
	 */
	@Override
	public boolean add(int value) {
		ensureCapacity(size + 1);
		data[size++] = value;
		return true;
	}

	/**
	 * @see org.digitalstain.datrie.store.IntegerList#add(int, int)
	 */
	@Override
	public void add(int index, int value) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

		ensureCapacity(size + 1);
		System.arraycopy(data, index, data, index + 1, size - index);
		data[index] = value;
		size++;
	}

	/**
	 * @see org.digitalstain.datrie.store.IntegerList#remove(int)
	 */
	@Override
	public int remove(int index) {
		checkValidIndex(index);
		int oldValue = data[index];
		int numMoved = size - index - 1;
		if (numMoved > 0) {
			System.arraycopy(data, index + 1, data, index, numMoved);
		}
		size--;
		return oldValue;
	}

	/**
	 * Checks if the given index is less than the size of this ArrayList.
	 * The other half of the check (if it is &lt;0) is performed by the array
	 * implementation. This check is needed because the array can be larger that
	 * <tt>size</tt> so accesses beyond that will be allowed by the JVM.
	 */
	private void checkValidIndex(int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	}

	@Override
	public String toString() {
		if (size() == 0)
			return "[]";

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (int i = 0; i < size(); i++) {
			int e = get(i);
			sb.append(e);
			if (i == size() - 1)
				sb.append(']').toString();
			sb.append(", ");
		}
		return sb.toString();
	}
}
