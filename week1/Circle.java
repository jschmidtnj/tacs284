package week1;

public class Circle extends Shape {
    private double radius;
    public Circle(double radius, String color) {
        super(color);
        this.radius = radius;
    }
    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }
    public double circumference() {
        return 2 * Math.PI * radius;
    }
    public String toString() {
        return "this is a circle with radius " + radius +
                ", area " + area() + ". " + super.toString();
    }

}
