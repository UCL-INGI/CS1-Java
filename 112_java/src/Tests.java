
/**
 *  Copyright (c)  2016 Ludovic Taffin
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package src;

import static org.junit.Assert.*;
import org.junit.runner.Result;
import org.junit.Test;
import java.util.Random;
import org.junit.runner.notification.Failure;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.MessageFormat;

import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import StudentCode.*;
import src.Correction.*;

import java.text.MessageFormat;

public class Tests {
    
    public String errorQ1Message = "Question 1 : ";
    private String feedback;
    
    public void testIsADN(){
        Method isADNStud = null;
        
        try {
        	isADNStud = BioInfo.class.getDeclaredMethod("isADN", String.class);
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode isADN.");
        }        
        
        BioInfo bioInfoStud = new BioInfo();
        Correction correction = new Correction();
        
        String[] tests = {"", "A", "Z", "AT", "AZ", "ATCG", "ATZG"};
        
        for(int i = 0; i < tests.length; i++){
            boolean expected = correction.isADN(tests[i]);
            boolean studentAnswer = bioInfoStud.isADN(tests[i]);    

            feedback = errorQ1Message + "Avec {0}, isADN devait renvoyer {1} or vous avez renvoyé {2}.";
            assertTrue(MessageFormat.format(feedback, tests[i], expected, studentAnswer), expected == studentAnswer);
        }
    }
    
    public void testTestIsADN(){
        Method testIsADNStud = null;
        
        try {
        	testIsADNStud = BioInfo.class.getDeclaredMethod("testIsADN");
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode testIsADN.");
        }        
        
        BioInfo bioInfoStud = new BioInfo();
        
        try{
            bioInfoStud.testIsADN();
        } catch(AssertionError e){
            fail(errorQ1Message + "Les tests dans votre testIsADN ne passent pas");
        }
    }
    
    public void testCount(){
        Method countStud = null;
        
        try {
        	countStud = BioInfo.class.getDeclaredMethod("count", String.class, char.class);
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode count.");
        }        
        
        BioInfo bioInfoStud = new BioInfo();
        Correction correction = new Correction();
        
        String[] tests = {"", "A", "Z", "AT", "AZ", "ATCG", "TACG", "TAGACGTA"};
        
        for(int i = 0; i < tests.length; i++){
            int expected = correction.count(tests[i], 'A');
            int studentAnswer = bioInfoStud.count(tests[i], 'A');    

            feedback = errorQ1Message + "Le count devait renvoyer {0} or vous avez renvoyé {1}.";
            assertTrue(MessageFormat.format(feedback, expected, studentAnswer), expected == studentAnswer);
        }
    }
    
    public void testTestCount(){
        Method testCountStud = null;
        
        try {
        	testCountStud = BioInfo.class.getDeclaredMethod("testCount");
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode testCount.");
        }        
        
        BioInfo bioInfoStud = new BioInfo();
        
        try{
            bioInfoStud.testCount();
        } catch(AssertionError e){
            fail(errorQ1Message + "Les tests dans votre testCount ne passent pas");
        }
    }
    
    public void testDistanceH(){
        Method countStud = null;
        
        try {
        	countStud = BioInfo.class.getDeclaredMethod("distanceH", String.class, String.class);
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode distanceH.");
        }        
        
        BioInfo bioInfoStud = new BioInfo();
        Correction correction = new Correction();
        
        String[] testsString1 = {"A", "AG", "AG", "ATGAC", "ATGAC", "ATGAC"};
        String[] testsString2 = {"A", "GG", "AT", "ATGAC", "AGGAG", "TGACG"};
        
        for(int i = 0; i < testsString1.length; i++){
            int expected = correction.distanceH(testsString1[i], testsString2[i]);
            int studentAnswer = bioInfoStud.distanceH(testsString1[i], testsString2[i]);    

            feedback = errorQ1Message + "distanceH devait renvoyer {0} or vous avez renvoyé {1}.";
            assertTrue(MessageFormat.format(feedback, expected, studentAnswer), expected == studentAnswer);
        }
    }
    
    public void testTestDistanceH(){
        Method testDistanceHStud = null;
        
        try {
        	testDistanceHStud = BioInfo.class.getDeclaredMethod("testDistanceH");
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode testDistanceH.");
        }        
        
        BioInfo bioInfoStud = new BioInfo();
        
        try{
            bioInfoStud.testDistanceH();
        } catch(AssertionError e){
            fail(errorQ1Message + "Les tests dans votre testDistanceH ne passent pas");
        }
    }
    
    public void testPlusLongPalindrome(){
        Method plusLongPalindromeStud = null;
        
        try {
        	plusLongPalindromeStud = BioInfo.class.getDeclaredMethod("plusLongPalindrome", String.class);
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode plusLongPalindrome.");
        }        
        
        BioInfo bioInfoStud = new BioInfo();
        Correction correction = new Correction();
    
        String[] testsString = {"A", "AGATCG", "TAAGAGTA", "ACCTGTTAGGATTC", "ATAGACAATTAAGG"};
        
        for(int i = 0; i < testsString.length; i++){
            String expected = correction.plusLongPalindrome(testsString[i]);
            String studentAnswer = bioInfoStud.plusLongPalindrome(testsString[i]);
            
            System.err.println(expected + " // " + studentAnswer);

            feedback = errorQ1Message + "plusLongPalindromeStud devait renvoyer {0} or vous avez renvoyé {1}.";
            assertTrue(MessageFormat.format(feedback, expected, studentAnswer), expected.equals(studentAnswer));
        }
    }
    
    public void testTestPlusLongPalindrome(){
        Method testPlusLongPalindromeStud = null;
        
        try {
        	testPlusLongPalindromeStud = BioInfo.class.getDeclaredMethod("testPlusLongPalindrome");
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode testPlusLongPalindrome.");
        }        
        
        BioInfo bioInfoStud = new BioInfo();
        
        try{
            bioInfoStud.testPlusLongPalindrome();
        } catch(AssertionError e){
            fail(errorQ1Message + "Les tests dans votre testPlusLongPalindrome ne passent pas");
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
