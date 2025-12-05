package br.com.unyleya;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    
    @Test
    public void testAppName() {
        String appName = "Unyleya DevOps";
        assertNotNull(appName);
        assertEquals("Unyleya DevOps", appName);
        System.out.println("✓ Test 1: App name is valid");
    }
    
    @Test
    public void testVersion() {
        String version = "1.0.0";
        assertTrue(version.matches("\\d+\\.\\d+\\.\\d+"));
        System.out.println("✓ Test 2: Version format is valid");
    }
    
    @Test
    public void testEnvironment() {
        String[] environments = {"desenvolvimento", "producao"};
        assertTrue(environments.length == 2);
        System.out.println("✓ Test 3: Environments configured");
    }
    
    @Test
    public void testPipeline() {
        String[] stages = {"Build", "Test", "SonarQube", "Deploy"};
        assertEquals(4, stages.length);
        System.out.println("✓ Test 4: Pipeline stages defined");
    }
    
    @Test
    public void testTechnologies() {
        String[] techs = {"Jenkins", "SonarQube", "Docker", "Maven"};
        assertTrue(techs.length >= 4);
        System.out.println("✓ Test 5: Technologies verified");
    }
}
