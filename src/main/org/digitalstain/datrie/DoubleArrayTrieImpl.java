/**
 * 
 */
package org.digitalstain.datrie;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.digitalstain.datrie.store.IntegerArrayListFactory;
import org.digitalstain.datrie.store.IntegerList;
import org.digitalstain.datrie.store.IntegerListFactory;

/**
 * @author Chris Gioran
 */

public class DoubleArrayTrieImpl extends AbstractDoubleArrayTrie {

	// The base array.
	private IntegerList base;
	// The check array.
	private IntegerList check;
	// The free positions, for quick access
	private TreeSet<Integer> freePositions;

	/**
	 * Constructs a DoubleArrayTrie for the given alphabet length.
	 * Uses a default IntegerArrayList for storage.
	 *  
	 * @param alphabetLength The size of the set of values that
	 * 				are to be stored.
	 */
	public DoubleArrayTrieImpl(int alphabetLength) {
		this(alphabetLength, IntegerArrayListFactory.newInstance());
	}

	/**
	 * Constructs a DoubleArrayTrie for the given alphabet length that
	 * uses the provided IntegerListFactory for creating the storage.
	 * 
	 * @param alphabetLength The size of the set of values that
	 * 				are to be stored.
	 * @param listFactory The IntegerListFactory to use for creating
	 * 				the storage.
	 */
	public DoubleArrayTrieImpl(int alphabetLength, IntegerListFactory listFactory) {
		super(alphabetLength);
		init(listFactory);
	}

	protected void init(IntegerListFactory listFactory) {
		base = listFactory.getNewIntegerList();
		check = listFactory.getNewIntegerList();
		// The original offset, everything non-root starts at base(1)
		base.add(INITIAL_ROOT_BASE);
		// The root check has no meaning, thus a special value is needed.
		check.add(ROOT_CHECK_VALUE);
		freePositions = new TreeSet<Integer>();
	}

	/**
	 * Ensures that the index == <tt>limit</tt> is available from
	 * the backing arrays. If it already available, this call is
	 * almost zero overhead.
	 * @param limit The least required accessible index.
	 */
	@Override
	protected void ensureReachableIndex(int limit) {
		while (getSize() <= limit) {
			/*
			 * In essence, we let all enlargement operations to the implementing
			 * class of the backing store. Since this currently is a ArrayList,
			 * simply adding values until we are done will work.
			 */
			base.add(EMPTY_VALUE);
			check.add(EMPTY_VALUE);
			// All new positions are free by default.
			freePositions.add(base.size() - 1);
		}
	}

	/**
	 * @see org.digitalstain.datrie.AbstractDoubleArrayTrie#nextAvailableHop(int)
	 */
	@Override
	protected int nextAvailableHop(int forValue) {

		Integer value = new Integer(forValue);
		/*
		 * First we make sure that there exists a free location that is
		 * strictly greater than the value.
		 */
		while (freePositions.higher(value) == null) {
			ensureReachableIndex(base.size() + 1); // This adds to the freePositions store
		}
		/*
		 * From the termination condition of the loop above, the next line
		 * CANNOT throw NullPointerException
		 * Note that we return the position minus the value. That is because
		 * the result is the ordinal of the new state which is translated
		 * to a store index. Therefore, since we add the value to the base
		 * to find the next state, here we must subtract.
		 */
		int result = freePositions.higher(value).intValue() - forValue;
		// This assertion must pass thanks to the loop above
		assert result >= 0;
		return result;
	}

	/** 
	 * @see org.digitalstain.datrie.AbstractDoubleArrayTrie#nextAvailableMove(java.util.SortedSet)
	 */
	@Override
	protected int nextAvailableMove(SortedSet<Integer> values) {
		// In the case of a single child, the problem is solved.
		if (values.size() == 1) {
			return nextAvailableHop(values.first());
		}

		int minValue = values.first();
		int maxValue = values.last();
		int neededPositions = maxValue - minValue + 1;

		int possible = findConsecutiveFree(neededPositions);
		if (possible - minValue >= 0) {
			return possible - minValue;
		}

		ensureReachableIndex(base.size() + neededPositions);
		return base.size() - neededPositions - minValue;
	}

