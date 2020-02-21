package week4;

public class SortedSingleLL<E extends Comparable<E>> {
	private Node head;
	// tail for min

	private class Node {
		private Node next;
		private E data;

		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}

		public Node(E data) {
			this.data = data;
			this.next = null;
		}
	}

	public SortedSingleLL() {
		this.head = null;
	}

	/**
	 * add
	 * 
	 * @param newData
	 */
	public void add(E newData) {
		Node curr = head;
		Node newNode = new Node(newData);
		if (head == null || head.data.compareTo(newData) >= 0) {
			curr.next = head;
			head = newNode;
		} else {
			while (curr.next != null && curr.next.data.compareTo(newData) < 0) {
				curr = curr.next;
			}
			newNode.next = curr.next;
			curr.next = newNode;
		}
	}

	/**
	 * contains
	 * 
	 * @param data
	 */
	public boolean contains(E data) {
		Node current = head;
		while (current != null) {
			if (current.data == data) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	/**
	 * remove
	 * 
	 * @param data
	 */
	public void remove(E data) {
		if (data == null) {
			return;
		}
		Node current = head;
		Node previous = null;
		while (current != null) {
			if (current.data == data) {
				if (previous != null) {
					previous.next = current.next;
				} else {
					head = current.next;
				}
				break;
			}
			previous = current;
			current = current.next;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!SortedSingleLL.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		// final SortedSingleLL<E> other = (SortedSingleLL<E>) obj;
		return true;
	}

	public String toString() {
		Node current = head;
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
