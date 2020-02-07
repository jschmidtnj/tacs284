package week1;

public class Polymorphism {
    private void show() {
        System.out.println("show parent");
    }
}

/*
public class privateMethod extends Polymorphism {
    public void show() {
        System.out.println("show child");
    }

    public static void main(String[] args) {
        Polymorphism p = new privateMethod();
        // errors out:
        p.show();
    }
}
*/
