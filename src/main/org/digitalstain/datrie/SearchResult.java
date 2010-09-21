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

/**
 * The result of a search operation for a string in a trie.
 * 
 * There is a distinction between finding the string as
 * a pure prefix (the search is successful but does not end at a
 * leaf node), as a perfect match (the search is successful and ends
 * at a leaf node), as a simple prefix (either of the above, i.e. the
 * search is successful), and a case of not found.
 *    
 * @author Chris Gioran
 */
public enum SearchResult {

	/**
	 * Represents a search for a string that
	 * successfully ends at a leaf node.
	 */
	PERFECT_MATCH,
	/**
	 * Represents a search for a string that
	 * successfully ends at a non-leaf node.
	 */
	PURE_PREFIX,
	/**
	 * Represents a search for a string that
	 * is successful. Not strictly necessary
	 * but useful as the disjunction of
	 * PERFECT_MATCH and PURE_PREFIX.
	 */
	PREFIX,
	/**
	 * Represents a search that was unsuccessful.
	 */
	NOT_FOUND
}
