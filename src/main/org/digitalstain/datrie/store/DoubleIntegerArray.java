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
