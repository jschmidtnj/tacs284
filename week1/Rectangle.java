package week1;

public class Rectangle extends Shape {
    private int base;
    private int height;
    Rectangle(int height, int base, String color) {
        super(color);
        this.base = base;
        this.height = height;
    }

    public double area() {
        return base * height;
    }
    // getters and setters
    public String toString() {
        return "this is a rectangle with base "
                + base + ", height " + height + ", area " +
                area() + "." + super.toString();
    }
}