	/**
	 * Finds consecutive free positions in the trie.
	 * 
	 * @param amount
	 *            How many consecutive positions are needed.
	 * @return The index of the first position in the group, or -1 if
	 *         unsuccessful.
	 */
	private int findConsecutiveFree(int amount) {

		Iterator<Integer> it = freePositions.iterator();
		int from;
		Integer current;
		int previous;
		boolean found;

		/*
		 *  TODO This is WRONG. Positions already checked shouldn't be
		 *  checked again by advancing an iterator, since there is no way
		 *  they can form a consecutive integer set. Instead, the next
		 *  position to check at the outer loop should be the higher()
		 *  of the last failed check.   
		 */
		// For each free position.
		while (it.hasNext()) {
			found = true;
			from = it.next().intValue();
			// We start searching from the current Iterator position.
			current = from;
			previous = current;
			// For each needed place, except the first
			for (int i = 1; i < amount; i++) {
				// Get the next greater free position.
				current = freePositions.higher(previous);
				// System.out.println("checking that higher of "+previous+ " is
				// "+current+" for free set "+freePositions);
				// If they differ exactly one, check the next
				if (current != null && current.intValue() - previous == 1) {
					previous = current;
					continue;
				}
				// Else fail.
				found = false;
				break;
			}
			// If the loop did not mark not found, just return the iterator
			// value.
			if (found) {
				// System.out.println("Requested "+amount+" free positions and
				// returned "+from+" from free position set "+freePositions);
				return from;
			}
		}
		// Could not find free spots, return -1.
		return -1;
	}

	/**
	 * @see org.digitalstain.datrie.AbstractDoubleArrayTrie#getBase(int)
	 */
	@Override
	protected int getBase(int position) {
		return base.get(position);
	}

	/**
	 * @see org.digitalstain.datrie.AbstractDoubleArrayTrie#getCheck(int)
	 */
	@Override
	protected int getCheck(int position) {
		return check.get(position);
	}

	/**
	 * @see org.digitalstain.datrie.AbstractDoubleArrayTrie#setBase(int, int)
	 */
	@Override
	protected void setBase(int position, int value) {
		base.set(position, value);
		if (value == EMPTY_VALUE) {
			freePositions.add(new Integer(position));
		}
		else {
			freePositions.remove(new Integer(position));
		}
	}

	/**
	 * @see org.digitalstain.datrie.AbstractDoubleArrayTrie#setCheck(int, int)
	 */
	@Override
	protected void setCheck(int position, int value) {
		check.set(position, value);
		if (value == EMPTY_VALUE) {
			freePositions.add(new Integer(position));
		}
		else {
			freePositions.remove(new Integer(position));
		}
	}

	/**
	 * @see org.digitalstain.datrie.AbstractDoubleArrayTrie#getSize()
	 */
	@Override
	protected int getSize() {
		return base.size();
	}
	
	/**
	 * @see org.digitalstain.datrie.AbstractDoubleArrayTrie#updateSearch(int, int, org.digitalstain.datrie.store.IntegerList)
	 */
	@Override
	protected void updateSearch(int state, int stringIndex, IntegerList searchString) {
		// No op
	}

	/**
	 * @see org.digitalstain.datrie.AbstractDoubleArrayTrie#updateInsert(int, int, org.digitalstain.datrie.store.IntegerList)
	 */
	@Override
	protected void updateInsert(int state, int stringIndex, IntegerList insertString) {
		// No op
	}

	/**
	 * @see org.digitalstain.datrie.AbstractDoubleArrayTrie#updateChildMove(int, int, int)
	 */
	@Override
	protected void updateChildMove(int parentIndex, int forCharacter,
			int newParentBase) {
		assert getCheck(getBase(parentIndex) + forCharacter) == parentIndex;  
	}

	/**
	 * @see org.digitalstain.datrie.AbstractDoubleArrayTrie#updateStateMove(int, int)
	 */
	@Override
	protected void updateStateMove(int stateIndex, int newBase) {
		// No op
	}
}
