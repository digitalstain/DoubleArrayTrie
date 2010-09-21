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
package org.digitalstain.datrie;

import org.digitalstain.datrie.store.IntegerArrayListFactory;
import org.digitalstain.datrie.store.IntegerList;

/**
 * @author chris
 *
 */
public class CountingTrie extends DoubleArrayTrieImpl {

	private IntegerList existCounts;
	private IntegerList searchCounts;

	public CountingTrie(int alphabetLength) {
		super(alphabetLength);
		existCounts = IntegerArrayListFactory.newInstance().getNewIntegerList();
		existCounts.add(0);
		searchCounts = IntegerArrayListFactory.newInstance().getNewIntegerList();
		searchCounts.add(0);
	}

	@Override
	protected void ensureReachableIndex(int limit) {
		super.ensureReachableIndex(limit);
		while(existCounts.size() <= limit) {
			existCounts.add(0);
		}
		while(searchCounts.size() <= limit) {
			searchCounts.add(0);
		}
	}

	@Override
	protected void updateChildMove(int parentIndex, int forCharacter,
			int newParentBase) {
		super.updateChildMove(parentIndex, forCharacter, newParentBase);
		int oldCount = existCounts.get(getBase(parentIndex)+forCharacter);
		existCounts.set(newParentBase+forCharacter, oldCount);
		existCounts.set(getBase(parentIndex)+forCharacter, 0);
		
		oldCount = searchCounts.get(getBase(parentIndex)+forCharacter);
		searchCounts.set(newParentBase+forCharacter, oldCount);
		searchCounts.set(getBase(parentIndex)+forCharacter, 0);
	}

	@Override
	protected void updateInsert(int state, int stringIndex,
			IntegerList insertString) {
		super.updateInsert(state, stringIndex, insertString);
		existCounts.set(state, existCounts.get(state)+1);
	}

	@Override
	protected void updateSearch(int state, int stringIndex,
			IntegerList searchString) {
		// TODO Auto-generated method stub
		super.updateSearch(state, stringIndex, searchString);
		if (stringIndex == searchString.size() - 1)
			searchCounts.set(state, searchCounts.get(state)+1);
	}

	public int getSearchCountFor(IntegerList prefix) {
		SearchState state = runPrefix(prefix);
		if (state.index == prefix.size()-1) 
			return searchCounts.get(state.finishedAtState);
		else return 0;
	}
}
