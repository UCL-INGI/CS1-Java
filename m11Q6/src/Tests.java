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
    private static Liste currentListe;
    private String currentPre = preQ1;
    
    @Test
    public void test_contains(){
        currentPre = preQ1;
        catcher(new t1());
    }
    private class t1 implements Callable<Void> {
        public Void call() {
            Liste q = new Liste();
            currentListe = q;
            test_contains(q, new String[] {"Erlang", "HTML"});
            
            q = new Liste();
            currentListe = q;
            q.ajoute("Erlang");
            test_contains(q, new String[] {"Erlang", "HTML"});
            
            q = new Liste();
            currentListe = q;
            q.ajoute("Java");
            q.ajoute("Python");
            q.ajoute("Scala");
            q.ajoute("C++");
            q.ajoute("Scala");
            q.ajoute("Erlang");
            q.ajoute("Erlang");
            q.ajoute("Bash");
            test_contains(q, new String[] {"Erlang", "Javascript", "C", "C++", "Bash", "Java", "Scala", "HTML"});
            return null;
        }
    }
    
    @Test
    public void test_retire(){
        currentPre = preQ2;
        catcher(new t2());
    }
    private class t2 implements Callable<Void> {
        public Void call() {
            Liste q = new Liste();
            Liste qCorrect = new Liste();
            currentListe = q;
            assertTrue(currentPre + Translator.translate("Vous devez retourner 0 quand la liste est vide.\n"), q.retire("Java") == 0);
            
            qCorrect.ajoute("Java");
            q.ajoute("Java");
            test_retire(q, qCorrect, "Erlang");
            test_retire(q, qCorrect, "Java");
            
            qCorrect.ajoute("Python");
            q.ajoute("Python");
            qCorrect.ajoute("Python");
            q.ajoute("Python");
            qCorrect.ajoute("Java");
            q.ajoute("Java");
            qCorrect.ajoute("Java");
            q.ajoute("Java");
            qCorrect.ajoute("Scala");
            q.ajoute("Scala");
            qCorrect.ajoute("Java");
            q.ajoute("Java");
            qCorrect.ajoute("Bash");
            q.ajoute("Bash");
            test_retire(q, qCorrect, "Scala");
            test_retire(q, qCorrect, "Pyhton");
            test_retire(q, qCorrect, "Java");
            test_retire(q, qCorrect, "Bash");
            return null;
        }
    }
    
    //Used by the @Test test_contains()()
    private void test_contains(Liste q, String[] to_test){
        for(int i = 0; i < to_test.length; i++){
            String liste_before_run_student_code = q.toString();
            boolean expected = q.contientCorrect(to_test[i]);
            boolean student = q.contient(to_test[i]);
            assertTrue(currentPre + Translator.translate("Votre méthode a modifié la liste. C'est inacceptable.\n"), liste_before_run_student_code.equals(q.toString()));
            assertTrue(MessageFormat.format(currentPre + Translator.translate("{0}(\"{1}\") a retourné {2} avec la liste :\n{3}"), "contient", to_test[i], student, q.toString()), expected == student);
        }
    }
    
    //Used by the @Test test_retire()
    private void test_retire(Liste q, Liste qCorrect, String to_test){
        String origin = q.toString();
        int exp = qCorrect.retireCorrect(to_test);
        int stu = q.retire(to_test);
        String feedback = MessageFormat.format(currentPre + Translator.translate("Vous retournez {0} alors qu''il faut retourner {1} lors de l''appel de {2}(\"{3}\") sur la liste :\n{4}"), stu, exp, "retire", to_test, q.toString());
        assertTrue(feedback, exp == stu);
        
        feedback = MessageFormat.format(currentPre + Translator.translate("Votre méthode {0}(\"{1}\") ne retire pas correctement les éléments.\nLa liste est :\n{2}\nVotre liste modifiée est :\n{3}\nLa liste attendue est :\n{4}"), "retire", to_test, origin, q.toString(), qCorrect.toString());
        assertTrue(feedback, qCorrect.toString().equals(q.toString()));
    }

    public void catcher(Callable<Void> test) {
        String msg = "\n" + Translator.translate("Cette erreur est survenue quand la liste est :\n");
        try{
            test.call();
        }catch (ArithmeticException e){
            fail(currentPre + Translator.translate("Attention, il est interdit de diviser par zéro.") + msg + currentListe.toString());
        }catch(ClassCastException e){
            fail(currentPre + Translator.translate("Attention, certaines variables ont été mal castées !") + msg + currentListe.toString());
        }catch(StringIndexOutOfBoundsException e){
            fail(currentPre + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)") + msg + currentListe.toString());
        }catch(ArrayIndexOutOfBoundsException e){
            fail(currentPre + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)") + msg + currentListe.toString());
        }catch(NullPointerException e){
            fail(currentPre + Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.") + msg + currentListe.toString());
        }catch(NegativeArraySizeException e){
            fail(currentPre + Translator.translate("Vous initialisez un tableau avec une taille négative.") + msg + currentListe.toString());
        }catch(StackOverflowError e){
            fail(currentPre + Translator.translate("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même.") + msg + currentListe.toString());
        }catch(Exception e){
            fail(currentPre + Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString() + msg + currentListe.toString());
        }
    }
}
