package week1;

public abstract class Shape implements Colorable {

    abstract public double area();

    Shape(String color) {
        this.color = color;
    }

    protected String color;

    public void setColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return color;
    }
    public String toString() {
        return "color is " + color;
    }
}
