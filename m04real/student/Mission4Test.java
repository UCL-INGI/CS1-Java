package student;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import java.text.MessageFormat;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Mission4Test
{
	public String errorQ1Message = "Question 1 : ";
    private String feedback;
    private Class<?> bioInfo;
    
    @Before
	public void before() throws ClassNotFoundException
	{
		bioInfo = Class.forName("BioInfo");
    }
    
    public void testIsADN(){
        Method isADNStud = null;

        try {
        	isADNStud = bioInfo.getDeclaredMethod("isADN", String.class);  
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode isADN.");
        }        
        
        String[] tests = {"", "A", "Z", "AT", "AZ", "ATCG", "ATZG"};
        boolean[] answers = {false, true, false, true, false, true, false};

        for(int i = 0; i < tests.length; i++){
            boolean expected = answers[i];
            boolean studentAnswer = false;
            
            try{
            	Object returStudentAnswer = isADNStud.invoke(null, tests[i]);
                studentAnswer = (Boolean) returStudentAnswer;
                
            } catch(Exception e){
                fail(errorQ1Message + "\n" + e.toString());
            }
            feedback = errorQ1Message + "Avec {0}, isADN devait renvoyer {1} or vous avez renvoyé {2}.";
            assertTrue(MessageFormat.format(feedback, tests[i], expected, studentAnswer), expected == studentAnswer);
        }
    }
    
    public void testTestIsADN(){
        Method testIsADNStud = null;
        
        try {
        	testIsADNStud = bioInfo.getDeclaredMethod("testIsADN");
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode testIsADN.");
        }        
        
        try{
            testIsADNStud.invoke(null);
        } catch(AssertionError e){
            fail(errorQ1Message + "Les tests dans votre testIsADN ne passent pas");
        } catch(Exception e){
            fail(errorQ1Message + "\n" + e.getMessage());
        }
    }
    
    public void testCount(){
        Method countStud = null;
        
        try {
        	countStud = bioInfo.getDeclaredMethod("count", String.class, char.class);
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode count.");
        }        
        
        String[] tests = {"", "A", "Z", "AT", "AZ", "ATCG", "TACG", "TAGACGTA"};
        int[] answers = {0, 1, 0, 1, 1, 1, 1, 3};
        
        for(int i = 0; i < tests.length; i++){
            int expected = answers[i];
            int studentAnswer = 0;
            
            try{
            	studentAnswer = (int) countStud.invoke(null, tests[i], 'A');
            } catch(Exception e){
                fail(errorQ1Message + "\n" + e.getMessage());
            }

            feedback = errorQ1Message + "Le count devait renvoyer {0} or vous avez renvoyé {1}.";
            assertTrue(MessageFormat.format(feedback, expected, studentAnswer), expected == studentAnswer);
        }
    }
    
    public void testTestCount(){
        Method testCountStud = null;
        
        try {
        	testCountStud = bioInfo.getDeclaredMethod("testCount");
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode testCount.");
        }
        
        try{
            testCountStud.invoke(null);
        } catch(AssertionError e){
            fail(errorQ1Message + "Les tests dans votre testCount ne passent pas");
        }  catch(Exception e){
                fail(errorQ1Message + "\n" + e.getMessage());
        }
    }
    
    public void testDistanceH(){
        Method distanceHStud = null;
        
        try {
        	distanceHStud = bioInfo.getDeclaredMethod("distanceH", String.class, String.class);
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode distanceH.");
        }        
        
        String[] testsString1 = {"A", "AG", "AG", "ATGAC", "ATGAC", "ATGAC"};
        String[] testsString2 = {"A", "GG", "AT", "ATGAC", "AGGAG", "TGACG"};
        int[] answers = {0, 1, 1, 0, 2, 5};
        
        for(int i = 0; i < testsString1.length; i++){
            int expected = answers[i];
            int studentAnswer = 0;
            
            try{
            	studentAnswer = (int) distanceHStud.invoke(null, testsString1[i], testsString2[i]);
            } catch(Exception e){
                fail(errorQ1Message + "\n" + e.getMessage());
            }
            
            feedback = errorQ1Message + "distanceH devait renvoyer {0} or vous avez renvoyé {1}.";
            assertTrue(MessageFormat.format(feedback, expected, studentAnswer), expected == studentAnswer);
        }
    }
    
    public void testTestDistanceH(){
        Method testDistanceHStud = null;
        
        try {
        	testDistanceHStud = bioInfo.getDeclaredMethod("testDistanceH");
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode testDistanceH.");
        }        
        
        try{
            testDistanceHStud.invoke(null);
        } catch(AssertionError e){
            fail(errorQ1Message + "Les tests dans votre testDistanceH ne passent pas");
        }  catch(Exception e){
                fail(errorQ1Message + "\n" + e.getMessage());
        }
    }
    
    public void testPlusLongPalindrome(){
        Method plusLongPalindromeStud = null;
        
        try {
        	plusLongPalindromeStud = bioInfo.getDeclaredMethod("plusLongPalindrome", String.class);
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode plusLongPalindrome.");
        }        
    
        String[] testsString = {"A", "AGATCG", "TAAGAGTA", "ACCTGTTAGGATTC", "ATAGACAATTAAGG"};
        String[] answers = {"A", "AGA", "AGA", "TTAGGATT", "AATTAA"};
        
        for(int i = 0; i < testsString.length; i++){
            String expected = answers[i];
            String studentAnswer = "";
            
            try{
            	studentAnswer = (String) plusLongPalindromeStud.invoke(null, testsString[i]);
            } catch(Exception e){
                fail(errorQ1Message + "\n" + e.getMessage());
            }

            feedback = errorQ1Message + "plusLongPalindromeStud devait renvoyer {0} or vous avez renvoyé {1}.";
            assertTrue(MessageFormat.format(feedback, expected, studentAnswer), expected.equals(studentAnswer));
        }
    }
    
    public void testTestPlusLongPalindrome(){
        Method testPlusLongPalindromeStud = null;
        
        try {
        	testPlusLongPalindromeStud = bioInfo.getDeclaredMethod("testPlusLongPalindrome");
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode testPlusLongPalindrome.");
        }        
        
        try{
            testPlusLongPalindromeStud.invoke(null);
        } catch(AssertionError e){
            fail(errorQ1Message + "Les tests dans votre testPlusLongPalindrome ne passent pas");
        } catch(Exception e){
                fail(errorQ1Message + "\n" + e.toString());
        }
    }
    
    @Test
    public void testLauncher(){
        try{
            testIsADN();
            testTestIsADN();
            testCount();
            testTestCount();
            testDistanceH();
            testTestDistanceH();
            testPlusLongPalindrome();
            testTestPlusLongPalindrome();
        }catch (ArithmeticException e){
            fail("Attention, il est interdit de diviser par zéro.");
        }catch(ClassCastException e){
            fail("Attention, certaines variables ont été mal castées !");
        }catch(StringIndexOutOfBoundsException e){
            fail("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException) " + e.toString());
        }catch(ArrayIndexOutOfBoundsException e){
            fail("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)");
        }catch(NullPointerException e){
            fail("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
        }catch(Exception e){
            fail("Une erreur inattendue est survenue dans votre tâche : " + e.toString());
        }
    }
}
