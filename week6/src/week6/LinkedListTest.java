package week6;

public class LinkedListTest<E> {
	private static class Node<E> {
		private E data;
		private Node<E> next;
		public Node(E data) {
			this.data = data;
			this.next = null;
		}
	}
	
	Node<E> head;
	
	public LinkedListTest() {
		head = null;
	}
	
	private void addFirst(E item) {
		Node<E> newNode = new Node<E>(item);
		if (head == null) {
			head = newNode;
		}
		newNode.next = head;
		head = newNode;
	}
	
	public void addLast(E item) {
		if (head == null) {
			addFirst(item);
		} else {
			Node<E> currentNode = head;
			while (currentNode.next == null) {
				currentNode = currentNode.next;
			}
			currentNode.next = new Node<E>(item);
		}
	}

	public static void main(String[] args) {
		LinkedListTest<Integer> l1 = new LinkedListTest<Integer>();
		Node<Integer> n1 = new Node<Integer>(115);
		Node<Integer> n2 = new Node<Integer>(284);
		n1.next = n2;
		System.out.println(n1.data);
		System.out.println(n1.next.data);
		l1.head = n1;
		l1.addLast(385);
		System.out.println(n2.next.data);
	}
}
