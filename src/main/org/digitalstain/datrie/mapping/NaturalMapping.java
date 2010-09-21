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
package org.digitalstain.datrie.mapping;

/**
 * <p>
 * Definition of expected behaviour from classes that can
 * map object of a class to and from a subset of the natural numbers.
 * </p><p>
 * Formally, implementors of this interface are expected to
 * provide an isomorphism from the class they map to a subset of the set of
 * natural numbers. In detail:
 * </p><p>
 * The allowed subsets are either
 * <li> Bounded sequences of naturals always starting at 0
 * and reaching up to <tt>n</tt> &gt; 0.
 * An isomorphism must be provided between this interval and the set of the
 * different values instances of E can take. That means that for objects
 * o1, o2 with corresponding values n1 &lt;= n, n2 &lt;= n, then o1.equals(o2)
 * if and only if n1 == n2, for every n1, n2, o1, o2.
 * </p><p>
 * No instance E can map to a natural greater that <tt>n</tt>, barring a special
 * case described below.
 * </p><p>
 * For every natural greater than <tt>n</tt>, the implementation is allowed to return
 * a class defined special value, such that for every n1, n2 &gt; n,
 * the corresponding values o1, o2 are such that o1.equals(o2)  returns true or o1 == o2 == null 
 * and for every value o3 corresponding to a n3 &lt;= n, o3.equals(o1) returns false.
 * The natural number this special object maps to must always be the same and it is recommended,
 * though not required, to be <tt>n+1</tt>.
 * </p></li><li>
 * Unbounded sequences, in which case the upper limit is defined as -1. In this case, the mapping is
 * a bijection between the possible values of the class and the set of naturals.
 * </li><p>
 * Examples are the set of chars mapped by their ordinal and the boolean literals for true = 0, false = 1. Note
 * than for neither of these two the special, above their respective n value, case is provided, although it could be
 * mapped to null.
 * </p>
 * Classes that implement this interface are expected to be implemented as Singleton objects.
 * @param E the class that is mapped 
 * @author Chris Gioran
 *
 */
public interface NaturalMapping<E> {

	/**
	 * Returns the maximum natural this class maps instance of E to. If
	 * it is unbounded, it is expected to return -1.
	 * 
	 * @return The greatest of the natural number instances of E
	 * 			maps to, -1 if unbounded.
	 */
	public int getN();

	/**
	 * Maps a natural to an instance of E. <br>
	 * For every i1, i2 &lt;= getN() then <br>
	 * fromNatural(i1).equals(fromNatural(i2)) if and only if i1 == i2.
	 * <p>Moreover, if getN() != -1 then<br> 
	 * for every i1, i2 &gt; getN(), i3 &lt;= getN(), then fromNatural(i1) != null if
	 * and only if fromNatural(i2) != null, if fromNatural(i1) != null then fromNatural(i1).equals(fromNatural(i2))
	 * returns true and fromNatural(i3).equals(i1) returns false.
	 * </p>
	 * @param i The natural
	 * @return An instance of E
	 * @throws NotANaturalException if i &lt; 0.
	 */
	public E fromNatural(int i);

	/**
	 * Maps an instance of E to a natural number.<br>
	 * For every instance o1, o2 that are not equal (as per the equals() method) to
	 * the value return getUnmapped(), then toNatural(o1) == toNatural(o2) if and only if
	 * o1.equals(o2). If getUnmapped() != null then toNatural(getUnmapped()) returns
	 * a value greater that abs(getN()).
	 *  
	 * @param object the object to map
	 * @return The natural number the argument maps to
	 */
	public int toNatural(E object);
	
	/**
	 * This method returns the object that the implementation defines to represent the
	 * value that naturals beyond its range map to. The same value must be returned for all
	 * integer values &gt; <tt>getN()</tt> if <tt>getN()</tt> != -1.
	 * 
	 * @return A special object that the natural beyond the class's range map to, possibly null
	 */
	public E getUnmapped();
}
