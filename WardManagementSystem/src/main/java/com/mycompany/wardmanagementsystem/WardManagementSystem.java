/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.wardmanagementsystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author lusanda Bulelwa Radebe
 */
public class WardManagementSystem {
    
// FEATURE 4: Patient Categories & Data Models
// 
 
enum PatientCategory {
    INPATIENT,
    OUTPATIENT,
    EMERGENCY
}
 
static class Patient {
    private String patientId;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String medicalCondition;
    private PatientCategory category;
 
    public Patient(String patientId, String firstName, String lastName, int age, String gender, String medicalCondition, PatientCategory category) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.medicalCondition = medicalCondition;
        this.category = category;
    }
 
    public String getPatientId() { return patientId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getMedicalCondition() { return medicalCondition; }
    public void setMedicalCondition(String medicalCondition) { this.medicalCondition = medicalCondition; }
    public PatientCategory getCategory() { return category; }
    public void setCategory(PatientCategory category) { this.category = category; }
 
    public void displayDetails() {
        System.out.printf("ID: %-5s | Name: %-15s | Age: %-3d | Gender: %-6s | Category: %-10s | Condition: %s\n", 
                          patientId, (firstName + " " + lastName), age, gender, category, medicalCondition);
    }
}
 
static class Inpatient extends Patient {
    private String wardNumber;
    private String bedNumber;
 
    public Inpatient(String patientId, String firstName, String lastName, int age, String gender, 
                     String medicalCondition, String wardNumber, String bedNumber) {
        // Uses super() to initialize inherited attributes
        super(patientId, firstName, lastName, age, gender, medicalCondition, PatientCategory.INPATIENT);
        this.wardNumber = wardNumber;
        this.bedNumber = bedNumber;
    }
 
    public String getWardNumber() { return wardNumber; }
    public void setWardNumber(String wardNumber) { this.wardNumber = wardNumber; }
    public String getBedNumber() { return bedNumber; }
    public void setBedNumber(String bedNumber) { this.bedNumber = bedNumber; }
 
    // Overrides displayDetails() to include ward and bed info
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("   └─ Location: Ward " + wardNumber + ", Bed " + bedNumber);
    }
}
 
static class Bed {
    private String bedId;
    private boolean isOccupied;
    private String patientId;
 
    public Bed(String bedId) {
        this.bedId = bedId;
        this.isOccupied = false;
        this.patientId = null;
    }
 
    public String getBedId() { return bedId; }
    public boolean isOccupied() { return isOccupied; }
    public String getPatientId() { return patientId; }
 
    public void allocateBed(String patientId) {
        this.isOccupied = true;
        this.patientId = patientId;
    }
 
    public void releaseBed() {
        this.isOccupied = false;
        this.patientId = null;
    }
}
 

// WARD SYSTEM CONTROLLER

 
static class WardSystem {
    private final String wardNumber = "W1";
    private List<Patient> patients = new ArrayList<>();
    private Bed[][] beds = new Bed[4][5]; // 4x5 Layout (20 beds total)
 
