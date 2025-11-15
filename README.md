**Warehouse & Truck Management System (A Java Swing Project)**

This is a desktop application built entirely in Java Swing that simulates a basic warehouse and truck management system.

The main goal was to build a complete application with a graphical user interface (GUI), handle multiple data models (trucks, batches, workers), and manage different user roles (an Admin vs. a warehouse Worker).

**CORE FEATURES:**

The application is split into two main user roles, which you choose from on the home screen:

**1. Admin Panel**

This is the "command center" for managing the whole warehouse. From here, you can:

Manage Trucks: Add new trucks to the fleet, remove old ones, and view a list of all trucks.

Manage Batches: Create new inventory batches, delete them, and, most importantly, assign them to a specific truck for delivery or receiving.

Manage Personnel: Add new drivers and warehouse workers to the system.

View Status: Get a quick overview of all trucks, batches, drivers, and workers.

Manage Warehouse: Define different shelf types for organizing inventory.

**2. Worker Station**

This is a simplified, task-focused UI for a worker on the warehouse floor.

Select Station: A worker can choose to be at the "Incoming" or "Outgoing" station.

View Trucks: The app shows only the trucks relevant to that station (e.g., incoming trucks for the incoming station).

Update Batch Status: The worker can select a truck, see all the batches assigned to it, and update the status of each batch (e.g., mark it as "Loaded" or "Unloaded").

**How to Run It**

This is a pure Java Swing app, so there's no complex setup.

Get the Code: Make sure you have all the .java files in one folder.

Java Needed: You'll need a Java Development Kit (JDK) installed (e.g., JDK 11, 17, or 21).

Compile: Open a terminal or command prompt in that folder and run:

javac *.java


Run: Once everything is compiled, run the main class:

java TruckManagementApp


This will launch the main window, and you can get started.

A Quick Look at the Code

TruckManagementApp.java: This is the main file. It has the main method that starts the app. It also contains all the code for the "Worker Station" UI.

MenuAdmin.java: This file builds that big admin menu. All the UI and button logic for the admin panel lives here.

ProjectDatabase.java: This is our "database" for now. It's just a Java class that holds all the ArrayLists and HashMaps to store data while the app is running. (Note: This means all data resets when you close the app!)

ProjectMethods.java: A helper class to keep things cleaner. It has reusable logic, like finding a truck by its ID, getting all shelves of a certain type, etc.

Batch.java, Truck.java, Driver.java, Worker.java, Shelf.java: These are just the data "model" classes. They're simple objects that hold information.

What's Next? (Future Improvements)

This project is a solid foundation, but there's a ton of room for improvement.

A Real Database: The biggest one. The current ProjectDatabase class is a temporary fix. The next step is to connect this to a real database (like MySQL, PostgreSQL, or even a simple SQLite file) so that data is saved permanently.

A Better UI: Java Swing is functional, but it's not the prettiest. The UI (especially in MenuAdmin) could be completely rebuilt to be cleaner, more responsive, and just... look better. (Maybe using GridBagLayout more effectively, or even moving to JavaFX or a web-based UI).

Refactor MenuAdmin.java: That file is huge. A lot of the panel-creation methods are very similar. This could be refactored to use a single, reusable function that builds a panel, instead of having createAddTruckPanel, createRemoveTruckPanel, etc., all as separate methods.

