package week4;

public class SortedSingleLL<E> {
	private Node head;

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
	}

	/**
	 * add
	 * 
	 * @param newData
	 */
	public void add(E newData) {

	}

	/**
	 * contains
	 * 
	 * @param data
	 */
	public boolean contains(E data) {
		return true;
	}

	/**
	 * remove
	 * 
	 * @param data
	 */
	public void remove(E data) {

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