    public WardSystem() {
        int bedCount = 1;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                // Formats numbers 1-9 to 01-09 (e.g., B01, B02)
                String bedId = String.format("B%02d", bedCount++);
                beds[row][col] = new Bed(bedId);
            }
        }
    }
 
    // --- FEATURE 1: PATIENT MANAGEMENT ---
 
    public void registerPatient(Patient patient) {
        patients.add(patient);
        System.out.println("✓ Patient " + patient.getPatientId() + " successfully registered!");
    }
 
    public Patient searchPatient(String patientId) {
        for (Patient p : patients) {
            if (p.getPatientId().equalsIgnoreCase(patientId)) {
                return p;
            }
        }
        return null;
    }
 
    public boolean updatePatient(String patientId, String newFirstName, String newLastName, int newAge, String newCondition) {
        Patient p = searchPatient(patientId);
        if (p != null) {
            if (!newFirstName.isBlank()) p.setFirstName(newFirstName);
            if (!newLastName.isBlank()) p.setLastName(newLastName);
            if (newAge > 0) p.setAge(newAge);
            if (!newCondition.isBlank()) p.setMedicalCondition(newCondition);
            return true;
        }
        return false;
    }
 
    public boolean deletePatient(String patientId) {
        Patient p = searchPatient(patientId);
        if (p != null) {
            if (p instanceof Inpatient) {
                releaseBedByNumber(((Inpatient) p).getBedNumber(), true);
            }
            patients.remove(p);
            return true;
        }
        return false;
    }
 
    public void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients currently registered.");
            return;
        }
        System.out.println("\n--- REGISTERED PATIENTS (" + patients.size() + ") ---");
        for (Patient p : patients) {
            p.displayDetails();
        }
    }
 
    // --- FEATURE 2: BED MANAGEMENT ---
 
    public void displayWardLayout() {
        System.out.println("\n--- WARD LAYOUT (4x5) ---");
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 5; c++) {
                Bed bed = beds[r][c];
                if (bed.isOccupied()) {
                    System.out.print("[X " + bed.getBedId() + "] ");
                } else {
                    System.out.print("[  " + bed.getBedId() + "] ");
                }
            }
            System.out.println();
        }
        System.out.println("\nNote: [X ...] represents an occupied bed.");
    }
 
    public void displayAvailableBeds() {
        System.out.println("\n--- AVAILABLE BEDS ---");
        boolean found = false;
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 5; c++) {
                if (!beds[r][c].isOccupied()) {
                    System.out.print(beds[r][c].getBedId() + "  ");
                    found = true;
                }
            }
        }
        if (!found) System.out.print("No available beds. Ward is full!");
        System.out.println();
    }
 
    public void displayOccupiedBeds() {
        System.out.println("\n--- OCCUPIED BEDS ---");
        boolean found = false;
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 5; c++) {
                if (beds[r][c].isOccupied()) {
                    System.out.println("Bed " + beds[r][c].getBedId() + " -> Allocated to Patient ID: " + beds[r][c].getPatientId());
                    found = true;
                }
            }
        }
        if (!found) System.out.println("No beds are currently occupied.");
    }
 
    public boolean allocateBedToInpatient(String patientId, String bedId) {
        Patient p = searchPatient(patientId);
 
        if (p == null) {
            System.out.println("Error: Patient ID not found.");
            return false;
        }
        
        if (p instanceof Inpatient && !((Inpatient)p).getBedNumber().equals("N/A")) {
             System.out.println("Error: Patient already has a bed (" + ((Inpatient)p).getBedNumber() + ").");
             return false;
        }
 
        if (getOccupiedBedCount() >= 20) {
            System.out.println("Error: Ward is full! Cannot allocate a bed.");
            return false;
        }
 
        Bed targetBed = findBedById(bedId);
        if (targetBed == null) {
            System.out.println("Error: Invalid Bed ID. Ensure it is between B01 and B20.");
            return false;
        }
 
        if (targetBed.isOccupied()) {
            System.out.println("Error: Bed " + bedId + " is already occupied.");
            return false;
        }
 
        // Process allocation
        targetBed.allocateBed(patientId);
 
        // Convert base Patient to Inpatient (Polymorphism/Inheritance requirement)
        if (!(p instanceof Inpatient)) {
            patients.remove(p);
            Inpatient inpatient = new Inpatient(p.getPatientId(), p.getFirstName(), p.getLastName(), 
                                                p.getAge(), p.getGender(), p.getMedicalCondition(), 
                                                wardNumber, targetBed.getBedId());
            patients.add(inpatient);
        } else {
            ((Inpatient) p).setBedNumber(targetBed.getBedId());
        }
 
        System.out.println(" Bed " + targetBed.getBedId() + " successfully allocated to Patient " + patientId + ".");
        return true;
    }
 
    public boolean releaseBedByNumber(String bedId, boolean silent) {
        Bed targetBed = findBedById(bedId);
        if (targetBed != null && targetBed.isOccupied()) {
            String patientId = targetBed.getPatientId();
            targetBed.releaseBed();
 
            // Clear inpatient bed details 
            Patient p = searchPatient(patientId);
            if (p instanceof Inpatient) {
                ((Inpatient) p).setBedNumber("N/A");
                p.setCategory(PatientCategory.OUTPATIENT); // Discharged from bed, becomes outpatient
            }
            if (!silent) System.out.println("✓ Bed " + bedId + " released successfully. Patient marked as Outpatient.");
            return true;
        }
        if (!silent) System.out.println("Error: Bed " + bedId + " is either invalid or already empty.");
        return false;
    }
 
    public Bed findBedById(String bedId) {
        // Fix formatting (e.g., user types "B1" instead of "B01")
        if (bedId.matches("B[1-9]")) {
            bedId = bedId.replace("B", "B0");
        }
        
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 5; c++) {
                if (beds[r][c].getBedId().equalsIgnoreCase(bedId)) {
                    return beds[r][c];
                }
            }
        }
        return null;
    }
 
    private int getOccupiedBedCount() {
        int count = 0;
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 5; c++) {
                if (beds[r][c].isOccupied()) count++;
            }
        }
        return count;
    }
 
    // --- FEATURE 3: REPORTS ---
 
    public void generateWardReport() {
        int totalPatients = patients.size();
        int occupiedBeds = getOccupiedBedCount();
        int availableBeds = 20 - occupiedBeds;
        double occupancyPercentage = (occupiedBeds / 20.0) * 100.0;
 
        System.out.println("\nMEDICARE WARD SUMMARY REPORT ");
        System.out.println("Total Registered Patients: " + totalPatients);
        System.out.println("Total Occupied Beds:       " + occupiedBeds);
        System.out.println("Total Available Beds:      " + availableBeds);
        System.out.printf( "Ward Occupancy Rate:       %.2f%%\n", occupancyPercentage);
        System.out.println("");
 
        displayAllPatients();
        displayAvailableBeds();
        displayOccupiedBeds();
    }
}
 
    private static Scanner scanner = new Scanner(System.in);
    private static WardSystem wardSystem = new WardSystem();

    public static void main(String[] args) {
     int choice = -1;
        System.out.println("Welcome to the Medicare Hospital Admission System.");
        
        while (choice != 0) {
            printMainMenu();
            choice = readInt("Select an option: ");
            
            switch (choice) {
                case 1 -> registerPatientUI();
                case 2 -> searchPatientUI();
                case 3 -> updatePatientUI();
                case 4 -> deletePatientUI();
                case 5 -> wardSystem.displayAllPatients();
                case 6 -> allocateBedUI();
                case 7 -> releaseBedUI();
                case 8 -> wardSystem.displayWardLayout();
                case 9 -> wardSystem.displayAvailableBeds();
                case 10 -> wardSystem.displayOccupiedBeds();
                case 11 -> wardSystem.generateWardReport();
                case 0 -> System.out.println("Exiting Medicare Hospital System. Goodbye!");
                default -> System.out.println("Invalid option. Please choose a number between 0 and 11.");
            }
            
            if (choice != 0) {
                System.out.print("\nPress Enter to return to the menu...");
                scanner.nextLine();
            }
        }
    }
 
    private static void printMainMenu() {
        System.out.println("\n");
        System.out.println("  MEDICARE HOSPITAL - WARD ADMISSION SYSTEM");
        System.out.println("");
        System.out.println(" 1. Register New Patient");
        System.out.println(" 2. Search Patient by ID");
        System.out.println(" 3. Update Patient Details");
        System.out.println(" 4. Delete Patient");
        System.out.println(" 5. Display All Registered Patients");
        System.out.println("-------------------------------------------");
        System.out.println(" 6. Allocate Bed to Patient (Inpatient)");
        System.out.println(" 7. Release Bed (Discharge)");
        System.out.println(" 8. View Complete Ward Layout");
        System.out.println(" 9. View Available Beds");
        System.out.println("10. View Occupied Beds");
        System.out.println("--------------------------------------------");
        System.out.println("11. Generate Ward Summary Report");
        System.out.println(" 0. Exit System");
        System.out.println("");
    }
 
    // --- HELPER METHODS FOR SAFE INPUT ---
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
 
    private static String readString(String prompt) {
        String input = "";
        while (input.isBlank()) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isBlank()) System.out.println("Input cannot be empty.");
        }
        return input;
    }
 
    // --- UI METHODS ---
    private static void registerPatientUI() {
        System.out.println("\n-- Register Patient --");
        String id = readString("Enter Patient ID (e.g., P01): ");
        
        if (wardSystem.searchPatient(id) != null) {
            System.out.println("Error: Patient ID already exists.");
            return;
        }
 
        String firstName = readString("Enter First Name: ");
        String lastName = readString("Enter Last Name: ");
        int age = readInt("Enter Age: ");
        String gender = readString("Enter Gender (M/F): ");
        String condition = readString("Enter Medical Condition: ");
 
        System.out.println("\nSelect Category:");
        System.out.println("1. Inpatient (Requires Bed Allocation)");
        System.out.println("2. Outpatient");
        System.out.println("3. Emergency");
        int catChoice = readInt("Choice (1-3): ");
 
        PatientCategory category = switch (catChoice) {
            case 1 -> PatientCategory.INPATIENT;
            case 3 -> PatientCategory.EMERGENCY;
            default -> PatientCategory.OUTPATIENT;
        };
 
        if (category == PatientCategory.INPATIENT) {
            wardSystem.displayAvailableBeds();
            String bedId = readString("Assign Bed Number (e.g., B01): ").toUpperCase();
            Bed bedCheck = wardSystem.findBedById(bedId);
            
            if (bedCheck == null || bedCheck.isOccupied()) {
                System.out.println("Error: Bed is invalid or already occupied. Patient registered as Outpatient. Please allocate a valid bed later.");
                wardSystem.registerPatient(new Patient(id, firstName, lastName, age, gender, condition, PatientCategory.OUTPATIENT));
            } else {
                Inpatient inpatient = new Inpatient(id, firstName, lastName, age, gender, condition, "W1", bedCheck.getBedId());
                wardSystem.registerPatient(inpatient);
                bedCheck.allocateBed(id); // Physically allocate it in the matrix
            }
        } else {
            Patient patient = new Patient(id, firstName, lastName, age, gender, condition, category);
            wardSystem.registerPatient(patient);
        }
    }
 
    private static void searchPatientUI() {
        String id = readString("\nEnter Patient ID to Search: ");
        Patient p = wardSystem.searchPatient(id);
        if (p != null) {
            System.out.println("\nPatient Found:");
            p.displayDetails();
        } else {
            System.out.println("Patient not found.");
        }
    }
 
    private static void updatePatientUI() {
        String id = readString("\nEnter Patient ID to Update: ");
        Patient p = wardSystem.searchPatient(id);
        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }
        
        System.out.println("Leave blank and press Enter to keep current value.");
        
        System.out.print("Enter New First Name [" + p.getFirstName() + "]: ");
        String fn = scanner.nextLine().trim();
        
        System.out.print("Enter New Last Name [" + p.getLastName() + "]: ");
        String ln = scanner.nextLine().trim();
        
        System.out.print("Enter New Age [" + p.getAge() + "]: ");
        String ageStr = scanner.nextLine().trim();
        int age = -1;
        if (!ageStr.isBlank()) {
            try {
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid age entered - keeping current age.");
            }
        }
        
        System.out.print("Enter New Medical Condition [" + p.getMedicalCondition() + "]: ");
        String cond = scanner.nextLine().trim();
 
        if (wardSystem.updatePatient(id, fn, ln, age, cond)) {
            System.out.println("✓ Patient updated successfully.");
        }
    }
 
    private static void deletePatientUI() {
        String id = readString("\nEnter Patient ID to Delete: ");
        if (wardSystem.deletePatient(id)) {
            System.out.println("✓ Patient deleted successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }
 
    private static void allocateBedUI() {
        String id = readString("\nEnter Patient ID to allocate bed: ");
        wardSystem.displayAvailableBeds();
        String bedId = readString("Enter Bed ID to allocate (e.g., B01): ").toUpperCase();
 
        wardSystem.allocateBedToInpatient(id, bedId);
    }
 
    private static void releaseBedUI() {
        String bedId = readString("\nEnter Bed ID to Release (e.g., B01): ").toUpperCase();
        wardSystem.releaseBedByNumber(bedId, false);
    }
}