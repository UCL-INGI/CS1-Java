package src;

import static org.junit.Assert.*;
import org.junit.Test;

import StudentCode.*;

public class Tests {
    
    @Test
	public void test_minimum() {
        double a=1.0;
        double b=2.0;
        double c=3.0;
        
        assertTrue("Erreur : minimum(1.0,2.0,3.0)!=1.0", Etudiant.minimum(a,b,c) == 1.0);
		assertTrue("Erreur : minimum(1.0,1.0,1.0)!=1.0", Etudiant.minimum(a,a,a) == 1.0);
        assertTrue("Erreur : minimum(3.0,3.0,3.0)!=3.0", Etudiant.minimum(c,c,c) == 3.0);
        assertTrue("Erreur : minimum(3.0,2.0,1.0)!=1.0", Etudiant.minimum(c,b,a) == 1.0);
        assertTrue("Erreur : minimum(3.0,2.0,3.0)!=2.0", Etudiant.minimum(c,b,c) == 2.0);
    }

 	@Test
    public void test_maximum() {

        double a=1.0;
        double b=2.0;
        double c=3.0;
        
		assertTrue("Erreur : maximum(1.0,2.0,3.0)!=3.0", Etudiant.maximum(a,b,c) == 3.0);
		assertTrue("Erreur : maximum(1.0,1.0,1.0)!=1.0", Etudiant.maximum(a,a,a) == 1.0);
        assertTrue("Erreur : maximum(3.0,3.0,3.0)!=3.0", Etudiant.maximum(c,c,c) == 3.0);
        assertTrue("Erreur : maximum(3.0,2.0,1.0)!=3.0", Etudiant.maximum(c,b,a) == 3.0);
        assertTrue("Erreur : maximum(1.0,2.0,1.0)!=2.0", Etudiant.maximum(a,b,a) == 2.0);
    }

    @Test
    public void test_average() {

        double a=1.0;
        double b=2.0;
        double c=3.0;
        
        assertTrue("Erreur : average(1.0,2.0,3.0)!=1.0", Etudiant.average(a,b,c) == 2.0);
		assertTrue("Erreur : average(1.0,1.0,1.0)!=1.0", Etudiant.average(a,a,a) == 1.0);
        assertTrue("Erreur : average(3.0,3.0,3.0)!=3.0", Etudiant.average(c,c,c) == 3.0);
        assertTrue("Erreur : average(3.0,2.0,1.0)!=1.0", Etudiant.average(c,b,a) == 2.0);
        assertTrue("Erreur : average(1.0,2.0,1.0)!=1.3333333333333333", Etudiant.average(a,b,a) == 1.3333333333333333);
    }


    @Test
    public void test_median() {

        double a=1.0;
        double b=2.0;
        double c=3.0;

        assertTrue("Erreur : median(1.0,2.0,3.0)!=2.0", Etudiant.median(a,b,c) == 2.0);
		assertTrue("Erreur : median(1.0,1.0,1.0)!=1.0", Etudiant.median(a,a,a) == 1.0);
        assertTrue("Erreur : median(3.0,3.0,3.0)!=3.0", Etudiant.median(c,c,c) == 3.0);
        assertTrue("Erreur : median(3.0,2.0,1.0)!=2.0", Etudiant.median(c,b,a) == 2.0);
        assertTrue("Erreur : median(1.0,2.0,1.0)!=1.0", Etudiant.median(a,b,a) == 1.0);
    }
    
    @Test
    public void test_sublime() {
        assertTrue("Erreur : sublime(12) => true", Etudiant.sublime(12));
        assertFalse("Erreur : sublime(11) => false", Etudiant.sublime(11));
    }
}