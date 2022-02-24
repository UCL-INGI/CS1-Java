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

import student.Translations.Translator;

import StudentCode.*;

public class Tests {
    
    private String preQ1 = "@1 :\n";
    private String preQ2 = "@2 :\n";
    
    //In case of exception, we use the content of these variables. Do not forget to use them !!!
    private static Queue currentQueue;
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
            Queue q = new Queue();
            currentQueue = q;
            q.ajoute(88);
            assertTrue(currentPre + MessageFormat.format(Translator.translate("Quand la queue est vide, et qu''on ajoute un noeud à votre queue, ''{0}'' vaut null alors qu''il devrait référer un nouveau noeud.\n"), "entree"), q.entree != null);
            assertTrue(currentPre + MessageFormat.format(Translator.translate("Quand la queue est vide, et qu''on ajoute un noeud à votre queue, ''{0}'' vaut null alors qu''il devrait référer un nouveau noeud.\n"), "sortie"), q.sortie != null);
            assertTrue(currentPre + MessageFormat.format(Translator.translate("Quand la queue est vide, et qu''on ajoute un noeud à votre queue, ''{0}'' et la ''{1}'' ne réfèrent pas le même noeud.\n"), "entree", "sortie"), q.entree == q.sortie);
            assertEquals(currentPre + Translator.translate("Quand la queue est vide, et qu'on ajoute un noeud à votre queue, ce noeud ne contient pas le bon élément.\n"), 88, q.sortie.element);
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
            Queue qCorrecte = new Queue();
            Queue q = new Queue();
            currentQueue = q;
            
            for(int i = 1; i < 5; i++){
                String before = qCorrecte.toString();
                q.ajoute(i);
                qCorrecte.ajouteCorrect(i);
                assertTrue(MessageFormat.format(currentPre + Translator.translate("Vous n''avez pas bien mis à jour la queue en ajoutant un noeud dans la structure. La queue avant l''opération ajoute() est :\n{0}\nLa queue attendue est :\n{1}\nVotre queue est :\n{2}"), before, qCorrecte.toString(), q.toString()), qCorrecte.toString().equals(q.toString()));
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
            Queue q = new Queue();
            currentQueue = q;
            assertTrue(currentPre + Translator.translate("Quand la queue est vide, vous devez retourner null.\n"), q.retire() == null);
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
            Queue q = new Queue();
            currentQueue = q;
            Object o = new Integer(555513);
            q.ajouteCorrect(o);
            Object retire_student = q.retire();
            assertTrue(currentPre + "Quand la queue ne contient qu'un élément, vous retournez null !\n", retire_student != null);
            assertTrue(currentPre + "Quand la queue ne contient qu'un élément, vous vous ne le retournez pas correctment.\n", o.equals(retire_student));
            assertTrue(MessageFormat.format(currentPre + Translator.translate("Vous n''avez pas mis la variable ''{0}'' à null quand la queue contient un seul élément et qu''on le retire.\n"), "entree"), q.entree == null);
            assertTrue(MessageFormat.format(currentPre + Translator.translate("Vous n''avez pas mis la variable ''{0}'' à null quand la queue contient un seul élément et qu''on le retire.\n"), "sortie"), q.sortie == null);
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
            Queue qCorrecte = new Queue();
            Queue q = new Queue();
            currentQueue = q;
            
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
                    Object stu = q.retire();
                    Object exp = qCorrecte.retireCorrect();
                    assertTrue(MessageFormat.format(currentPre + Translator.translate("Vous n''avez pas bien mis à jour la queue en retirant un noeud de la structure. La queue avant l''opération retire() est :\n{0}\nLa queue attendue est :\n{1}\nVotre queue est :\n{2}"), beforeRetire, qCorrecte.toString(), q.toString()), qCorrecte.toString().equals(q.toString()));
                    assertTrue(MessageFormat.format(currentPre + Translator.translate("Vous avez retourné {0} alors que la réponse attendue est {1}. Le queue est :\n{2}"), stu, exp, q.toString()), exp.equals(stu));
                }
            }
            return null;
        }
    }

    public void catcher(Callable<Void> test) {
        String msg = "\n" + Translator.translate("Cette erreur est survenue quand la queue est :\n");
        try{
            test.call();
        }catch (ArithmeticException e){
            fail(currentPre + Translator.translate("Attention, il est interdit de diviser par zéro.") + msg + currentQueue.toString());
        }catch(ClassCastException e){
            fail(currentPre + Translator.translate("Attention, certaines variables ont été mal castées !") + msg + currentQueue.toString());
        }catch(StringIndexOutOfBoundsException e){
            fail(currentPre + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)") + msg + currentQueue.toString());
        }catch(ArrayIndexOutOfBoundsException e){
            fail(currentPre + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)") + msg + currentQueue.toString());
        }catch(NullPointerException e){
            fail(currentPre + Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.") + msg + currentQueue.toString());
        }catch(NegativeArraySizeException e){
            fail(currentPre + Translator.translate("Vous initialisez un tableau avec une taille négative.") + msg + currentQueue.toString());
        }catch(StackOverflowError e){
            fail(currentPre + Translator.translate("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même.") + msg + currentQueue.toString());
        }catch(Exception e){
            fail(currentPre + Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString() + msg + currentQueue.toString());
        }
    }
}
