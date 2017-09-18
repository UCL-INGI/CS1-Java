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

import static student.Translations.Translator._;

import StudentCode.*;

public class Tests {
    
    //In case of exception, we use the content of these variables. Do not forget to use them !!!
    private static FIFOQueue currentQueue;
    
    @Test
    public void test_vide(){catcher(new t1());}
    private class t1 implements Callable<Void> {
        public Void call() {
            FIFOQueue q = new FIFOQueue();
            currentQueue = q;
            assertTrue(_("Quand la queue est vide, vous devez retourner null.\n"), q.retire() == null);
            return null;
        }
    }
    
    @Test
    public void test_one_element(){
        catcher(new t2());
    }
    private class t2 implements Callable<Void> {
        public Void call() {
            FIFOQueue q = new FIFOQueue();
            currentQueue = q;
            Object o = new Integer(555513);
            q.ajoute(o);
            Object retire_student = q.retire();
            assertTrue(_("Quand la queue ne contient qu'un élément, vous retournez null !\n"), retire_student != null);
            assertTrue(_("Quand la queue ne contient qu'un élément, vous vous ne le retournez pas correctment.\n"), o.equals(retire_student));
            assertTrue(MessageFormat.format(_("Vous n''avez pas mis la variable ''{0}'' à null quand la queue contient un seul élément et qu''on le retire.\n"), "entree"), q.entree == null);
            assertTrue(MessageFormat.format(_("Vous n''avez pas mis la variable ''{0}'' à null quand la queue contient un seul élément et qu''on le retire.\n"), "sortie"), q.sortie == null);
            return null;
        }
    }
    
    //For testing pop()
    @Test
    public void test_more_element(){
        //These 2 tests have to be correct before we run this
        try{
            test_vide();
            test_one_element();
        }catch(Throwable t){
            return;
        }
        catcher(new t4());
    }
    private class t4 implements Callable<Void> {
        public Void call() {
            FIFOQueue qCorrecte = new FIFOQueue();
            FIFOQueue q = new FIFOQueue();
            currentQueue = q;
            
            for(int j = 0; j < 2; j++){
                q.ajoute(2);
                q.ajoute(4);
                q.ajoute(6);
                q.ajoute(8);
                
                qCorrecte.ajoute(2);
                qCorrecte.ajoute(4);
                qCorrecte.ajoute(6);
                qCorrecte.ajoute(8);
                
                for(int i = 0; i < 4; i++){
                    String beforeRetire = qCorrecte.toString();
                    Object stu = q.retire();
                    Object exp = qCorrecte.retireCorrect();
                    assertTrue(MessageFormat.format(_("Vous n''avez pas bien mis à jour la queue en retirant un noeud de la structure. La queue avant l''opération retire() est :\n{0}\nLa queue attendue est :\n{1}\nVotre queue est :\n{2}"), beforeRetire, qCorrecte.toString(), q.toString()), qCorrecte.toString().equals(q.toString()));
                    assertTrue(MessageFormat.format(_("Vous avez retourné {0} alors que la réponse attendue est {1}. Le queue est :\n{2}"), stu, exp, q.toString()), exp.equals(stu));
                }
            }
            return null;
        }
    }

    public void catcher(Callable<Void> test) {
        String msg = "\n" + _("Cette erreur est survenue quand la queue est :\n");
        try{
            test.call();
        }catch (ArithmeticException e){
            fail(_("Attention, il est interdit de diviser par zéro.") + msg + currentQueue.toString());
        }catch(ClassCastException e){
            fail(_("Attention, certaines variables ont été mal castées !") + msg + currentQueue.toString());
        }catch(StringIndexOutOfBoundsException e){
            fail(_("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)") + msg + currentQueue.toString());
        }catch(ArrayIndexOutOfBoundsException e){
            fail(_("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)") + msg + currentQueue.toString());
        }catch(NullPointerException e){
            fail(_("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.") + msg + currentQueue.toString());
        }catch(NegativeArraySizeException e){
            fail(_("Vous initialisez un tableau avec une taille négative.") + msg + currentQueue.toString());
        }catch(StackOverflowError e){
            fail(_("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même.") + msg + currentQueue.toString());
        }catch(Exception e){
            fail(_("Une erreur inattendue est survenue dans votre tâche : ") + e.toString() + msg + currentQueue.toString());
        }
    }
}
