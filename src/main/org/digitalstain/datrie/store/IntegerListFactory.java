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
