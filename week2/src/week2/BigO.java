package week2;

public class BigO {
	public void method1(int n) {
		// O(n)
		for (int i = 0; i < n; i++) {
			System.out.println("test");
		}
	}

	public void method2(int n) {
		// O(n^2)
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println("test");
			}
		}
	}

	public void method3(int n) {
		// O(1)
		for (int i = 0; i < n; i++) {
			System.out.println("test");
			break;
		}
	}

	public void method4(int n) {
		// n(n - 5)
		// n*n
		// O(n^2)
		for (int i = 0; i < n; i++) {
			for (int j = 5; j < n; j++) {
				System.out.println("test");
			}
		}
	}

	public void method5(int n) {
		// n * inner
		// inner = n / 2
		// n * (n / 2)
		// O(n^2)
		// i 0 1 2 3 4 5
		// j 0 0 0 0,2 0, 2 0, 2, 4
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j += 2) {
				System.out.println("test");
			}
		}
	}

	public void method6(int n) {
		// outer = n - linear
		// inner = 1, 1 * 2, 2 * 2, 4 * 2
		// 2^n => log_2(n) => upper bound for j
		// lg(n) == log_2
		// O(n * log_2(n))
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < n; j *= 2) {
				System.out.println("test");
			}
		}
	}

	public void method7(int n) {
		// runtime = 3n
		// O(n)
		for (int i = 0; i < n; i++) {
			System.out.println("test");
		}
		for (int i = 0; i < n; i++) {
			System.out.println("test");
		}
		for (int i = 0; i < n; i++) {
			System.out.println("test");
		}
	}

	public void method8(int n) {
		// TOTAL = O(n^2)
		// O(n)
		for (int i = 0; i < n; i++) {
			System.out.println("test");
		}
		// outside = O(n)
		// inside = O(n)
		// O(n^2)
		for (int i = 0; i < n; i++) {
			for (int k = n; k > 0; k--) {
				System.out.println("test");
			}
		}
	}

	public void method9(int n) {
		// O(log(log(n)))
		for (int i = 2; i < n; i *= i) {
			System.out.println("test");
		}
		for (int i = 0; i < n; i++) {
			for (int j = 2; i < n; j *= i) {
				System.out.println("test");
			}
		}
	}
}
