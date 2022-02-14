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
import java.util.Random;

import student.Translations.Translator;

import StudentCode.*;

public class Tests {
    
    //Do not use \n since there are multiple questions.
    private String feedbackBuilder1 = "@{0} :\n" + Translator.translate("Le tableau doit avoir une taille de {1}. Cependant, votre code crée un tableau de taille {2}.\n");
    private String feedbackBuilder2 = "@{0} :\n" + Translator.translate("La matrice devrait avoir {1} lignes et {2} colonnes. Cependant, votre code crée une matrice de {3} lignes et {4} colonnes.");
    
    private String feedbackBuilder3 = "@{0} :\n" + Translator.translate("Vous n'effectuez pas la bonne opération pour obtenir le nombre de lignes.\n");
    private String feedbackBuilder4 = "@{0} :\n" + Translator.translate("Vous n'effectuez pas la bonne opération pour obtenir le nombre de colonnes.\n");

    @Test
    public void test_1(){ catcher(new t1(), 1); }
    private class t1 implements Callable<Void> {
        public Void call() {
            int question = 1;
            int size = new Random().nextInt(11)+1;
            int[] v = Etudiant.createIntArray(size);
            assertEquals(MessageFormat.format(feedbackBuilder1, question, size, v.length), size, v.length);
            return null;
        }
    }
    
    @Test
    public void test_2(){ catcher(new t2(), 2); }
    private class t2 implements Callable<Void> {
        public Void call() {
            int question = 2;
            int size_k = new Random().nextInt(5)+1;
            int size_l = new Random().nextInt(5)+7;
            double[][] v = Etudiant.createDoubleMatrix(size_k, size_l);
            assertEquals(MessageFormat.format(feedbackBuilder2, question, size_l, size_k, v.length, v[0].length), size_l, v.length);
            assertEquals(MessageFormat.format(feedbackBuilder2, question, size_l, size_k, v.length, v[0].length), size_k, v[0].length);
            return null;
        }
    }
    
    @Test
    public void test_3(){ catcher(new t3(), 3); }
    private class t3 implements Callable<Void> {
        public Void call() {
            int question = 3;
            int size_k = new Random().nextInt(5)+1;
            int size_l = new Random().nextInt(5)+7;
            int response_etu = Etudiant.getNumLines(new double[size_k][size_l]);
            assertEquals(MessageFormat.format(feedbackBuilder3, question), size_l, response_etu);
            return null;
        }
    }
    
    @Test
    public void test_4(){ catcher(new t4(), 4); }
    private class t4 implements Callable<Void> {
        public Void call() {
            int question = 4;
            int size_k = new Random().nextInt(5)+1;
            int size_l = new Random().nextInt(5)+7;
            int response_etu = Etudiant.getNumColumns(new double[size_k][size_l]);
            assertEquals(MessageFormat.format(feedbackBuilder4, question), size_k, response_etu);
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
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
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
