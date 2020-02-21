package week3;

public class TestLinkedList {
	public static void main(String[] args) {
		LinkedList<Integer> test = new LinkedList<Integer>();
		test.add(123);
		test.add(44);
		test.add(22);
		test.add(null);
		test.add(60);
		System.out.println(test);
		System.out.println(test.allNonNull());
		test.stutterNL(3);
		System.out.println(test);
	}
}
