package week2;

// Java 1

public class Ex1 {
	public static class AClass {
		public AClass() {
			// constructor
		}

		public boolean readFile(String s) {
			System.out.println("read file AClass");
			return false;
		} // method1

		public boolean setValue(int i) {
			System.out.println("set val AClass");
			return false;
		} // method2
	}

	public static class AnotherClass extends AClass {
		public AnotherClass() {
			// constructor
		}

		public boolean readFile(String s) {
			System.out.println("read file Another");
			return false;
		} // method3

		public boolean setValue(double d) {
			System.out.println("set val Another");
			return false;
		}; // method4
	}

	public static void main(String[] args) {
		AClass obj = new AnotherClass();
		obj.setValue(3);
		AnotherClass obj2 = new AnotherClass();
		obj2.setValue(3.0);
		AClass obj3 = new AnotherClass();
		// obj3.setValue(3.0);
	}
}
