/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.wardmanagementsystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lusan
 */
public class WardManagementSystemTest {
    
    public WardManagementSystemTest() {
    }
    
    

    /**
     * Test of main method, of class WardManagementSystem.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        WardManagementSystem.main(args);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
 @Test
    public void testBedAllocation() {

        WardManagementSystem.Bed bed =
                new WardManagementSystem.Bed("B01");

        assertFalse(bed.isOccupied());

        bed.allocateBed("P01");

        assertTrue(bed.isOccupied());
        assertEquals("P01", bed.getPatientId());
        
    }
    @Test
public void testBedRelease() {

    WardManagementSystem.Bed bed =
            new WardManagementSystem.Bed("B01");

    bed.allocateBed("P01");

    assertTrue(bed.isOccupied());
    assertEquals("P01", bed.getPatientId());

    bed.releaseBed();

    assertFalse(bed.isOccupied());
    assertNull(bed.getPatientId());
}
@Test
public void testPatientRegistrationAndSearch() {

    WardManagementSystem.WardSystem ward =
            new WardManagementSystem.WardSystem();

    WardManagementSystem.Patient patient =
            new WardManagementSystem.Patient(
                    "P01",
                    "John",
                    "Smith",
                    25,
                    "M",
                    "Flu",
                    WardManagementSystem.PatientCategory.OUTPATIENT
            );

    ward.registerPatient(patient);

    WardManagementSystem.Patient result =
            ward.searchPatient("P01");

    assertNotNull(result);
    assertEquals("P01", result.getPatientId());
    assertEquals("John", result.getFirstName());
    assertEquals("Smith", result.getLastName());
}
  @Test
public void testSearchPatientNotFound() {

    WardManagementSystem.WardSystem ward =
            new WardManagementSystem.WardSystem();

    WardManagementSystem.Patient result =
            ward.searchPatient("P99");

    assertNull(result);
}
    
}

