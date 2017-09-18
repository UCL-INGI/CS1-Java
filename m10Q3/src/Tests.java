
/**
 *  Copyright (c) 2017 Brandon Naitali
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
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.Random;

import java.text.MessageFormat;
import static student.Translations.Translator._;
import StudentCode.*;

import src.librairies.*;

public class Tests{
    @Rule public TestName name = new TestName();
    public boolean doubleArrayEquality(double [] a, double [] b){
        if(a == null || b == null) return false;
        if(a.length!=b.length) return false;
        for(int i = 0; i < a.length; i++){
            if(a[i]!=b[i]) return false;
        }
        return true;
    }
    public String generateFakeRandomStudent(){
        String toReturn = "";
        String nom = StringHelper.generateNumberOrLetter(7, false);
        toReturn += nom;
        Random r = new Random();
        int nbrCours = r.nextInt(6) + 1;
        toReturn += ": ";
        for(int i = 0; i < nbrCours-1; i++){
            toReturn += StringHelper.generateDoubleString() + ", " ;
        }
        toReturn += StringHelper.generateDoubleString();
        return toReturn;
    }
    public String generateRandomStudentWithFault(boolean neg){
        String toReturn = "";
        String nom = StringHelper.generateNumberOrLetter(7, false);
        toReturn += nom;
        Random r = new Random();
        int nbrCours = r.nextInt(6) + 1;
        toReturn += "; ";
        for(int i = 0; i < nbrCours-1; i++){
            toReturn += StringHelper.generateDoubleString() + ", " ;
        }
        toReturn = neg? toReturn + "-" + StringHelper.generateDoubleString(): toReturn + (r.nextInt(10) + 21);
        return toReturn;
    }
    public String generateRandomStudent(boolean parseFail){
        String toReturn = "";
        String nom = StringHelper.generateNumberOrLetter(7, false);
        toReturn += nom;
        Random r = new Random();
        int nbrCours = r.nextInt(6) + 1;
        toReturn += "; ";
        for(int i = 0; i < nbrCours-1; i++){
            toReturn += StringHelper.generateDoubleString() + ", " ;
        }
        toReturn = parseFail? toReturn + StringHelper.generateNumberOrLetter(2,false) : toReturn + StringHelper.generateDoubleString();
        return toReturn;
    }
    @Test
    public void test_4(){
        String feedback  = MessageFormat.format(_("{0} : le test de parsing d'un string qui n'est pas un double ne passe pas : "), test_name());
        catcher(new TestException(generateRandomStudent(true), feedback), 1);
    }
    @Test
    public void test_3(){
        String feedback  = MessageFormat.format(_("{0} : le test avec un étudiant possédant une cote supérieure à 20 ne passe pas : "), test_name());
        catcher(new TestException(generateRandomStudentWithFault(false), feedback), 1);
    }
    @Test
    public void test_2(){
        String feedback  = MessageFormat.format(_("{0} : le test avec un étudiant possédant une cote négative ne passe pas : "), test_name());
        catcher(new TestException(generateRandomStudentWithFault(true), feedback), 1);
    }
    private class TestException implements Callable<Void> {
        String a;
        String feedbackAdd;
        String feedbackBad = _("vous ne lancez pas la bonne exception.\n");
        String feedbackBad2 = _("vous ne lancez pas une exception dans les cas particuliers.\n");
        public TestException(String a, String feedback){
            this.a = a;
            this.feedbackAdd = feedback;
        }
        public Void call(){
            try{
                Etudiant s= new Etudiant();
                Etudiant.Student f= s.new Student(a);
                fail(feedbackAdd + feedbackBad2);
            }catch(StudentCode.StudentFormatException e){
                System.err.println(MessageFormat.format(_("{0} : réussi"), test_name()));
            }catch(Exception e){
                fail(feedbackAdd + feedbackBad);
            }
            return null;
        }
    }
    @Test
    public void test_6(){
        String feedbackT  =  MessageFormat.format(_("{0} : les cotes sont comprises entre 0 et 20 !\n"), test_name());
        catcher(new TestParsing(generateRandomStudent(false) + " , 0.0", feedbackT), 1);
    }
    @Test
    public void test_5(){
        String feedbackT  =  MessageFormat.format(_("{0} : les cotes sont comprises entre 0 et 20 !\n"), test_name());
        catcher(new TestParsing(generateRandomStudent(false) + " , 20.0", feedbackT), 1);
    }
    @Test
    public void test_1(){
        String feedbackT  =  MessageFormat.format(_("{0} : le test avec un string correct ne passe pas : "), test_name());
        catcher(new TestParsing(generateRandomStudent(false), feedbackT), 1);
    }
    private class TestParsing implements Callable<Void> {
        String a;
        String feedbackAdd;
        String feedbackBad = _("vous ne respectez pas le format \"nom; cote1, cote2, cote3\" avec les cotes <=20 et >=0.\n");
        String feedbackBad2 = _("vous n'initialisez pas correctement les variables d'instances de la classe Student.\n");
        public TestParsing(String a, String feedback){
            this.a = a;
            this.feedbackAdd = feedback;
        }
        public Void call(){
            try{
                Correction co= new Correction();
                Etudiant s= new Etudiant();
                Correction.Student c = co.new Student(a);
                Etudiant.Student e = s.new Student(a);
                if(!c.nom.equals(e.nom) || !doubleArrayEquality(c.cotes, e.cotes)) fail(feedbackAdd + feedbackBad2);
                System.err.println(MessageFormat.format(_("{0} : réussi"), test_name()));
            }catch(Exception e){
                fail(feedbackAdd + feedbackBad );
            }
            
            return null;
        }
    }
    public void catcher(Callable<Void> test, int n) {
        try{
            test.call();
        }catch (ArithmeticException e){
            fail(_("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(_("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(_("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(_("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(_("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(NegativeArraySizeException e){
            fail(_("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(_("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(_("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }
    private String test_name(){
        String s = name.getMethodName().replaceAll("_", " ");
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
    
}

