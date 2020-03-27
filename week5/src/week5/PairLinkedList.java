package week5;

/**
 * 
 * compresses linked list odd numbers into pairs
 * @author Joshua
 *
 */
public class PairLinkedList {

	private static class Node {
		private Integer data;
		private Node next;

		/**
		 * Creates a new node with a null next field
		 * 
		 * @param dataItem The data stored
		 */

		private Node(int dataItem) {
			data = dataItem;
			next = null;
		}
	}

	private static class Pair {
		private Integer data;
		private Integer copy_count;
		private Pair next;

		/**
		 * Creates a new pair with a null next field
		 * 
		 * @param dataItem The data stored
		 */

		private Pair(Integer dataItem) {
			data = dataItem;
			next = null;
		}

		/**
		 * set the number of copies as copy
		 * 
		 * @param copy
		 */
		private void set_copy(Integer copy) {
			copy_count = copy;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("(");
			sb.append(data.toString());
			sb.append(", ");
			sb.append(copy_count);
			sb.append(")");
			return sb.toString();
		}
	}

	Pair head;

	/**
	 * return the string of the linked list
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		// sb.append("[");
		Pair p = head;
		if (p != null) {
			while (p.next != null) {
				sb.append(p);
				sb.append(", ");
				p = p.next;
			}
			sb.append(p);
		}
		// sb.append("]");
		return sb.toString();
	}

	public void compress(Node node_head) {
		Node current = node_head;
		if (node_head == null) {
			return;
		}
		while (current != null && current.data % 2 == 0) {
			current = current.next;
		}
		if (current == null) {
			return;
		}
		Pair current_pair = new Pair(current.data);
		head = current_pair;
		int current_count = 0;
		while (current != null) {
			if (current.data % 2 == 0) {
				current = current.next;
				continue;
			}
			if (current.data != current_pair.data) {
				current_pair.set_copy(current_count);
				Pair new_pair = new Pair(current.data);
				current_pair.next = new_pair;
				current_pair = new_pair;
				current_count = 1;
			} else {
				current_count++;
			}
			current = current.next;
		}
		current_pair.set_copy(current_count);
	}

	public static void main(String[] args) {

		Node n1 = new Node(4);
		Node n2 = new Node(4);
		Node n3 = new Node(4);
		Node n4 = new Node(2);
		Node n5 = new Node(3);
		Node n6 = new Node(3);
		Node n7 = new Node(2);
		Node n8 = new Node(3);
		Node n9 = new Node(2);
		Node n10 = new Node(1);
		Node n11 = new Node(1);

		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n8;
		n8.next = n9;
		n9.next = n10;
		n10.next = n11;

		PairLinkedList pll = new PairLinkedList();

		// System.out.println(pll);
		pll.compress(n1);
		System.out.println(pll);
	}
}
