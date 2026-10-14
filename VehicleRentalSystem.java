import java.util.*;
import java.sql.*;

interface Rentable {
    void rent();
    void returnVehicle();
}

abstract class Vehicle implements Rentable {
    int id;
    String brand;
    double rent;
    boolean available = true;

    Vehicle(int id, String brand, double rent) {
        this.id = id;
        this.brand = brand;
        this.rent = rent;
    }

    abstract void display();
}

class Car extends Vehicle {
    Car(int id, String brand, double rent) {
        super(id, brand, rent);
    }

    public void display() {
        System.out.println("Car " + id + " " + brand +
                " Rs." + rent + " " + available);
    }

    public void rent() {
        available = false;
        System.out.println("Car Rented Successfully!");
    }

    public void returnVehicle() {
        available = true;
        System.out.println("Car Returned Successfully!");
    }
}

class Bike extends Vehicle {
    Bike(int id, String brand, double rent) {
        super(id, brand, rent);
    }

    public void display() {
        System.out.println("Bike " + id + " " + brand +
                " Rs." + rent + " " + available);
    }

    public void rent() {
        available = false;
        System.out.println("Bike Rented Successfully!");
    }

    public void returnVehicle() {
        available = true;
        System.out.println("Bike Returned Successfully!");
    }
}

class Truck extends Vehicle {
    Truck(int id, String brand, double rent) {
        super(id, brand, rent);
    }

    public void display() {
        System.out.println("Truck " + id + " " + brand +
                " Rs." + rent + " " + available);
    }

    public void rent() {
        available = false;
        System.out.println("Truck Rented Successfully!");
    }

    public void returnVehicle() {
        available = true;
        System.out.println("Truck Returned Successfully!");
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        Class.forName("com.mysql.jdbc.Driver");

        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3307/Vehicle",
            "root", ""
        );

        int choice;

        do {
            System.out.println("\n1.Add Vehicle");
            System.out.println("2.Display Vehicles");
            System.out.println("3.Rent Vehicle");
            System.out.println("4.Return Vehicle");
            System.out.println("5.Calculate Rent");
            System.out.println("6.Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            PreparedStatement ps;
            ResultSet rs;

            switch (choice) {
                case 1:
                    System.out.print("Enter Type (Car/Bike/Truck): ");
                    String type = sc.next();

                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();

                    System.out.print("Enter Brand: ");
                    String brand = sc.next();

                    System.out.print("Enter Rent per day: ");
                    double rent = sc.nextDouble();

                    ps = con.prepareStatement(
                        "INSERT INTO vehicles VALUES(?,?,?,?,?)"
                    );
                    ps.setInt(1, id);
                    ps.setString(2, type);
                    ps.setString(3, brand);
                    ps.setDouble(4, rent);
                    ps.setBoolean(5, true);
                    ps.executeUpdate();

                    System.out.println("Vehicle Added Successfully!");
                    break;

                case 2:
                    ps = con.prepareStatement("SELECT * FROM vehicles");
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        int vid = rs.getInt("id");
                        String t = rs.getString("type");
                        String b = rs.getString("brand");
                        double r = rs.getDouble("rent");
                        boolean a = rs.getBoolean("available");

                        Vehicle v = null;

                        if (t.equalsIgnoreCase("Car"))
                            v = new Car(vid, b, r);
                        else if (t.equalsIgnoreCase("Bike"))
                            v = new Bike(vid, b, r);
                        else if (t.equalsIgnoreCase("Truck"))
                            v = new Truck(vid, b, r);

                        if (v != null) {
                            v.available = a;
                            v.display();
                        }
                    }
                    break;

                case 3:
                case 4:
                    System.out.print("Enter Vehicle ID: ");
                    id = sc.nextInt();

                    ps = con.prepareStatement(
                        "SELECT * FROM vehicles WHERE id=?"
                    );
                    ps.setInt(1, id);
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        String t = rs.getString("type");
                        brand = rs.getString("brand");
                        rent = rs.getDouble("rent");
                        boolean a = rs.getBoolean("available");

                        Vehicle v = null;

                        if (t.equalsIgnoreCase("Car"))
                            v = new Car(id, brand, rent);
                        else if (t.equalsIgnoreCase("Bike"))
                            v = new Bike(id, brand, rent);
                        else if (t.equalsIgnoreCase("Truck"))
                            v = new Truck(id, brand, rent);

                        if (v != null) {
                            v.available = a;

                            if (choice == 3 && a) {
                                v.rent();
                                a = false;
                            } else if (choice == 4 && !a) {
                                v.returnVehicle();
                                a = true;
                            } else {
                                System.out.println("Invalid Vehicle Status!");
                                break;
                            }

                            ps = con.prepareStatement(
                                "UPDATE vehicles SET available=? WHERE id=?"
                            );
                            ps.setBoolean(1, a);
                            ps.setInt(2, id);
                            ps.executeUpdate();
                        }
                    } else {
                        System.out.println("Vehicle Not Found!");
                    }
                    break;

                case 5:
                    System.out.print("Enter Vehicle ID: ");
                    id = sc.nextInt();

                    System.out.print("Enter Number of Days: ");
                    int days = sc.nextInt();

                    ps = con.prepareStatement(
                        "SELECT rent FROM vehicles WHERE id=?"
                    );
                    ps.setInt(1, id);
                    rs = ps.executeQuery();

                    if (rs.next() && days > 0)
                        System.out.println("Total Rent = Rs." +
                            rs.getDouble("rent") * days);
                    else
                        System.out.println("Invalid ID or Days!");
                    break;

                case 6:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);

        con.close();
        sc.close();
    }
}
