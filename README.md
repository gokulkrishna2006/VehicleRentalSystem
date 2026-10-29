# Vehicle Rental System

A console-based **Vehicle Rental System** developed in Java. The application allows users to view vehicles, rent and return vehicles, search for vehicles, and view rental history.

## Features

- Display all vehicles
- Display only available vehicles
- Rent a vehicle
- Return a rented vehicle
- View rental history
- Search vehicles by brand or model
- Automatic rental charge calculation
- Vehicle availability tracking
- Input validation for user entries
- Rental ID generation

## Technologies Used

- Java
- ArrayList
- Scanner
- Object-Oriented Programming (OOP)

## Project Structure

The project uses a main `VehicleRentalSystem` class with two inner classes:

- **Vehicle** — stores vehicle ID, brand, model, type, rent per day, and availability.
- **Rental** — stores rental ID, vehicle details, customer name, rental duration, total amount, and return status.

## Available Vehicles

The system is initialized with sample vehicles including:

- Maruti Swift
- Hyundai i20
- Toyota Innova
- Honda Activa
- Royal Enfield Classic350
- Yamaha R15
- Tata Ace

## Menu Options

```text
1. Display All Vehicles
2. Display Available Vehicles
3. Rent a Vehicle
4. Return a Vehicle
5. View Rental History
6. Search Vehicle
7. Exit
```

## How to Run

1. Make sure Java is installed on your system.
2. Place `VehicleRentalSystem.java` inside the `projectv` package/folder.
3. Compile the program.
4. Run the `VehicleRentalSystem` class.
5. Select the required option from the console menu.

## Rental Process

1. Select **Rent a Vehicle**.
2. Choose an available vehicle using its Vehicle ID.
3. Enter the customer name.
4. Enter the number of rental days.
5. Review the calculated rental amount.
6. Confirm the rental.
7. Save the generated Rental ID for returning the vehicle.

## Return Process

1. Select **Return a Vehicle**.
2. Enter the Rental ID.
3. Review the return summary.
4. Confirm the return.
5. The vehicle becomes available for rental again.

## Author Details

Roll Number:
	
Name: GOKUL KRISHNA S