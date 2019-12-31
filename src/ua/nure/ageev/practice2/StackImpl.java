package ua.nure.ageev.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

	private int size;

	/**
	 * Pointer to first node.
	 */
	private Node head;

	/**
	 * Pointer to last node.
	 */
	private Node tail;

	@Override
	public void clear() {
		Node x = head;
		while (x != null) {
			Node next = x.next;
			x.item = null;
			x.next = null;
			x = next;
		}
		head = null;
		tail = null;
		this.size = 0;
	}

	@Override
	public int size() {

		return this.size;
	}

	@Override
	public Iterator<Object> iterator() {

		return new StackIterator();
	}

	@Override
	public void push(Object element) {
		Node f = head;
		Node newNode = new Node(null, element, f);
		head = newNode;
		if (f == null) {
			tail = newNode;
		} else {
			f.prev = newNode;
		}
		size++;
	}

	@Override
	public Object pop() {
		Node f = head;
		if (f == null) {
			throw new NoSuchElementException();
		}
		Object element = f.item;
		Node next = f.next;
		f.item = null;
		f.next = null;
		head = next;
		if (next == null) {
			tail = null;
		} else {
			next.prev = null;
		}
		size--;
		return element;
	}

	@Override
	public Object top() {
		return head.item;
	}

	@Override
	public String toString() {
		Node h = tail;
		if (h == null) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		while (h != null) {
			sb.append(h.item.toString());
			sb.append(h.prev == null ? "" : ", ");
			h = h.prev;
		}
		return sb.append(']').toString();
	}

	private static class Node {
		private Object item;
		private Node next;
		private Node prev;

		Node(Node prev, Object element, Node next) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}
	}

	public class StackIterator implements Iterator<Object> {
		private Node nextNode;
		private Node lastReturnedNode;

		public StackIterator() {
			nextNode = head;
		}

		@Override
		public boolean hasNext() {
			return nextNode != null;
		}

		@Override
		public Object next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException("end of the iteration");
			}
			lastReturnedNode = nextNode;
			nextNode = nextNode.next;
			return lastReturnedNode.item;
		}

		private void unlink(Node x) {
			final Node next = x.next;
			final Node prev = x.prev;

			if (prev == null) {
				head = next;
			} else {
				prev.next = next;
				x.prev = null;
			}

			if (next == null) {
				tail = prev;
			} else {
				next.prev = prev;
				x.next = null;
			}

			x.item = null;
			size--;
		}

		@Override
		public void remove() {
			if (lastReturnedNode == null) {
				throw new IllegalStateException("improper iterator state for remove operation");
			} else {

				Node lastNext = lastReturnedNode.next;
				unlink(lastReturnedNode);
				if (nextNode == lastReturnedNode) {
					nextNode = lastNext;
				} else {
					lastReturnedNode = null;
				}
			}
		}
	}

	public static void main(String[] args) {
		StackImpl st = new StackImpl();
		st.push("A");
		st.push("B");
		st.push("C");
		System.out.println(st);
		for (Object object : st) {
			System.out.println(object);
		}
		System.out.println(st.pop());
		System.out.println(st.pop());
		System.out.println(st.pop());
	}
}
