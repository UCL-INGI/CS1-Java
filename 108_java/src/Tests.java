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

import src.Correction.*;

import java.text.MessageFormat;

public class Tests {
    
    public String errorQ1Message = "Question 1 : ";
    public String errorQ2Message = "Question 2 : ";
    private String feedback;
    
    public void testCorrectFields(){
        Field[] allFieldsCorr = Correction.Word.class.getDeclaredFields();
        Field[] allFieldsStud = StudentCode.Word.class.getDeclaredFields();
        
        feedback = errorQ1Message + "Il vous faut {0} attributs or vous en avez {1}.";
        assertTrue(MessageFormat.format(feedback, allFieldsCorr.length, allFieldsStud.length), allFieldsCorr.length == allFieldsStud.length);
        
        feedback = errorQ1Message + "Un de vos attributs est de type {0} or il doit être de type {1}.";
        for(int i = 0; i < allFieldsStud.length; i++){
            assertTrue(
                MessageFormat.format(
                    feedback, allFieldsStud[i].getType().getSimpleName(), allFieldsCorr[i].getType().getSimpleName()
                ),
                allFieldsStud[i].getType().getSimpleName().equals(allFieldsCorr[i].getType().getSimpleName())
            );
            
            assertTrue(errorQ1Message + "les attributs doivent être private",allFieldsStud[i].getModifiers() == allFieldsCorr[i].getModifiers());
        }
        
        allFieldsCorr = Correction.Dictionary.class.getDeclaredFields();
        allFieldsStud = StudentCode.Dictionary.class.getDeclaredFields();
        
        feedback = errorQ2Message + "Il vous faut {0} attributs or vous en avez {1}.";
        assertTrue(MessageFormat.format(feedback, allFieldsCorr.length, allFieldsStud.length), allFieldsCorr.length == allFieldsStud.length);
        
        feedback = errorQ2Message + "Un de vos attributs est de type {0} or il doit être de type {1}.";
        for(int i = 0; i < allFieldsStud.length; i++){
            assertTrue(
                MessageFormat.format(
                    feedback, allFieldsStud[i].getType().getSimpleName(), allFieldsCorr[i].getType().getSimpleName()
                ),
                allFieldsStud[i].getType().getSimpleName().equals(allFieldsCorr[i].getType().getSimpleName())
            );
            
            assertTrue(errorQ2Message + "les attributs doivent être private",allFieldsStud[i].getModifiers() == allFieldsCorr[i].getModifiers());
        }
	}
    
    public void testConstructors() {  
        Constructor[] allConstructorsCorr = Correction.Word.class.getDeclaredConstructors();
        Constructor[] allConstructorsStud = StudentCode.Word.class.getDeclaredConstructors();
        
        feedback = errorQ1Message + "Il vous faut {0} constructeur or vous en avez {1}.";
        assertTrue(MessageFormat.format(feedback, allConstructorsCorr.length, allConstructorsStud.length), 
                   allConstructorsCorr.length == allConstructorsStud.length);
        
        Constructor constructorStud = null;
        
        try {
        	constructorStud = StudentCode.Word.class.getDeclaredConstructor(String.class, int.class);
        } catch (NoSuchMethodException e) {
            fail(errorQ1Message + "Il vous faut un constructeur avec 1 paramètre String et 1 int.");
        }
        
        allConstructorsCorr = Correction.Dictionary.class.getDeclaredConstructors();
        allConstructorsStud = StudentCode.Dictionary.class.getDeclaredConstructors();
        
        feedback = errorQ1Message + "Il vous faut {0} constructeur or vous en avez {1}.";
        assertTrue(MessageFormat.format(feedback, allConstructorsCorr.length, allConstructorsStud.length), 
                   allConstructorsCorr.length == allConstructorsStud.length);
        
        constructorStud = null;
        
        try {
        	constructorStud = StudentCode.Dictionary.class.getDeclaredConstructor(String.class);
        } catch (NoSuchMethodException e) {
            fail(errorQ1Message + "Il vous faut un constructeur avec 1 paramètre String.");
        }
	}
    
    public void testCompareTo() {  
        
        Method compareToStud = null;
        
        try {
        	compareToStud = StudentCode.Word.class.getMethod("compareTo", Object.class);
        } catch (NoSuchMethodException e) {
            fail(errorQ1Message + "Il vous faut une methode compareTo.");
        }
        
        String[] words = {"pomme", "poire", "orange", "ananas", "chien", "chat"};
        
        for (int i = 1; i < words.length; i++){
            StudentCode.Word wordStud = new StudentCode.Word(words[i], 1);
            Word word = new Word(words[i], 1);
            
            StudentCode.Word wordStud2 = new StudentCode.Word(words[i - 1], 1);
			Word word2 = new Word(words[i - 1], 1);
            
            int studentAnswer = wordStud.compareTo(wordStud2);
            int correctionAnswer = word.compareTo(word2);
            
            feedback = errorQ1Message + "Avec {0} et {1}, votre compareTo a renvoyé {2} or on attendait {3}.";
            assertTrue(MessageFormat.format(feedback, words[i], words[i - 1], studentAnswer, correctionAnswer), studentAnswer == correctionAnswer);
        }
	}
    
