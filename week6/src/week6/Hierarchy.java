package week6;

public class Hierarchy {
	private interface intA {
		public void method1();
	}

	private interface intB {
		public void method2();
	}

	private abstract class abstractClass {

		public abstractClass() {
		}

		public String getString() {
			return "test123";
		}

		public abstract void method3();
	}

	public class mainClass extends abstractClass implements intA, intB {
		public mainClass() {
			
		}

		public void method1() {

		}

		public void method2() {

		}

		public void method3() {

		}
	}

	public class otherClass {
		public void useMain() {
			abstractClass obj1 = new mainClass();
			obj1.method3();
		}
	}

	public static void main(String[] args) {

	}
}
