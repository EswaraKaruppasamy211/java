import java.util.*;

interface Rentable {
    void rent();
    void returnVehicle();
}

class Vehicle implements Rentable {
    int id, type;
    String brand;
    double rent;
    boolean available = true;

    Vehicle(int id, String brand, double rent, int type) {
        this.id = id;
        this.brand = brand;
        this.rent = rent;
        this.type = type;
    }

    void display() {
        String name = type == 1 ? "Car" :
                      type == 2 ? "Bike" : "Truck";
        System.out.println(name + " | " + id + " | " +
                brand + " | Rs." + rent + " | " + available);
    }

    public void rent() {
        if (available) {
            available = false;
            System.out.println("Vehicle rented successfully!");
        } else {
            System.out.println("Vehicle already rented!");
        }
    }

    public void returnVehicle() {
        available = true;
        System.out.println("Vehicle returned successfully!");
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        System.out.print("Enter number of vehicles: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("1.Car  2.Bike  3.Truck");
            System.out.print("Enter vehicle type: ");
            int type = sc.nextInt();

            System.out.print("Enter ID: ");
            int id = sc.nextInt();

            System.out.print("Enter brand: ");
            String brand = sc.next();

            System.out.print("Enter rent per day: ");
            double rent = sc.nextDouble();

            vehicles.add(new Vehicle(id, brand, rent, type));
        }

        int choice;
        do {
            System.out.println("\n1.Display  2.Rent  3.Return");
            System.out.println("4.Calculate Rent  5.Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if (choice == 1) {
                for (Vehicle v : vehicles)
                    v.display();
            } else if (choice >= 2 && choice <= 4) {
                System.out.print("Enter vehicle ID: ");
                int id = sc.nextInt();

                Vehicle found = null;
                for (Vehicle v : vehicles)
                    if (v.id == id) found = v;

                if (found == null) {
                    System.out.println("Vehicle not found!");
                } else if (choice == 2) {
                    found.rent();
                } else if (choice == 3) {
                    found.returnVehicle();
                } else {
                    System.out.print("Enter number of days: ");
                    int days = sc.nextInt();
                    System.out.println("Total Rent = Rs." +
                            (found.rent * days));
                }
            } else if (choice == 5) {
                System.out.println("Thank you!");
            } else {
                System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}