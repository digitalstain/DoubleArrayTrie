/**
 * 
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
