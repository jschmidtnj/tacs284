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
