
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
    public String errorQ2Message = "Question 2 : ";
    private String feedback;
    
	public void testNumberFields(){
        Field[] allFieldsCorr = TempsCorr.class.getDeclaredFields();
        Field[] allFieldsStud = Temps.class.getDeclaredFields();
        
        feedback = errorQ1Message + "Il vous faut {0} attributs or vous en avez {1}.";
        assertTrue(MessageFormat.format(feedback, allFieldsCorr.length, allFieldsStud.length), allFieldsCorr.length == allFieldsStud.length);
        
        allFieldsCorr = ChansonCorr.class.getDeclaredFields();
        allFieldsStud = Chanson.class.getDeclaredFields();
        
        feedback = errorQ2Message + "Il vous faut {0} attributs or vous en avez {1}.";
        assertTrue(MessageFormat.format(feedback, allFieldsCorr.length, allFieldsStud.length), allFieldsCorr.length == allFieldsStud.length);
	}
    
    public void testCorrectFields(){
        Field[] allFieldsCorr = TempsCorr.class.getDeclaredFields();
        Field[] allFieldsStud = Temps.class.getDeclaredFields();
        
        feedback = errorQ1Message + "Vos attributs sont de type {0} or Ils doivent être de type {1}.";

        for(int i = 0; i < allFieldsStud.length; i++){
            assertTrue(
                MessageFormat.format(
                    feedback, allFieldsStud[i].getType().getSimpleName(), allFieldsCorr[i].getType().getSimpleName()
                ),
                allFieldsStud[i].getType().getSimpleName().equals(allFieldsCorr[i].getType().getSimpleName())
            );
            
            assertTrue(errorQ1Message + "les attributs doivent être private",allFieldsStud[i].getModifiers() == allFieldsCorr[i].getModifiers());
        }
        
        allFieldsCorr = ChansonCorr.class.getDeclaredFields();
        allFieldsStud = Chanson.class.getDeclaredFields();
        
        feedback = errorQ2Message + "Un de vos attributs est de type {0} or il doit être de type {1}.";
        String expectedType;
        for(int i = 0; i < allFieldsStud.length; i++){
            
            if(allFieldsCorr[i].getType().getSimpleName().equals("TempsCorr")){
                expectedType = "Temps";
            } else{
                expectedType = allFieldsCorr[i].getType().getSimpleName();
            }
            
            assertTrue(
                MessageFormat.format(
                    feedback, allFieldsStud[i].getType().getSimpleName(), expectedType
                ),
                allFieldsStud[i].getType().getSimpleName().equals(expectedType)
            );
            
            assertTrue(errorQ2Message + "les attributs doivent être private",allFieldsStud[i].getModifiers() == allFieldsCorr[i].getModifiers());
        }
	}
    
    public void testConstructors() {
        
        Constructor[] allConstructorsCorr = TempsCorr.class.getDeclaredConstructors();
        Constructor[] allConstructorsStud = Temps.class.getDeclaredConstructors();
        
        feedback = errorQ1Message + "Il vous faut {0} constructeur or vous en avez {1}.";
        assertTrue(MessageFormat.format(feedback, allConstructorsCorr.length, allConstructorsStud.length), 
                   allConstructorsCorr.length == allConstructorsStud.length);
        
        Constructor constructorStud = null;
        
        try {
        	constructorStud = Temps.class.getDeclaredConstructor(int.class, int.class, int.class);
        } catch (NoSuchMethodException e){
            fail(errorQ1Message + "Il vous faut un constructeur avec 3 paramètres int.");
        }
        
        assertTrue(true);
        
        allConstructorsCorr = ChansonCorr.class.getDeclaredConstructors();
        allConstructorsStud = Chanson.class.getDeclaredConstructors();
        
        feedback = errorQ2Message + "Il vous faut {0} constructeur or vous en avez {1}.";
        assertTrue(MessageFormat.format(feedback, allConstructorsCorr.length, allConstructorsStud.length), 
                   allConstructorsCorr.length == allConstructorsStud.length);
        
        constructorStud = null;
        
        try {
            constructorStud = Chanson.class.getDeclaredConstructor(String.class, String.class, Temps.class);
        } catch (NoSuchMethodException e){
            fail(errorQ2Message + "Il vous faut un constructeur avec 2 paramètres String et 1 paramètre Temps.");
        }
        
        assertTrue(true);
	}
    
    public void testNumberMethods(){
        Method[] allMethodsCorr = TempsCorr.class.getDeclaredMethods();
        Method[] allMethodsStud = Temps.class.getDeclaredMethods();
        
        feedback = errorQ1Message + "Il vous faut {0} méthodes or vous en avez {1}.";
        assertTrue(MessageFormat.format(feedback, allMethodsCorr.length, allMethodsStud.length), allMethodsCorr.length == allMethodsStud.length);
        
        allMethodsCorr = ChansonCorr.class.getDeclaredMethods();
        allMethodsStud = Chanson.class.getDeclaredMethods();
        
        feedback = errorQ2Message + "Il vous faut {0} méthodes or vous en avez {1}.";
        assertTrue(MessageFormat.format(feedback, allMethodsCorr.length, allMethodsStud.length), allMethodsCorr.length == allMethodsStud.length);
	}
   
    // from http://tutorials.jenkov.com/java-reflection/getters-setters.html
    public static boolean isGetter(Method method){
      if(!method.getName().startsWith("get"))      return false;
      if(method.getParameterTypes().length != 0)   return false;  
      if(void.class.equals(method.getReturnType())) return false;
      return true;
    }
    
    public void testGetters() {
        
        Method[] allMethodsCorr = TempsCorr.class.getDeclaredMethods();
        Method[] allMethodsStud = Temps.class.getDeclaredMethods();
        int nbGettersCorr = 0;
        int nbGettersStud = 0;
        
        for(Method method : allMethodsCorr){
            if(isGetter(method)) nbGettersCorr++;
        }
        
        for(Method method : allMethodsStud){
            if(isGetter(method)) nbGettersStud++;
        }
        
        feedback = errorQ1Message + "Il vous faut {0} getters or vous en avez {1}.";
        assertTrue(MessageFormat.format(feedback, nbGettersCorr, nbGettersStud), nbGettersCorr == nbGettersStud);
        
        Method getterStud = null;
        
        for(Method method : allMethodsCorr){
            if(isGetter(method) && !method.getName().equals("getClass")) {
                try {
                    getterStud = Temps.class.getDeclaredMethod(method.getName());
                } catch (NoSuchMethodException e){
                    fail(errorQ1Message + "Il vous faut une méthode " + method.getName() + ".");
                }
            }
        }
       
        assertTrue(true);
        
        allMethodsCorr = ChansonCorr.class.getDeclaredMethods();
        allMethodsStud = Chanson.class.getDeclaredMethods();
        nbGettersCorr = 0;
        nbGettersStud = 0;
        
        for(Method method : allMethodsCorr){
            if(isGetter(method)) nbGettersCorr++;
        }
        
        for(Method method : allMethodsStud){
            if(isGetter(method)) nbGettersStud++;
        }
        
        feedback = errorQ2Message + "Il vous faut {0} getters or vous en avez {1}.";
        assertTrue(MessageFormat.format(feedback, nbGettersCorr, nbGettersStud), nbGettersCorr == nbGettersStud);
        
        getterStud = null;
        
        for(Method method : allMethodsCorr){
            if(isGetter(method) && !method.getName().equals("getClass")) {
                try {
                    getterStud = Chanson.class.getDeclaredMethod(method.getName());
                } catch (NoSuchMethodException e){
                    fail(errorQ2Message + "Il vous faut une méthode " + method.getName() + ".");
                }
            }
        }
        
        assertTrue(true);
	}
    
    // from http://tutorials.jenkov.com/java-reflection/getters-setters.html
    public static boolean isSetter(Method method){
      if(!method.getName().startsWith("set")) return false;
      if(method.getParameterTypes().length != 1) return false;
      return true;
    }
    
    public void testSetters(){
        Method[] allMethodsCorr = TempsCorr.class.getDeclaredMethods();
        Method[] allMethodsStud = Temps.class.getDeclaredMethods();
        int nbSettersCorr = 0;
        int nbSettersStud = 0;
        
        for(Method method : allMethodsCorr){
            if(isSetter(method)) nbSettersCorr++;
        }
        
        for(Method method : allMethodsStud){
            if(isSetter(method)) nbSettersStud++;
        }
        
        feedback = errorQ1Message + "Il vous faut {0} setters or vous en avez {1}.";
        assertTrue(MessageFormat.format(feedback, nbSettersCorr, nbSettersStud), nbSettersCorr == nbSettersStud);
        
        Method setterStud = null;
        
        for(Method method : allMethodsCorr){
            if(isSetter(method)) {
                try {
                    setterStud = Temps.class.getDeclaredMethod(method.getName(), int.class);
                } catch (NoSuchMethodException e){
                    fail(errorQ1Message + "Il vous faut une méthode " + method.getName() + ".");
                }
            }
        }
       
        assertTrue(true);
        
        allMethodsCorr = ChansonCorr.class.getDeclaredMethods();
        allMethodsStud = Chanson.class.getDeclaredMethods();
        nbSettersCorr = 0;
        nbSettersStud = 0;
        
        for(Method method : allMethodsCorr){
            if(isSetter(method)) nbSettersCorr++;
        }
        
        for(Method method : allMethodsStud){
            if(isSetter(method)) nbSettersStud++;
        }
        
        feedback = errorQ2Message + "Il vous faut {0} setters or vous en avez {1}.";
        assertTrue(MessageFormat.format(feedback, nbSettersCorr, nbSettersStud), nbSettersCorr == nbSettersStud);
        
        setterStud = null;
        
        for(Method method : allMethodsCorr){
            if(isSetter(method)) {
                try {
                    setterStud = Chanson.class.getDeclaredMethod(method.getName());
                } catch (NoSuchMethodException e){
                    fail(errorQ2Message + "Il vous faut une méthode " + method.getName() + ".");
                }
            }
        }
        
        assertTrue(true);
    }
    
    public void testToSeconds(){
        Method toSecondsStud = null;
        
        try {
        	toSecondsStud = Temps.class.getDeclaredMethod("toSeconds");
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode toSeconds.");
        }        
        
        for(int i = 0; i < 10; i++){
            
            int h = (int)(Math.random() * 23);
            int m = (int)(Math.random() * 59);
            int s = (int)(Math.random() * 59);
            
            Temps t = new Temps(h,m,s);
            TempsCorr tCorr = new TempsCorr(h,m,s);
            
            String formattedExpected = String.format("%d", tCorr.toSeconds());
            String formattedStudent = String.format("%d", t.toSeconds());

            feedback = errorQ1Message + "Le toSeconds devait renvoyer {0} or vous avez renvoyé {1}.";
            assertTrue(MessageFormat.format(feedback, formattedExpected, formattedStudent), formattedExpected.equals(formattedStudent));
        }
    }
    
    public void testDelta(){
        Method DeltaStud = null;
        
        try {
        	DeltaStud = Temps.class.getDeclaredMethod("delta", Temps.class);
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode delta.");
        }        
        
        for(int i = 0; i < 10; i++){
            
            int h = (int)(Math.random() * 23);
            int m = (int)(Math.random() * 59);
            int s = (int)(Math.random() * 59);
            
            Temps t = new Temps(h,m,s);
            TempsCorr tCorr = new TempsCorr(h,m,s);
            
            int hDelta = (int)(Math.random() * 23);
            int mDelta = (int)(Math.random() * 59);
            int sDelta = (int)(Math.random() * 59);
            
            Temps tDelta = new Temps(hDelta, mDelta, sDelta);
            TempsCorr tDeltaCorr = new TempsCorr(hDelta, mDelta, sDelta);
            
            String formattedStudent = String.format("%d", t.delta(tDelta));
            String formattedExpected = String.format("%d", tCorr.delta(tDeltaCorr));
            
            feedback = errorQ1Message + "Le delta devait renvoyer {0} or vous avez renvoyé {1}.";
            assertTrue(MessageFormat.format(feedback, formattedExpected, formattedStudent), formattedExpected.equals(formattedStudent));
        }
    }
    
    public void testApres(){
        Method apresStud = null;
        
        try {
        	apresStud = Temps.class.getDeclaredMethod("apres", Temps.class);
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode apres.");
        }        
        
        for(int i = 0; i < 10; i++){
            
            int h = (int)(Math.random() * 23);
            int m = (int)(Math.random() * 59);
            int s = (int)(Math.random() * 59);
            
            Temps t = new Temps(h,m,s);
            TempsCorr tCorr = new TempsCorr(h,m,s);
            
            int hApres = (int)(Math.random() * 23);
            int mApres = (int)(Math.random() * 59);
            int sApres = (int)(Math.random() * 59);
                       
            Temps tApres = new Temps(hApres, mApres, sApres);
            TempsCorr tApresCorr = new TempsCorr(hApres, mApres, sApres);
            
            boolean expected = tCorr.apres(tApresCorr);
            boolean studentAnswer = t.apres(tApres);
            
            String tToString = String.format("%02d:%02d:%02d", h, m, s);
            String tApresToString = String.format("%02d:%02d:%02d", hApres, mApres, sApres);

            feedback = errorQ1Message + "Avec {0} et {1} votre méthode apres renvoie {2} or on attendait qu''elle renvoie {3}.";
            assertTrue(MessageFormat.format(feedback, tToString, tApresToString, studentAnswer, expected), studentAnswer == expected);
        }
    }
    
    public void testAjouter(){
        Method ajouterStud = null;
        
        try {
        	ajouterStud = Temps.class.getDeclaredMethod("ajouter", Temps.class);
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode ajouter.");
        }        
        
        for(int i = 0; i < 10; i++){
            
            int h = (int)(Math.random() * 23);
            int m = (int)(Math.random() * 59);
            int s = (int)(Math.random() * 59);
            
            Temps t = new Temps(h,m,s);
            TempsCorr tCorr = new TempsCorr(h,m,s);
            
            int hAjout = (int)(Math.random() * 23);
            int mAjout = (int)(Math.random() * 59);
            int sAjout = (int)(Math.random() * 59);
                       
            Temps tAjout = new Temps(hAjout, mAjout, sAjout);
            TempsCorr tAjoutCorr = new TempsCorr(hAjout, mAjout, sAjout);
            
            tCorr.ajouter(tAjoutCorr);
            t.ajouter(tAjout);

            feedback = errorQ1Message + "L''ajout de {0}h et {1}h doit donner {2}h or vous avez trouvé {3}h.";
            assertTrue(MessageFormat.format(feedback, h, hAjout, tCorr.getH(), t.getH()), tCorr.getH() == t.getH());
            
            feedback = errorQ1Message + "L''ajout de {0}m et {1}m doit donner {2}m or vous avez trouvé {3}m.";
            assertTrue(MessageFormat.format(feedback, m, mAjout, tCorr.getM(), t.getM()), tCorr.getM() == t.getM());
            
            feedback = errorQ1Message + "L''ajout de {0}s et {1}s doit donner {2}s or vous avez trouvé {3}s.";
            assertTrue(MessageFormat.format(feedback, s, sAjout, tCorr.getS(), t.getS()), tCorr.getS() == t.getS());
        }
    }
    
    public void testToString(){
        
        Method toStringStud = null;
        
        try {
        	toStringStud = Temps.class.getDeclaredMethod("toString");
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode toString.");
        }        
        
        for(int i = 0; i < 10; i++){
            
            int h = (int)(Math.random() * 23);
            int m = (int)(Math.random() * 59);
            int s = (int)(Math.random() * 59);
            
            Temps t = new Temps(h,m,s);
            String expectedString = String.format("%02d:%02d:%02d", h, m, s);

            feedback = errorQ1Message + "Le toString devait renvoyer {0} or vous avez renvoyé {1}.";
            assertTrue(MessageFormat.format(feedback, expectedString, t.toString()), t.toString().equals(expectedString));
        }
        
        try {
        	toStringStud = Chanson.class.getDeclaredMethod("toString");
        } catch (NoSuchMethodException e){
        	fail(errorQ1Message + "Il vous faut une méthode toString.");
        }
        
        
        for(int i = 1; i <= 10; i++){
            String t = "Titre" + i;
            String a = "Artiste" + i;;
            
            int h = (int)(Math.random() * 23);
            int m = (int)(Math.random() * 59);
            int s = (int)(Math.random() * 59);
            
            Temps d = new Temps(h,m,s);
            
            Chanson c = new Chanson(t,a,d);
            String expectedString = t + " - " + a + " - " + d.toString();
            
            System.err.println(t.toString());

            feedback = errorQ2Message + "Le toString devait renvoyer {0} or vous avez renvoyé {1}.";
            assertTrue(MessageFormat.format(feedback, expectedString, c.toString()), c.toString().equals(expectedString));
        }
        
        assertTrue(true);
    }
    
    @Test
    public void testLauncher(){
        try{
            testNumberFields();
            testCorrectFields();
            testConstructors();
            testNumberMethods();
            testGetters();
            testSetters();
            testToSeconds();
            testDelta();
            testApres();
            testAjouter();
            testToString();
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
