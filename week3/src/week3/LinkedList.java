package week3;

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

	public void add(E newData) {
		head = new Node<E>(newData, head);
		size++;
	}
	
	public boolean isSingleton() {
		return size == 1;
	}
	
	public boolean allNonNull() {
		Node<E> curr = head;
		for (int i = 0; i < size; i++, curr = curr.next) {
			if (curr.data == null) {
				return false;
			}
		}
		return true;
	}

	public boolean mem(E el) {
		Node<E> curr = head;
		while (curr != null) {
			if (curr.data == el) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * SingleLL<E> stutterNL(Integer n) that repeats each element in the list n times. Eg. Given [1, 2, 3] and
	the number 3, it should return [1, 1, 1, 2, 2, 2, 3, 3, 3].	
	 */
	public void stutterNL(Integer n) {
		Node<E> curr = head;
		while (curr != null) {
			Node<E> next = curr.next;
			for (Integer i = 0; i < n - 1; i++) {
				curr.next = new Node<E>(curr.data);
				curr = curr.next;
			}
			curr.next = next;
		}
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
