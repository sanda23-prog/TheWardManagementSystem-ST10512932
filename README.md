# TheWardManagementSystem-ST10512932
A Java-based hospital ward management system for registering and managing patients, allocating and releasing beds, viewing ward layouts, and generating reports. The project demonstrates OOP concepts including encapsulation, inheritance, polymorphism, enums, ArrayLists, and JUnit unit testing.

🏥 Medicare Hospital Ward Management System
📌 Project Overview

The Medicare Hospital Ward Management System is a Java-based console application designed to assist hospital staff in managing patients, hospital beds, admissions, discharges, and ward information.

The system provides a simple menu-driven interface that allows users to:

Register new patients
Search for patients
Update patient information
Delete patient records
Categorize patients
Allocate beds to inpatients
Release beds when patients are discharged
View the complete ward layout
View available and occupied beds
Generate a ward summary report

The application demonstrates important Object-Oriented Programming (OOP) concepts in Java, including:

Classes and Objects
Encapsulation
Inheritance
Polymorphism
Enumeration (enum)
ArrayLists
Two-dimensional arrays
Methods
Constructors
Exception handling
User input validation
Conditional statements
Loops
🎯 Project Objectives

The main objectives of this project are to:

Manage hospital patient records efficiently.
Categorize patients according to their admission type.
Manage the allocation and release of hospital beds.
Display the current occupancy of the hospital ward.
Provide a simple interface for hospital staff.
Demonstrate practical application of Java OOP concepts.
Generate useful ward statistics and reports.
🏥 System Features
1. Patient Registration

The system allows hospital staff to register new patients.

When registering a patient, the following information is captured:

Patient ID
First Name
Last Name
Age
Gender
Medical Condition
Patient Category
Patient Categories

Patients can belong to one of three categories:

INPATIENT
OUTPATIENT
EMERGENCY
Inpatients

An inpatient is a patient who requires hospital admission and a physical bed in the ward.

Outpatients

An outpatient receives medical treatment without being admitted to a hospital bed.

Emergency Patients

Emergency patients are patients who require urgent medical attention.

2. Patient Search

Users can search for a patient using their unique Patient ID.

Example:

Enter Patient ID to Search: P01

If the patient exists, the system displays their information.

If the patient does not exist:

Patient not found.

Patient IDs are searched using a case-insensitive comparison, meaning:

P01
p01

will refer to the same patient.

3. Update Patient Details

The system allows users to update existing patient information.

The following information can be changed:

First Name
Last Name
Age
Medical Condition

Users can leave a field blank to keep the existing information.

Example:

Enter New First Name [John]:
Enter New Last Name [Smith]:
Enter New Age [35]:
Enter New Medical Condition [Flu]:
4. Delete Patient

Users can delete a patient using their Patient ID.

If the patient is currently an inpatient, the system automatically releases their allocated bed before deleting their patient record.

Example:

Enter Patient ID to Delete: P01
✓ Patient deleted successfully.
5. Bed Management

The hospital ward contains a total of 20 beds.

The beds are arranged in a:

4 × 5

layout.

The system automatically creates beds:

B01
B02
B03
...
B20
Example Ward Layout
[  B01] [  B02] [X B03] [  B04] [  B05]
[  B06] [  B07] [  B08] [X B09] [  B10]
[  B11] [  B12] [  B13] [  B14] [  B15]
[  B16] [X B17] [  B18] [  B19] [  B20]

Where:

[  B01] = Available bed
[X B03] = Occupied bed
6. Allocate Bed

The system allows an available bed to be allocated to a patient.

Before allocating a bed, the system checks:

Whether the patient exists
Whether the patient already has a bed
Whether the ward is full
Whether the entered bed ID is valid
Whether the selected bed is already occupied

Example:

Enter Patient ID to allocate bed: P01

Enter Bed ID to allocate: B05

✓ Bed B05 successfully allocated to Patient P01.

When a normal Patient is allocated a bed, the system converts the patient into an Inpatient.

This demonstrates inheritance and polymorphism.

7. Release Bed / Discharge

When an inpatient leaves the ward, their bed can be released.

Example:

Enter Bed ID to Release: B05

✓ Bed B05 released successfully.
Patient marked as Outpatient.

The system:

Finds the bed.
Identifies the patient using the bed.
Releases the bed.
Removes the patient's bed allocation.
Changes the patient's category to OUTPATIENT.

The bed can then be allocated to another patient.

8. View Ward Layout

Users can view the complete 4 × 5 ward layout.

Example:

--- WARD LAYOUT (4x5) ---

[  B01] [  B02] [X B03] [  B04] [  B05]
[  B06] [  B07] [  B08] [  B09] [  B10]
[  B11] [X B12] [  B13] [  B14] [  B15]
[  B16] [  B17] [  B18] [X B19] [  B20]

Note: [X ...] represents an occupied bed.
9. View Available Beds

The system displays all beds that are currently available.

Example:

--- AVAILABLE BEDS ---

