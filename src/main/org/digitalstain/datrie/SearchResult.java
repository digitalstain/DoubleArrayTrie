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
