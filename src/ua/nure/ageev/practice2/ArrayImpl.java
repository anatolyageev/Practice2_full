package ua.nure.ageev.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

	private static final int DEFAULT_CAPACITY = 10;
	private static final Object[] EMPTY_ELEMENTDATA = {};
	protected Object[] objArray;
	protected int size;// capacity
	protected int elemCount;

	public ArrayImpl(int initCapacity) {
		if (initCapacity > 0) {
			this.objArray = new Object[initCapacity];
		} else {
			this.objArray = EMPTY_ELEMENTDATA;
		}
	}

	public ArrayImpl() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public void clear() {
		for (int i = 0; i < objArray.length; i++) {
			objArray[i] = null;
		}
		elemCount = 0;
	}

	@Override
	public int size() {
		return this.elemCount;
	}

//	private int capacity() {
//		return this.size;
//	}
//
//	private synchronized void ensureCapacity(int minCapacity) {
//		
//	}
//
//	private void grow(int minCapacity) {
//
//	}

	@Override
	public void add(Object element) {
//		ensureCapacity(elemCount + 1);
		objArray[elemCount++] = element;
	}

	@Override
	public void set(int index, Object element) {
		if (index >= elemCount) {
			throw new ArrayIndexOutOfBoundsException(index + " >= " + elemCount);
		}
		objArray[index] = element;

	}

	@Override
	public Object get(int index) {
		return objArray[index];
	}

	@Override
	public int indexOf(Object element) {
		for (int i = 0; i < elemCount; i++) {
			if (objArray[i].equals(element)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void remove(int index) {
		if (index >= elemCount) {
			throw new ArrayIndexOutOfBoundsException(index + " >= " + elemCount);
		} else if (index < 0) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		int j = elemCount - index - 1;
		if (j > 0) {
			System.arraycopy(objArray, index + 1, objArray, index, j);
		}
		/* to let gc do its work */
		objArray[--elemCount] = null;
	}

	@Override
	public String toString() {
		int index = 0;
		if (elemCount == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (Object object : this) {
			index++;
			sb.append(object);
			if (index == elemCount) {
				return sb.append(']').toString();
			}
			sb.append(", ");

		}
		return sb.toString();
	}

	@Override
	public Iterator<Object> iterator() {

		return new IteratorImpl();
	}

	private class IteratorImpl implements Iterator<Object> {
		// index of next element to return
		private int cursor;
		// index of last element returned; -1 if no such
		private int lastRet = -1;

		@Override
		public boolean hasNext() {

			return cursor < elemCount;
		}

		@Override
		public Object next() {
			int i = cursor;
			if (i >= elemCount) {
				throw new NoSuchElementException();
			}
			cursor = i + 1;
			lastRet = i;
			return objArray[lastRet];
		}

		@Override
		public void remove() {
			if (lastRet < 0) {
				throw new IllegalStateException("improper iterator state for remove operation");
			} else {
				ArrayImpl.this.remove(lastRet);
				cursor = lastRet;
				lastRet = -1;
			}
		}

	}

	public static void main(String[] args) {

		ArrayImpl array = new ArrayImpl();

		array.add("A");
		array.add("B");
		array.add("C");

		Iterator<Object> it = array.iterator();
		System.out.println(it.next());
		it.remove();
		System.out.println(array);
		System.out.println(it.next());
		it.remove();
		System.out.println(array);
		System.out.println(it.next());
		it.remove();
		System.out.println(array);

	}
}
