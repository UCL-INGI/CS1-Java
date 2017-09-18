/**
 *  Copyright (c)  2017 Olivier Martin
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
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.Test;
import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.Random;
import java.util.Arrays;

import static student.Translations.Translator._;

import StudentCode.*;

public class Tests {
    
    private String preQ1 = "@1 :\n";
    private String preQ2 = "@2 :\n";
    
    //In case of exception, we use the content of these variables. Do not forget to use them !!!
    private static OrderedList currentListe;
    private String currentPre = preQ1;
    
    ///////////
    // AJOUTE()
    ///////////
    @Test
    public void test_ajoute_vide(){
        currentPre = preQ1;
        catcher(new t11());
    }
    private class t11 implements Callable<Void> {
        public Void call() {
            OrderedList q = new OrderedList();
            currentListe = q;
            q.ajoute(88);
            assertTrue(currentPre + MessageFormat.format(_("Quand la liste est vide, et qu''on ajoute un noeud à votre liste, ''{0}'' vaut null alors qu''il devrait référer un nouveau noeud.\n"), "min"), q.min != null);
            assertTrue(currentPre + MessageFormat.format(_("Quand la liste est vide, et qu''on ajoute un noeud à votre liste, ''{0}'' vaut null alors qu''il devrait référer un nouveau noeud.\n"), "max"), q.max != null);
            assertTrue(currentPre + MessageFormat.format(_("Quand la liste est vide, et qu''on ajoute un noeud à votre liste, ''{0}'' et la ''{1}'' ne réfèrent pas le même noeud.\n"), "min", "max"), q.min == q.max);
            assertEquals(currentPre + _("Quand la liste est vide, et qu'on ajoute un noeud à votre liste, ce noeud ne contient pas le bon élément.\n"), 88, q.max.d, 0.001);
            return null;
        }
    }
    
    @Test
    public void test_ajoute(){
        currentPre = preQ1;
        catcher(new t10());
    }
    private class t10 implements Callable<Void> {
        public Void call() {
            OrderedList qCorrecte = new OrderedList();
            OrderedList q = new OrderedList();
            currentListe = q;
            double[] to_add = new double[] {1,5,3,7,2,9,10};
            for(int i = 0; i < to_add.length; i++){
                String before = qCorrecte.toString();
                q.ajoute(to_add[i]);
                qCorrecte.ajouteCorrect(to_add[i]);
                assertTrue(MessageFormat.format(currentPre + _("Vous n''avez pas bien mis à jour la liste en ajoutant un noeud dans la structure. La liste avant l''opération ajoute() est :\n{0}\nLa liste attendue est :\n{1}\nVotre liste est :\n{2}"), before, qCorrecte.toString(), q.toString()), qCorrecte.toString().equals(q.toString()));
                }
            return null;
        }
    }
    
    
    ///////////
    // RETIRE()
    ///////////
    //We use retire() and ajouteCorrecte()
    @Test
    public void test_retire_vide(){
        currentPre = preQ2;
        catcher(new t1());
    }
    private class t1 implements Callable<Void> {
        public Void call() {
            OrderedList q = new OrderedList();
            currentListe = q;
            try {
                q.retireMax();
            }catch(IllegalStateException e){
                //correct
                return null;
            }
            fail(currentPre + _("Vous ne lancez pas une IllegalStateException quand on essaye de retirer un élément alors que la liste est vide.\n"));
            return null;
        }
    }
    
    @Test
    public void test_retire_one_element(){
        currentPre = preQ2;
        catcher(new t2());
    }
    private class t2 implements Callable<Void> {
        public Void call() {
            OrderedList q = new OrderedList();
            currentListe = q;
            q.ajouteCorrect(555513);
            double retire_student = q.retireMax();
            assertTrue(currentPre + "Quand la liste ne contient qu'un élément, vous vous ne le retournez pas correctment.\n", 555513 == retire_student);
            assertTrue(MessageFormat.format(currentPre + _("Vous n''avez pas mis la variable ''{0}'' à null quand la liste contient un seul élément et qu''on le retire.\n"), "min"), q.min == null);
            assertTrue(MessageFormat.format(currentPre + _("Vous n''avez pas mis la variable ''{0}'' à null quand la liste contient un seul élément et qu''on le retire.\n"), "max"), q.max == null);
            return null;
        }
    }
    
    @Test
    public void test_retire_full(){
        try{
            test_retire_vide();
            test_retire_one_element();
        }catch(Throwable t){
            return;
        }
        currentPre = preQ2;
        catcher(new t4());
    }
    private class t4 implements Callable<Void> {
        public Void call() {
            OrderedList qCorrecte = new OrderedList();
            OrderedList q = new OrderedList();
            currentListe = q;
            
            for(int j = 0; j < 2; j++){
                q.ajouteCorrect(2);
                q.ajouteCorrect(4);
                q.ajouteCorrect(6);
                q.ajouteCorrect(8);
                
                qCorrecte.ajouteCorrect(2);
                qCorrecte.ajouteCorrect(4);
                qCorrecte.ajouteCorrect(6);
                qCorrecte.ajouteCorrect(8);
                
                for(int i = 0; i < 4; i++){
                    String beforeRetire = qCorrecte.toString();
                    double stu = q.retireMax();
                    double exp = qCorrecte.retireMaxCorrect();
                    assertTrue(MessageFormat.format(currentPre + _("Vous n''avez pas bien mis à jour la liste en retirant un noeud de la structure. La liste avant l''opération retire() est :\n{0}\nLa liste attendue est :\n{1}\nVotre liste est :\n{2}"), beforeRetire, qCorrecte.toString(), q.toString()), qCorrecte.toString().equals(q.toString()));
                    assertTrue(MessageFormat.format(currentPre + _("Vous avez retourné {0} alors que la réponse attendue est {1}. Le queue est :\n{2}"), stu, exp, q.toString()), stu == exp);
                }
            }
            return null;
        }
    }

    public void catcher(Callable<Void> test) {
        String msg = "\n" + _("Cette erreur est survenue quand la liste est :\n");
        try{
            test.call();
        }catch (ArithmeticException e){
            fail(currentPre + _("Attention, il est interdit de diviser par zéro.") + msg + currentListe.toString());
        }catch(ClassCastException e){
            fail(currentPre + _("Attention, certaines variables ont été mal castées !") + msg + currentListe.toString());
        }catch(StringIndexOutOfBoundsException e){
            fail(currentPre + _("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)") + msg + currentListe.toString());
        }catch(ArrayIndexOutOfBoundsException e){
            fail(currentPre + _("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)") + msg + currentListe.toString());
        }catch(NullPointerException e){
            fail(currentPre + _("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.") + msg + currentListe.toString());
        }catch(NegativeArraySizeException e){
            fail(currentPre + _("Vous initialisez un tableau avec une taille négative.") + msg + currentListe.toString());
        }catch(StackOverflowError e){
            fail(currentPre + _("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même.") + msg + currentListe.toString());
        }catch(Exception e){
            fail(currentPre + _("Une erreur inattendue est survenue dans votre tâche : ") + e.toString() + msg + currentListe.toString());
        }
    }
}