B01  B02  B04  B05  B06  B07  B08

If all beds are occupied:

No available beds. Ward is full!
10. View Occupied Beds

The system displays beds that currently have patients assigned to them.

Example:

--- OCCUPIED BEDS ---

Bed B03 -> Allocated to Patient ID: P01
Bed B12 -> Allocated to Patient ID: P04
Bed B19 -> Allocated to Patient ID: P07
11. Ward Summary Report

The system can generate a complete ward summary report.

The report provides:

Total registered patients
Total occupied beds
Total available beds
Ward occupancy rate
Registered patient information
Available beds
Occupied beds

Example:

============== MEDICARE WARD SUMMARY REPORT ==============

Total Registered Patients: 8
Total Occupied Beds:       5
Total Available Beds:     15
Ward Occupancy Rate:       25.00%

==========================================================

The occupancy rate is calculated using:

Occupied Beds / Total Beds × 100

Since the ward contains 20 beds:

Occupancy Rate = (Occupied Beds / 20) × 100
🖥️ Main Menu

When the application starts, the following menu is displayed:

============================================
  MEDICARE HOSPITAL - WARD ADMISSION SYSTEM
============================================
 1. Register New Patient
 2. Search Patient by ID
 3. Update Patient Details
 4. Delete Patient
 5. Display All Registered Patients
--------------------------------------------
 6. Allocate Bed to Patient (Inpatient)
 7. Release Bed (Discharge)
 8. View Complete Ward Layout
 9. View Available Beds
10. View Occupied Beds
--------------------------------------------
11. Generate Ward Summary Report
 0. Exit System
============================================
🧱 System Structure

The application consists of several classes and components contained within the main Java file.

WardManagementSystem
│
├── PatientCategory
│
├── Patient
│
├── Inpatient
│
├── Bed
│
├── WardSystem
│
├── Main Menu
│
├── Input Validation Methods
│
└── User Interface Methods
👨‍💻 Classes and Components
WardManagementSystem

This is the main class of the application.

It contains:

The main() method
Scanner input
Main menu
User interface methods
Patient management interactions
Bed management interactions

The application starts from:

public static void main(String[] args)
PatientCategory

The PatientCategory enum defines the different categories of patients.

enum PatientCategory {
    INPATIENT,
    OUTPATIENT,
    EMERGENCY
}

Using an enum prevents arbitrary category values from being entered into the system.

👤 Patient Class

The Patient class represents a general hospital patient.

Attributes
patientId
firstName
lastName
age
gender
medicalCondition
category
Example
Patient patient = new Patient(
    "P01",
    "John",
    "Smith",
    35,
    "M",
    "Flu",
    PatientCategory.OUTPATIENT
);

The class includes getters and setters to control access to its private attributes.

🛏️ Inpatient Class

The Inpatient class extends the Patient class.

static class Inpatient extends Patient

This means an inpatient is a specialized type of patient.

Additional attributes include:

wardNumber
bedNumber

The constructor uses:

super(...)

to initialize the attributes inherited from Patient.

The class also overrides:

displayDetails()

to display additional ward and bed information.

This demonstrates method overriding and polymorphism.

🛏️ Bed Class

The Bed class represents a hospital bed.

Attributes
bedId
isOccupied
patientId

A bed can either be:

Available

or:

Occupied
Main methods
allocateBed()
releaseBed()
isOccupied()
getBedId()
getPatientId()
🏥 WardSystem Class

The WardSystem class acts as the main controller for the hospital ward.

It manages:

Patients
Beds
Patient registration
Searching
Updating
Deleting
Bed allocation
Bed release
Ward reports

The ward is represented using:

private Bed[][] beds = new Bed[4][5];

This creates a two-dimensional array containing:

4 rows × 5 columns = 20 beds
🧠 Object-Oriented Programming Concepts

This project was designed to demonstrate several important Java OOP principles.

1. Encapsulation

The attributes of classes are declared as private.

Example:

private String patientId;
private String firstName;
private int age;

Access is provided through getters and setters.

Example:

public String getFirstName()
public void setFirstName(String firstName)

This protects the object's internal data.

2. Inheritance

The Inpatient class inherits from the Patient class.

class Inpatient extends Patient

This allows Inpatient to reuse properties and methods from Patient.

3. Polymorphism

The application demonstrates polymorphism through the displayDetails() method.

The Patient class has:

public void displayDetails()

The Inpatient class overrides it:

@Override
public void displayDetails()

The system can therefore call:

p.displayDetails();

and Java determines the appropriate implementation at runtime.

4. Abstraction

The system separates responsibilities into different classes.

For example:

Patient → Patient information
Inpatient → Inpatient-specific information
Bed → Bed information
WardSystem → Ward operations
WardManagementSystem → User interface

This makes the application easier to understand and maintain.

5. Enum

The application uses an enum for patient categories:

enum PatientCategory

This provides a controlled set of possible categories.

📦 Data Structures Used
ArrayList

Patient records are stored using:

List<Patient> patients = new ArrayList<>();

