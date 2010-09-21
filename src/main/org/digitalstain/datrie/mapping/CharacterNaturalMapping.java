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

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import org.digitalstain.datrie.AbstractDoubleArrayTrie;
import org.digitalstain.datrie.DoubleArrayTrieImpl;
import org.digitalstain.datrie.SearchResult;
import org.digitalstain.datrie.store.IntegerArrayList;
import org.digitalstain.datrie.store.IntegerList;

/**
 * @author Chris Gioran
 *
 */
public class CharacterNaturalMapping implements NaturalMapping<Character> {

	private static final int N = Character.MAX_VALUE;
	private static final CharacterNaturalMapping instance = 
		new CharacterNaturalMapping();

	/**
	 * Private constructor, this is a Singleton.
	 */
	private CharacterNaturalMapping() {
	}

	public static CharacterNaturalMapping getInstance() {
		return instance;
	}

	/**
	 * @see org.digitalstain.datrie.mapping.NaturalMapping#getN()
	 */
	@Override
	public int getN() {
		return N;
	}

	/**
	 * @see org.digitalstain.datrie.mapping.NaturalMapping#fromNatural(int)
	 */
	@Override
	public Character fromNatural(int i) {
		ByteBuffer bb = ByteBuffer.allocate(4);
		bb.putInt(i);
		bb.flip();
		return Charset.defaultCharset().decode(bb).get(3);
	}

	/**
	 * @see org.digitalstain.datrie.mapping.NaturalMapping#toNatural(java.lang.Object)
	 */
	@Override
	public int toNatural(Character object) {
		CharBuffer cb = CharBuffer.allocate(1);
		cb.put(object.charValue());
		cb.flip();
		ByteBuffer bb = Charset.defaultCharset().encode(cb);
		return bb.get();
	}

	/**
	 * @see org.digitalstain.datrie.mapping.NaturalMapping#getUnmapped()
	 */
	@Override
	public Character getUnmapped() {
		return null;
	}

	public static void main(String[] args) {
		CharacterNaturalMapping cnm = CharacterNaturalMapping.getInstance();
		String toMap1 = "abc12\n3H\tiThere!!!()";
		AbstractDoubleArrayTrie trie;
		int nat;
		IntegerList list1 = new IntegerArrayList();
		for(Character c : toMap1.toCharArray()) {
			nat = cnm.toNatural(c);
			list1.add(nat);
		}

		String toMap2 = "abcABCHi";
		IntegerList list2 = new IntegerArrayList();
		for(Character c : toMap2.toCharArray()) {
			nat = cnm.toNatural(c);
			list2.add(nat);
		}
		for(int i = 0; i < list1.size(); i++) {
			list1.set(i, list1.get(i));
		}
		for(int i = 0; i < list2.size(); i++) {
			list2.set(i, list2.get(i));
		}
		trie = new DoubleArrayTrieImpl(cnm.getN());
		trie.addToTrie(list1);
		trie.addToTrie(list2);
		assert trie.containsPrefix(list1) == SearchResult.PERFECT_MATCH;
		assert trie.containsPrefix(list2) == SearchResult.PERFECT_MATCH;
		list2.remove(list2.size() - 1);
		assert trie.containsPrefix(list2) == SearchResult.PURE_PREFIX;
		list1.set(list1.size() - 1, 90);
		assert trie.containsPrefix(list1) == SearchResult.NOT_FOUND;
	}
}