    public void testEquals() {  
        
        Method equalsStud = null;
        
        try {
        	equalsStud = StudentCode.Word.class.getMethod("equals", Object.class);
        } catch (NoSuchMethodException e) {
            fail(errorQ1Message + "Il vous faut une methode equals.");
        }
        
        
        StudentCode.Word wordStud = new StudentCode.Word("pomme", 1);
        Word word = new Word("pomme", 1);
            
        StudentCode.Word wordStud2 = new StudentCode.Word("pomme", 1);
		Word word2 = new Word("pomme", 1);
        
        StudentCode.Word wordStud3 = new StudentCode.Word("poire", 1);
		Word word3 = new Word("poire", 1);
            
        
        boolean studentAnswer = wordStud.equals(wordStud2);
        boolean correctionAnswer = word.equals(word2);
            
        feedback = errorQ1Message + "Avec {0} et {1}, votre equals a renvoyé {2} or on attendait {3}.";
        assertTrue(MessageFormat.format(feedback, "pomme", "pomme", studentAnswer, correctionAnswer), studentAnswer == correctionAnswer);
        
        studentAnswer = wordStud.equals(wordStud3);
        correctionAnswer = word.equals(word3);
            
        feedback = errorQ1Message + "Avec {0} et {1}, votre equals a renvoyé {2} or on attendait {3}.";
        assertTrue(MessageFormat.format(feedback, "pomme", "poire", studentAnswer, correctionAnswer), studentAnswer == correctionAnswer);
        
        studentAnswer = wordStud3.equals(wordStud3);
        correctionAnswer = word3.equals(word3);
            
        feedback = errorQ1Message + "Avec {0} et {1}, votre equals a renvoyé {2} or on attendait {3}.";
        assertTrue(MessageFormat.format(feedback, "poire", "poire", studentAnswer, correctionAnswer), studentAnswer == correctionAnswer);
	}
    
    public void testIsInDico() {  
        
        Method isInDicoStud = null;
        
        try {
        	isInDicoStud = StudentCode.Dictionary.class.getMethod("isInDico", StudentCode.Word.class);
        } catch (NoSuchMethodException e) {
            fail(errorQ2Message + "Il vous faut une methode isInDico.");
        }
        
        
        
        String[] words = {"pomme", "poire", "orange", "ananas", "chien", "chat"};

        	StudentCode.Dictionary dico = new StudentCode.Dictionary("./words.txt");

            StudentCode.Word wordStud = new StudentCode.Word(words[0], 1);
        
            
            boolean studentAnswer = dico.isInDico(wordStud);
            
            feedback = errorQ1Message + "Votre isInDico avec {0} a renvoyé {1} or on attendait {2}";
            assertTrue(MessageFormat.format(feedback, "lapin", studentAnswer, !studentAnswer), studentAnswer == true);
        
        	StudentCode.Word wordStud2 = new StudentCode.Word("lapin", 0);
            
            studentAnswer = dico.isInDico(wordStud2);
            
            feedback = errorQ1Message + "Votre isInDico avec {0} a renvoyé {1} or on attendait {2}";
            assertTrue(MessageFormat.format(feedback, "lapin", studentAnswer, !studentAnswer), studentAnswer == false);
        
        	StudentCode.Word wordStud3 = new StudentCode.Word("vélo", 0);
            
            studentAnswer = dico.isInDico(wordStud3);
            
            feedback = errorQ1Message + "Votre isInDico avec {0} a renvoyé {1} or on attendait {2}";
            assertTrue(MessageFormat.format(feedback, "vélo", studentAnswer, !studentAnswer), studentAnswer == false);
        }
    
    public void testMotLePlusProche() {  
        
        Method motLePlusProche = null;
        
        try {
        	motLePlusProche = StudentCode.Dictionary.class.getMethod("motLePlusProche", StudentCode.Word.class);
        } catch (NoSuchMethodException e) {
            fail(errorQ2Message + "Il vous faut une methode motLePlusProche.");
        }

        	StudentCode.Dictionary dico = new StudentCode.Dictionary("./words.txt");
            Correction.Dictionary dicoCorr = new Correction.Dictionary("./words.txt");
        
        	String[] words = {"pomme", "poire", "orange", "ananas", "chien", "chat"};
        
            StudentCode.Word wordStud = new StudentCode.Word(words[0], 1);
            Correction.Word wordCorr = new Correction.Word(words[0], 1);
            
            String studentAnswer = dico.motLePlusProche(wordStud);
        	String correctionAnswer = dicoCorr.motLePlusProche(wordCorr);
            
            feedback = errorQ1Message + "Votre motLePlusProche avec {0} a renvoyé {1} or on attendait {2}";
            assertTrue(MessageFormat.format(feedback, words[0], studentAnswer, correctionAnswer), studentAnswer.equals(correctionAnswer));
        
        	wordStud = new StudentCode.Word("lapin", 1);
            wordCorr = new Correction.Word("lapin", 1);
            
            studentAnswer = dico.motLePlusProche(wordStud);
        	correctionAnswer = dicoCorr.motLePlusProche(wordCorr);
            
            feedback = errorQ1Message + "Votre motLePlusProche avec {0} a renvoyé {1} or on attendait {2}";
            assertTrue(MessageFormat.format(feedback, "lapin", studentAnswer, correctionAnswer), studentAnswer.equals(correctionAnswer));
        }
    
    @Test
    public void testLauncher(){
        try{
            testCorrectFields();
            testConstructors();
            testCompareTo();
            testEquals();
            testIsInDico();
            testMotLePlusProche();
        }catch (ArithmeticException e){
            fail("Attention, il est interdit de diviser par zéro.");
        }catch(ClassCastException e){
            fail("Attention, certaines variables ont été mal castées !");
        }catch(StringIndexOutOfBoundsException e){
            fail("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
        }catch(ArrayIndexOutOfBoundsException e){
            fail("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)");
        }catch(NullPointerException e){
            fail("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
        }catch(Exception e){
            fail("Une erreur inattendue est survenue dans votre tâche : " + e.toString());
        }
    }
}