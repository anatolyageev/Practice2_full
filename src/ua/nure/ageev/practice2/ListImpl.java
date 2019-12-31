package ua.nure.ageev.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

	private int size;

	/**
	 * Pointer to first node.
	 */
	private Node head;

	/**
	 * Pointer to last node.
	 */
	private Node tail;

	/**
	 * Constructs an empty list.
	 */

	@Override
	public void addFirst(Object element) {
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
	public void addLast(Object element) {
		Node l = tail;
		Node newNode = new Node(l, element, null);
		tail = newNode;
		if (l == null) {
			head = newNode;
		} else {
			l.next = newNode;
		}
		size++;
	}

	@Override
	public void removeFirst() {
		Node f = head;
		if (f == null) {
			throw new NoSuchElementException();
		}
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
	}

	@Override
	public void removeLast() {
		Node l = tail;
		if (l == null) {
			throw new NoSuchElementException();
		}
		final Node prev = l.prev;
		l.item = null;
		l.prev = null;
		tail = prev;
		if (prev == null) {
			head = null;
		} else {
			prev.next = null;
		}
		size--;
	}

	@Override
	public Object getFirst() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		return head.item;
	}

	@Override
	public Object getLast() {
		if (tail == null) {
			throw new NoSuchElementException();
		}
		return tail.item;
	}

	@Override
	public Object search(Object element) {
		if (element == null) {
			for (Node x = head; x != null; x = x.next) {
				if (x.item == null) {
					return x.item;
				}
			}
		} else {
			for (Node x = head; x != null; x = x.next) {
				if (element.equals(x.item)) {
					return x.item;
				}
			}
		}
		return null;
	}

	public int size() {
		return this.size;
	}

	@Override
	public boolean remove(Object element) {
		if (element == null) {
			for (Node x = head; x != null; x = x.next) {
				if (x.item == null) {
					unlink(x);
					return true;
				}
			}
		} else {
			for (Node x = head; x != null; x = x.next) {
				if (element.equals(x.item)) {
					unlink(x);
					return true;
				}
			}
		}
		return false;
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
	public String toString() {
		Node h = head;
		if (h == null) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		while (h != null) {
			sb.append(h.item.toString());
			sb.append(h.next == null ? "" : ", ");
			h = h.next;
		}
		return sb.append(']').toString();
	}

	public Iterator<Object> iterator() {

		return new ListIterator();
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

	public class ListIterator implements Iterator<Object> {
		private Node nextNode;
		private Node lastReturnedNode;

		public ListIterator() {
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

	public static void main(String[] args) {

		ListImpl queue = new ListImpl();
		queue.addFirst("A");
		queue.addLast("B");
		queue.addLast("C");

		System.out.println(queue);
		System.out.println(queue.size());

		Iterator<Object> iter = queue.iterator();
		System.out.println(iter.next());
		System.out.println(iter.next());
		System.out.println(iter.next());
		iter.remove();
		System.out.println(queue);
		System.out.println(queue.size());
		System.out.println(iter.hasNext());

	}
}
