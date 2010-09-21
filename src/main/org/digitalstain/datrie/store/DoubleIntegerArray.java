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


public class DoubleIntegerArray implements JointIntegerArray {

	private IntegerList first;
	private IntegerList second;

	public DoubleIntegerArray(int initialCapacity) {
		this.first = new IntegerArrayList(initialCapacity);
		this.second = new IntegerArrayList(initialCapacity);
	}

	@Override
	public int size() {
		assert first.size() == second.size() : "Sizes are not equal"; 
		return first.size();
	}

	@Override
	public int getFirst(int index) {
		return first.get(index);
	}

	@Override
	public int getSecond(int index) {
		return second.get(index);
	}

	@Override
	public void setFirst(int index, int element) {
		first.set(index, element);
	}

	@Override
	public void setSecond(int index, int element) {
		second.set(index, element);
	}

	@Override
	public void add(int first, int second) {
		this.first.add(first);
		this.second.add(second);
	}

	@Override
	public void remove(int index) {
		this.first.remove(index);
		this.second.remove(index);
		
	}

}
