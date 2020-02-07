package week2;

// Java 2

public class Ex2 {

	public static class Computer {
		public double getWeight() {
			System.out.println("computer get weight");
			return 0.0;
		}
	}

	public static class Desktop extends Computer {
		// unique to Desktop class
		double getWirelessKeyboardStatus() {
			System.out.println("desktop get keyboard");
			return 0.0;
		}
	}

	public static class Laptop extends Computer {
		// unique to Laptop class
		double getBatteryDuration() {
			System.out.println("laptop get battery");
			return 0.0;
		}
	}

	public static void main(String[] args) {
		Computer aComputer = new Laptop();
		Desktop aDesktop = new Desktop();
		// double a = aComputer.getWirelessKeyboardStatus();
		double b = aComputer.getWeight();
		// double c = aComputer.getBatteryDuration();
		double d = aDesktop.getWirelessKeyboardStatus();
		double e = aDesktop.getWeight();
	}
}
