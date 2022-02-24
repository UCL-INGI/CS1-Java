/**
 *  Copyright (c)  2016 Ludovic Taffin, 2017 Olivier Martin
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
import java.util.Arrays;

import student.Translations.Translator;

import StudentCode.*;

public class Tests {
    
    //Do not use \n since there are multiple questions.
    private String feedbackBuilder1 = "@{0} :\n" + Translator.translate("Le test t1 == t2 ne réussi pas.\n");
    private String feedbackBuilder2 = "@{0} :\n" + Translator.translate("Avec t1 = {1} et t2 = {2}, votre code ne donne pas le bon résultat.");

    @Test
    public void test_1(){ catcher(new t1(), 1); }
    private class t1 implements Callable<Void> {
        public Void call() {
            int question = 1;
            Etudiant.equalequalSucceeds();
            assertTrue(MessageFormat.format(feedbackBuilder1, question), Etudiant.t1 == Etudiant.t2);
            return null;
        }
    }
    
    @Test
    public void test_2(){ catcher(new t2(), 2); }
    private class t2 implements Callable<Void> {
        public Void call() {
            int question = 2;
            
            double[] t3 = {0.1, 0.2};
            double[] t4 = {0.4, 0.5};
            double[] t5 = {0.1, 0.2};
            double[] t6 = {0.1, 0.2, 0.3};
            double[] t7 = {};

            assertEquals(MessageFormat.format(feedbackBuilder2, question, Arrays.toString(t3), Arrays.toString(t4)), Correction.egal(t3, t4), Etudiant.egal(t3, t4));
            assertEquals(MessageFormat.format(feedbackBuilder2, question, Arrays.toString(t3), Arrays.toString(t5)), Correction.egal(t3, t5), Etudiant.egal(t3, t5));
            assertEquals(MessageFormat.format(feedbackBuilder2, question, Arrays.toString(t6), Arrays.toString(t3)), Correction.egal(t6, t3), Etudiant.egal(t6, t3));
            assertEquals(MessageFormat.format(feedbackBuilder2, question, Arrays.toString(t6), Arrays.toString(t7)), Correction.egal(t6, t7), Etudiant.egal(t6, t7));
            return null;
        }
    }
    
    public void catcher(Callable<Void> test, int nQuestion) {
        try{
            test.call();
        }catch (ArithmeticException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)") + " " + Translator.translate("Les deux tableaux ne sont pas forcément de la même taille…"));
        }catch(NullPointerException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(NegativeArraySizeException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }
}
