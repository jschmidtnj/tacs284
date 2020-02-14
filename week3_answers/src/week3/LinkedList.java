package week3;

import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.IllegalArgumentException;
import java.util.HashSet;

public class LinkedList<E> {
	private Node<E> head;
	private int size;

	private class Node<E> {
		private Node<E> next;
		private E data;

		public Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}

		public Node(E data) {
			this.data = data;
			this.next = null;
		}
	}

	public LinkedList() {
		this.head = null;
		this.size = 0;
	}

	public String concatenate() {
		return concatenate(this.head);
	}

	public void add(E item) {
		head = new Node<E>(item, head);
		size++;
	}

	public E get(int index) {
		if (index < 0 || index > size - 1) {
			throw new ArrayIndexOutOfBoundsException("get: index not found");
		}
		Node<E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.data;
	}

	public void removeFirst() {
		if (head == null) {
			throw new IllegalArgumentException("removefirst: cannot remove from empty list");
		}
		head = head.next;
		size--;
	}

	public Boolean member(E item) {
		Node<E> current = head;
		// comparison from left to right - short circuit vs long circuit
		// just & or | result in long circuit, && and || result in short circuit
		while (current != null && current.data != item) { // if these two are switched, you get a null pointer exception
															// (example of short circuit)
			current = current.next;
		}
		return current != null; // do it this way instead of if else statements (duh)
	}

	public void add(E item, int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("add: cannot add at this index");
		}
		// operation reused at beginning
		if (index == 0) {
			this.add(item);
		} else {
			Node<E> previous = head;
			for (int i = 0; i < index - 1; i++) {
				previous = previous.next;
			}
			Node<E> next = previous.next;
			Node<E> newNode = new Node<E>(item, next);
			previous.next = newNode;
			size++;
		}
	}

	public boolean isSingleton() {
		return size == 1;
	}

	public boolean hasDuplicate() {
		HashSet<E> set = new HashSet<E>();
		Node<E> current = head;
		while (current != null) {
			if (set.contains(current.data)) {
				return true;
			}
			set.add(current.data);
		}
		return false;
	}

	public String concatenate(Node<E> head) {
		try {
			StringBuilder result = new StringBuilder();
			for (Node<E> current = head; current != null; current = current.next) {
				result.append(current.data);
			}
			return result.toString();
		} catch (Exception e) {
			throw new IllegalArgumentException("bad argument must be a String");
		}
	}

	public <E extends Number> Integer summation(Node<E> head) {
		try {
			int result = 0;
			for (Node<E> current = head; current != null; current = current.next) {
				result = result + current.data.intValue();
			}
			return result;
		} catch (Exception e) {
			throw new IllegalArgumentException("bad argument must be an int");
		}
	}

	public Node<E> reverse(Node<E> head) {
		Node<E> current = head;
		Node<E> previous = null;
		Node<E> next;
		while (current != null) {
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		return previous;
	}

	public String toString() {
		Node<E> current = head;
		StringBuilder s1 = new StringBuilder("[");
		if (current != null) {
			s1.append(current.data);
			current = current.next;
		}
		while (current != null) {
			s1.append(", ");
			s1.append(current.data);
			current = current.next;
		}
		s1.append("]");
		return s1.toString();
	}
}
