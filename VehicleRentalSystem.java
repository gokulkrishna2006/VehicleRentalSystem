package projectv;

import java.util.ArrayList;
import java.util.Scanner;

public class VehicleRentalSystem {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Vehicle> vehicles = new ArrayList<>();
    static ArrayList<Rental> rentals = new ArrayList<>();

    static int nextRentalId = 1001;

    // Vehicle class
    static class Vehicle {
        int vehicleId;
        String brand;
        String model;
        String type;
        double rentPerDay;
        boolean available;

        Vehicle(int vehicleId, String brand, String model,
                String type, double rentPerDay) {

            this.vehicleId = vehicleId;
            this.brand = brand;
            this.model = model;
            this.type = type;
            this.rentPerDay = rentPerDay;
            this.available = true;
        }

        void displayVehicle() {
            System.out.printf(
                "%-10d %-15s %-15s %-12s Rs. %-10.2f %-12s%n",
                vehicleId, brand, model, type, rentPerDay,
                available ? "Available" : "Rented"
            );
        }
    }

    // Rental class
    static class Rental {
        int rentalId;
        Vehicle vehicle;
        String customerName;
        int numberOfDays;
        double totalAmount;
        boolean returned;

        Rental(int rentalId, Vehicle vehicle, String customerName,
               int numberOfDays) {

            this.rentalId = rentalId;
            this.vehicle = vehicle;
            this.customerName = customerName;
            this.numberOfDays = numberOfDays;
            this.totalAmount = numberOfDays * vehicle.rentPerDay;
            this.returned = false;
        }

        void displayRental() {
            System.out.println("------------------------------------------");
            System.out.println("Rental ID       : " + rentalId);
            System.out.println("Customer Name   : " + customerName);
            System.out.println("Vehicle         : " + vehicle.brand
                    + " " + vehicle.model);
            System.out.println("Vehicle ID      : " + vehicle.vehicleId);
            System.out.println("Rental Days     : " + numberOfDays);
            System.out.printf("Rent Per Day    : Rs. %.2f%n",
                    vehicle.rentPerDay);
            System.out.printf("Total Amount    : Rs. %.2f%n",
                    totalAmount);
            System.out.println("Status          : "
                    + (returned ? "Returned" : "Currently Rented"));
        }
    }

    // Add sample vehicles
    public static void initializeVehicles() {

        vehicles.add(new Vehicle(
                101, "Maruti", "Swift", "Car", 1500));

        vehicles.add(new Vehicle(
                102, "Hyundai", "i20", "Car", 1800));

        vehicles.add(new Vehicle(
                103, "Toyota", "Innova", "Car", 3000));

        vehicles.add(new Vehicle(
                104, "Honda", "Activa", "Bike", 500));

        vehicles.add(new Vehicle(
                105, "Royal Enfield", "Classic350", "Bike", 1000));

        vehicles.add(new Vehicle(
                106, "Yamaha", "R15", "Bike", 800));

        vehicles.add(new Vehicle(
                107, "Tata", "Ace", "Van", 2500));
    }

    // Display all vehicles
    public static void displayVehicles() {

        System.out.println("\n================ VEHICLE LIST ================");

        System.out.printf(
            "%-10s %-15s %-15s %-12s %-15s %-12s%n",
            "ID", "Brand", "Model", "Type",
            "Rent/Day", "Status"
        );

        System.out.println(
            "--------------------------------------------------------------------------"
        );

        for (Vehicle vehicle : vehicles) {
            vehicle.displayVehicle();
        }
    }

    // Display only available vehicles
    public static void displayAvailableVehicles() {

        System.out.println("\n=========== AVAILABLE VEHICLES ===========");

        System.out.printf(
            "%-10s %-15s %-15s %-12s %-15s%n",
            "ID", "Brand", "Model", "Type", "Rent/Day"
        );

        boolean found = false;

        for (Vehicle vehicle : vehicles) {

            if (vehicle.available) {

                System.out.printf(
                    "%-10d %-15s %-15s %-12s Rs. %-10.2f%n",
                    vehicle.vehicleId,
                    vehicle.brand,
                    vehicle.model,
                    vehicle.type,
                    vehicle.rentPerDay
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No vehicles are currently available.");
        }
    }

    // Find a vehicle using its ID
    public static Vehicle findVehicle(int vehicleId) {

        for (Vehicle vehicle : vehicles) {

            if (vehicle.vehicleId == vehicleId) {
                return vehicle;
            }
        }

        return null;
    }

    // Find a rental using its ID
    public static Rental findRental(int rentalId) {

        for (Rental rental : rentals) {

            if (rental.rentalId == rentalId) {
                return rental;
            }
        }

        return null;
    }

    // Read a valid integer
    public static int readInt(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter a whole number.");
            }
        }
    }

    // Read a valid customer name
    public static String readName(String message) {

        while (true) {

            System.out.print(message);

            String name = scanner.nextLine().trim();

            if (!name.isEmpty()) {
                return name;
            }

            System.out.println("Name cannot be empty. Please try again.");
        }
    }