This allows the number of registered patients to grow dynamically.

Two-Dimensional Array

Beds are stored using:

Bed[][] beds = new Bed[4][5];

This represents the physical ward layout.

🛡️ Input Validation

The system contains helper methods to safely handle user input.

Integer Validation
readInt()

If the user enters something that is not a number, the system displays:

Invalid input! Please enter a valid number.

The user is then asked to try again.

String Validation
readString()

prevents empty input.

If the user does not enter anything:

Input cannot be empty.
⚙️ Technologies Used
Technology	Purpose
Java	Main programming language
Java OOP	System architecture
ArrayList	Patient storage
2D Array	Ward and bed layout
Scanner	User input
Enum	Patient categories
NetBeans	Development environment
Java Console	User interface
💻 System Requirements

To run this project, you need:

Java Development Kit (JDK)
Java IDE such as NetBeans, IntelliJ IDEA, or VS Code
Terminal/Command Prompt
A computer capable of running Java

Recommended:

JDK 17 or newer

The project can also be used with newer JDK versions that support the syntax used in the application.

🚀 How to Run the Project
Option 1: NetBeans
Step 1 — Open NetBeans

Launch NetBeans IDE.

Step 2 — Open the Project

Open the project containing:

WardManagementSystem.java
Step 3 — Check the Package

Make sure the file is located in:

com.mycompany.wardmanagementsystem
Step 4 — Build the Project

Select:

Run → Clean and Build Project
Step 5 — Run

Select:

Run → Run Project

The application should display:

Welcome to the Medicare Hospital Admission System.

followed by the main menu.

🖥️ Running From the Terminal

Navigate to the project's source directory.

Compile the Java file:

javac WardManagementSystem.java

Then run:

java WardManagementSystem

If the project uses the package structure, run it from the appropriate project directory using the fully qualified class name:

java com.mycompany.wardmanagementsystem.WardManagementSystem
🔄 Example System Workflow

A typical workflow could look like this:

Start Application
       ↓
Register Patient
       ↓
Select Patient Category
       ↓
Is Patient an Inpatient?
       ↓
      Yes
       ↓
Select Available Bed
       ↓
Allocate Bed
       ↓
Patient Becomes Inpatient
       ↓
Patient Receives Treatment
       ↓
Release Bed
       ↓
Patient Becomes Outpatient
       ↓
Generate Ward Report
       ↓
Exit System
📊 Example Usage
Registering an Inpatient
Enter Patient ID: P01
Enter First Name: John
Enter Last Name: Smith
Enter Age: 40
Enter Gender: M
Enter Medical Condition: Pneumonia

Select Category:
1. Inpatient
2. Outpatient
3. Emergency

Choice: 1

Available Beds:
B01 B02 B03 B04 B05 ...

Assign Bed Number: B01

✓ Patient P01 successfully registered!

The patient will now appear as:

Category: INPATIENT
Location: Ward W1, Bed B01
📈 Ward Capacity

The current ward has:

Total Beds: 20

Capacity:

4 rows × 5 beds = 20 beds

The system prevents additional bed allocations once all 20 beds are occupied.

⚠️ Important Notes

This project is currently a console-based prototype.

It does not currently use:

A database
A graphical user interface
User authentication
Network connectivity
Cloud storage
Persistent file storage

Patient data exists only while the program is running.

When the application is closed, the registered patients and bed allocations are lost.

🔮 Possible Future Improvements

The system could be expanded in the future with:

Database Integration

Connect the system to:

MySQL
PostgreSQL
SQLite

This would allow patient information to be permanently stored.

Graphical User Interface

A GUI could be developed using:

JavaFX
Swing
Authentication

Add different user roles such as:

Administrator
Doctor
Nurse
Receptionist
Patient Medical Records

Add:

Medication records
Doctor information
Treatment history
Admission dates
Discharge dates
Allergies
Emergency contacts
Multiple Wards

Instead of only:

Ward W1

the system could support:

Ward W1
Ward W2
Ward W3
Ward W4
Persistent Reports

Reports could be exported to:

PDF
CSV
Excel
👥 Project Purpose

This project demonstrates how Java can be used to create a basic hospital management application while applying fundamental programming and Object-Oriented Programming concepts.

It is particularly useful as an academic project for demonstrating:

Java programming
OOP
Data structures
Inheritance
Polymorphism
Encapsulation
User input handling
System design
Problem solving
📄 Project Information

Project Name: Medicare Hospital Ward Management System

Programming Language: Java

Application Type: Console-Based Application

Ward Capacity: 20 Beds

Ward Layout: 4 × 5

Patient Categories:

Inpatient
Outpatient
Emergency

Main Class:

WardManagementSystem

Package:

com.mycompany.wardmanagementsystem
👨‍💻 Author

Author: Lusanda Bulelwa Radebe

Project: Medicare Hospital Ward Management System

📜 License

This project was created for educational and academic purposes.

You are free to study, modify, and improve the source code for learning purposes.
