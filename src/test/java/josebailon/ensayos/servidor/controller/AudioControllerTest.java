/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
Lista de paquetes:
 */
package josebailon.ensayos.servidor.controller;

import josebailon.ensayos.servidor.model.entity.Audio;
import josebailon.ensayos.servidor.security.UserPrincipal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
public class AudioControllerTest {
    
    public AudioControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of create method, of class AudioController.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Audio request = null;
        MultipartFile archivo = null;
        UserPrincipal principal = null;
        AudioController instance = null;
        Audio expResult = null;
        Audio result = instance.create(request, archivo, principal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class AudioController.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        Audio request = null;
        MultipartFile archivo = null;
        UserPrincipal principal = null;
        AudioController instance = null;
        Audio expResult = null;
        Audio result = instance.edit(request, archivo, principal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
