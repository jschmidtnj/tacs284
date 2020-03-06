package week6;

public class BigO {

	public void method1(int n) {
		// O(n^2)
		for (int i = 0; i < n; i++) {
			for (int j = n; j > i; j--) {
				System.out.println("test");
			}
		}
	}

	@SuppressWarnings("unused")
	public void method2(int n) {
		// O(n^2)
		for (int i = 0; i < n; i++) {
			System.out.println("test");
		}
		for (int i = n / 2; i < n; i++) {
			for (int j = i; j < n; j++) {
				System.out.println("test");
			}
		}
		for (int i = 0; i < n; i++) {
			return;
		}
	}

	public void method3(int n) {
		// O(n * log(n))
		for (int i = n / 4; i < n; i++) {
			for (int j = 1; j < n; j *= 2) {
				System.out.println("test");
			}
		}
	}

	@SuppressWarnings("unused")
	public void method4(int n) {
		// O(n)
		for (int i = 2; i < n; i++) {
			System.out.println("test");
		}
		for (int i = n / 2; i < n; i++) {
			System.out.println("test");
		}
		for (int i = 0; i < n; i++) {
			break;
		}
	}
}
