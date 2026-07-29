import java.util.Scanner;

abstract class Shape {
    int dim1, dim2;

    public abstract void printArea();
}

class Rectangle extends Shape {

    Rectangle(int length, int breadth) {
        dim1 = length;
        dim2 = breadth;
    }

   
    public void printArea() {
        int area = dim1 * dim2;
        System.out.println("Area of Rectangle = " + area);
    }
}

class Triangle extends Shape {

    Triangle(int base, int height) {
        dim1 = base;
        dim2 = height;
    }

   
    public void printArea() {
        double area = 0.5 * dim1 * dim2;
        System.out.println("Area of Triangle = " + area);
    }
}

class Circle extends Shape {

    Circle(int radius) {
        dim1 = radius;
    }

 
    public void printArea() {
        double area = Math.PI * dim1 * dim1;
        System.out.printf("Area of Circle = %.2f%n", area);
    }
}

public class Shapes {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Choose Shape");
        System.out.println("1. Rectangle");
        System.out.println("2. Triangle");
        System.out.println("3. Circle");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        Shape s;

        switch (choice) {
            case 1:
                System.out.print("Length: ");
                int length = sc.nextInt();
                System.out.print("Breadth: ");
                int breadth = sc.nextInt();

                s = new Rectangle(length, breadth);
                s.printArea();
                break;

            case 2:
                System.out.print("Base: ");
                int base = sc.nextInt();
                System.out.print("Height: ");
                int height = sc.nextInt();

                s = new Triangle(base, height);
                s.printArea();
                break;

            case 3:
                System.out.print("Radius: ");
                int radius = sc.nextInt();

                s = new Circle(radius);
                s.printArea();
                break;

            default:
                System.out.println("Invalid Choice");
        }

        sc.close();
    }
}