    // Rent a vehicle
    public static void rentVehicle() {

        System.out.println("\n============== RENT A VEHICLE ==============");

        displayAvailableVehicles();

        int vehicleId = readInt("\nEnter Vehicle ID to rent: ");

        Vehicle vehicle = findVehicle(vehicleId);

        if (vehicle == null) {
            System.out.println("Vehicle ID not found.");
            return;
        }

        if (!vehicle.available) {
            System.out.println("Sorry, this vehicle is already rented.");
            return;
        }

        String customerName = readName("Enter customer name: ");

        int days;

        do {
            days = readInt("Enter number of rental days: ");

            if (days <= 0) {
                System.out.println("Rental days must be greater than zero.");
            }

        } while (days <= 0);

        double totalAmount = vehicle.rentPerDay * days;

        System.out.println("\n============== RENTAL SUMMARY ==============");
        System.out.println("Customer Name : " + customerName);
        System.out.println("Vehicle       : "
                + vehicle.brand + " " + vehicle.model);
        System.out.println("Vehicle ID    : " + vehicle.vehicleId);
        System.out.println("Rental Days   : " + days);

        System.out.printf("Rent Per Day  : Rs. %.2f%n",
                vehicle.rentPerDay);

        System.out.printf("Total Amount  : Rs. %.2f%n",
                totalAmount);

        System.out.print("\nConfirm rental? (Y/N): ");

        String confirmation = scanner.nextLine().trim();

        if (!confirmation.equalsIgnoreCase("Y")) {
            System.out.println("Rental cancelled.");
            return;
        }

        Rental rental = new Rental(
                nextRentalId,
                vehicle,
                customerName,
                days
        );

        rentals.add(rental);

        vehicle.available = false;

        System.out.println("\nVehicle rented successfully!");
        System.out.println("Your Rental ID is: " + nextRentalId);

        System.out.println(
            "Please remember your Rental ID for returning the vehicle."
        );

        nextRentalId++;
    }

    // Return a vehicle
    public static void returnVehicle() {

        System.out.println("\n============== RETURN A VEHICLE ==============");

        int rentalId = readInt("Enter your Rental ID: ");

        Rental rental = findRental(rentalId);

        if (rental == null) {
            System.out.println("Invalid Rental ID.");
            return;
        }

        if (rental.returned) {
            System.out.println("This vehicle has already been returned.");
            return;
        }

        System.out.println("\n============== RETURN SUMMARY ==============");

        System.out.println("Customer Name : " + rental.customerName);

        System.out.println("Vehicle       : "
                + rental.vehicle.brand + " "
                + rental.vehicle.model);

        System.out.println("Rental ID     : " + rental.rentalId);

        System.out.printf("Rental Charge : Rs. %.2f%n",
                rental.totalAmount);

        System.out.println(
            "\nThe displayed charge is based on the rental period "
            + "entered when booking."
        );

        System.out.print("Confirm vehicle return? (Y/N): ");

        String confirmation = scanner.nextLine().trim();

        if (!confirmation.equalsIgnoreCase("Y")) {
            System.out.println("Return cancelled.");
            return;
        }

        rental.returned = true;
        rental.vehicle.available = true;

        System.out.println("\nVehicle returned successfully!");

        System.out.printf("Total Rental Charge: Rs. %.2f%n",
                rental.totalAmount);

        System.out.println("Thank you for using our rental service!");
    }

    // View rental history
    public static void viewRentalHistory() {

        System.out.println("\n============== RENTAL HISTORY ==============");

        if (rentals.isEmpty()) {
            System.out.println("No rental records found.");
            return;
        }

        for (Rental rental : rentals) {
            rental.displayRental();
        }

        System.out.println("------------------------------------------");
    }

    // Search for a vehicle
    public static void searchVehicle() {

        System.out.println("\n============== SEARCH VEHICLE ==============");

        System.out.print("Enter vehicle brand or model: ");

        String search = scanner.nextLine().trim();

        if (search.isEmpty()) {
            System.out.println("Search input cannot be empty.");
            return;
        }

        boolean found = false;

        System.out.printf(
            "%-10s %-15s %-15s %-12s %-15s %-12s%n",
            "ID", "Brand", "Model", "Type", "Rent/Day", "Status"
        );

        for (Vehicle vehicle : vehicles) {

            if (vehicle.brand.equalsIgnoreCase(search)
                    || vehicle.model.equalsIgnoreCase(search)) {

                vehicle.displayVehicle();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching vehicle found.");
        }
    }

    // Display menu
    public static void displayMenu() {

        System.out.println("\n============================================");
        System.out.println("          VEHICLE RENTAL SYSTEM");
        System.out.println("============================================");
        System.out.println("1. Display All Vehicles");
        System.out.println("2. Display Available Vehicles");
        System.out.println("3. Rent a Vehicle");
        System.out.println("4. Return a Vehicle");
        System.out.println("5. View Rental History");
        System.out.println("6. Search Vehicle");
        System.out.println("7. Exit");
        System.out.println("============================================");
    }

    // Main method
    public static void main(String[] args) {

        initializeVehicles();

        System.out.println("Welcome to the Vehicle Rental System!");

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    displayVehicles();
                    break;

                case 2:
                    displayAvailableVehicles();
                    break;

                case 3:
                    rentVehicle();
                    break;

                case 4:
                    returnVehicle();
                    break;

                case 5:
                    viewRentalHistory();
                    break;

                case 6:
                    searchVehicle();
                    break;

                case 7:
                    System.out.println(
                        "\nThank you for using the Vehicle Rental System!"
                    );

                    running = false;
                    break;

                default:
                    System.out.println(
                        "Invalid choice! Please select an option from 1 to 7."
                    );
            }
        }

        scanner.close();
    }
}
