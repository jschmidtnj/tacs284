package week4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSortedSingleLL {

	@Test
	void add() {
		fail("Not yet implemented");
	}

	@Test
	void contains() {
		fail("Not yet implemented");
	}

	@Test
	void remove() {
		fail("Not yet implemented");
	}
	
	@Test
	void equals() {
		fail("Not yet implemented");
	}
	
	public static void main(String[] args) {
		SortedSingleLL<Integer> test = new SortedSingleLL<Integer>();
		test.add(123);
		test.add(44);
		test.add(22);
		test.add(60);
		System.out.println(test);
		System.out.println(test.contains(44));
		test.remove(44);
		System.out.println(test.contains(44));
		System.out.println(test);
		SortedSingleLL<Integer> test2 = new SortedSingleLL<Integer>();
		test.add(123);
		test.add(22);
		test.add(60);
		System.out.println(test.equals(test2));
	}

}
