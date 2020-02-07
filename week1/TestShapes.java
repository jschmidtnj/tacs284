package week1;

public class TestShapes {
    public static void main(String[] args) {
        Circle c1 = new Circle(5, "blue");
        // System.out.println(c1);
        Rectangle r1 = new Rectangle(5, 2, "red");
        // System.out.println(r1);
        Shape[] shapes = new Shape[2];
        shapes[0] = c1;
        shapes[1] = r1;
        for (Shape s : shapes) {
            System.out.println(s);
            System.out.println(s.area());
        }
        System.out.println(((Circle)shapes[0]).circumference());
    }
}
