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
 * Implementation of a factory class for IntegerArrayLists. Holds
 * the configuration for creating ArrayLists for <tt>int</tt>s with
 * specified initial size and growth characteristics.
 * 
 * @author Chris Gioran
 *
 */
public class IntegerArrayListFactory implements IntegerListFactory {

	private final int initialCapacity;
	private final int numerator;
	private final int denominator;
	private final int fixedInc;

	/**
	 * Private, for use by static factory methods.
	 */
	private IntegerArrayListFactory(int initialCapacity, int numerator, int denominator, int fixedInc) {
		this.initialCapacity = initialCapacity;
		this.numerator = numerator;
		this.denominator = denominator;
		this.fixedInc = fixedInc;
	}

	/**
	 * Creates and returns an <tt>IntegerListFactory</tt> that manufactures <tt>IntegerArrayList</tt>s
	 * with an initial capacity of <tt>initialCapacity</tt> and a growth factor of <p>
	 * <tt>numerator/denominator + fixedInc</tt>
	 * 
	 * @param initialCapacity The initialCapacity of the Array
	 * @param numerator
	 * @param denominator
	 * @param fixedInc
	 * @return
	 */
	public static IntegerArrayListFactory newInstance(int initialCapacity, int numerator, int denominator, int fixedInc) {
		return new IntegerArrayListFactory(initialCapacity, numerator, denominator, fixedInc);
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
		return new IntegerArrayList(initialCapacity, numerator, denominator, fixedInc);
	}
}
