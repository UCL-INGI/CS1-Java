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
import org.junit.Rule;
import org.junit.rules.TestName;
import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.Random;
import java.util.Arrays;

import static student.Translations.Translator._;

import StudentCode.*;

public class Tests {
    
    @Rule public TestName name = new TestName();
    
    private int[][] gen_random_matrix(int size, int size2){
        Random r = new Random();
        int[][] m = new int [size][size2];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size2; j++){
                m[i][j] = r.nextInt(100);
            }
        }
        return m;
    }
    
    /*
     * Test vériviant le calcul de la somme de deux matrices et les assertions éronées.
     */
    @Test
    public void test_1(){ catcher(new t1()); }
    private class t1 implements Callable<Void> {
        public Void call() {
            int a [][] = gen_random_matrix(10,8);
            int b [][] = gen_random_matrix(10,8);
            int c_expected[][] = Correction.add(a, b);
            int c_student[][] = gen_random_matrix(1,1);
            
            //Check student does not modify array
            int[][] origin_a = new int[a.length][a[0].length];
            for (int i = 0; i < origin_a.length; i++)
                origin_a[i] = Arrays.copyOf(a[i], a[i].length);
            int[][] origin_b = new int[b.length][b[0].length];
            for (int i = 0; i < origin_b.length; i++)
                origin_b[i] = Arrays.copyOf(b[i], b[i].length);
            
            try{
                c_student = Etudiant.add(a, b);
            }catch(AssertionError e){
                fail(MessageFormat.format(_("{0} : vous utilisez mal les assertions."), test_name()));
            }
            
            String noModif = _("Votre méthode modifie le contenu du tableau passé en argument. Une telle modification n'est autorisée que si elle est explicitement indiquée dans les spécifications. Ce n'est pas le cas pour cette méthode.\n");
            assertTrue(noModif, java.util.Arrays.deepEquals(a,origin_a));
            assertTrue(noModif, java.util.Arrays.deepEquals(b,origin_b));
            
            if(c_student == null)
                fail(MessageFormat.format(_("{0} : votre code retourne null."), test_name()));
            if(! Arrays.deepEquals(c_student, c_expected))
                fail(MessageFormat.format(_("{0} : votre code ne calcule pas la somme correctement."), test_name()));
            else
                System.err.println(MessageFormat.format(_("{0} : réussi"), test_name()));
            return null;
        }
    }
    
    /**
     * Test sur des matrices égales à null
     * @pre -
     * @post -
     */
    @Test
    public void test_2(){ catcher(new t2()); }
    private class t2 implements Callable<Void> {
        public Void call() {
            try {
                Etudiant.add(gen_random_matrix(3,2), null);
                Etudiant.add(null, gen_random_matrix(3,2));
                Etudiant.add(null, null);
            }catch(AssertionError e){
                //Si l'étudiant à correctement lancé une AssertionError
                System.err.println(MessageFormat.format(_("{0} : réussi"), test_name()));
                return null;
            }catch(NullPointerException e){
            	fail(MessageFormat.format(_("{0} : vous devez lancer un assert dans le cas où a et/ou b est null.") + "\n" + _("Faites aussi attention à l''ordre de vos assertions. Par exemple, l''assertion qui vérifie que ''a'' est différent de null doit venir avant l''assertion qui vérifie la taille de la matrice car sinon, vous allez provoquer une NullPointerException."), test_name()));            }catch(Exception e){
                //Intercept eventual exceptions due to missing assert of student
            }
            fail(MessageFormat.format(_("{0} : vous devez lancer un assert dans le cas où a et/ou b est null."), test_name()));
            return null;
        }
    }
    
    /**
     * Tests sur des matrices aux formats étranges:
     *
     *  * * *  et   * * *  par exemple.
     *  * * *       * *
     *  * * *       *
     *
     */
    @Test
    public void test_3(){ catcher(new t3()); }
    private class t3 implements Callable<Void> {
        public Void call() {
            int[][] d = new int[10][];
            for(int i = 0; i < d.length; i++){
                d[i] = new int[d.length-i];
            }
            try {
                Etudiant.add(gen_random_matrix(10,10), d);
                Etudiant.add(d, gen_random_matrix(10,10));
            }catch(AssertionError e){
                //Si l'étudiant à correctement lancé une AssertionError
                System.err.println(MessageFormat.format(_("{0} : réussi"), test_name()));
                return null;
            }catch(Exception e){
                //Intercept eventual exceptions due to missing assert of student
            }
            fail(MessageFormat.format(_("{0} : vous devez lancer un assert dans le cas où les lignes/colonnes ne sont pas TOUTES de le même longeur (si un des double tableaux est arrangé sous forme triangle par exemple)."), test_name()));
            return null;
        }
    }
    
    /**
     * Test sur des matrices aux foramts différents:
     *
     *    * *   et  * * *  par exemple.
     *    * *       * * *
     *              * * *
     *
     */
    @Test
    public void test_4(){ catcher(new t4()); }
    private class t4 implements Callable<Void> {
        public Void call() {
            try {
                Etudiant.add(gen_random_matrix(4,6), gen_random_matrix(6,2));
            }catch(AssertionError e){
                //Si l'étudiant à correctement lancé une AssertionError
                System.err.println(MessageFormat.format(_("{0} : réussi"), test_name()));
                return null;
            }catch(Exception e){
                //Intercept eventual exceptions due to missing assert of student
            }
            fail(MessageFormat.format(_("{0} : vous devez lancer un assert dans le cas où les matrices ne sont pas de la même taille."), test_name()));
            return null;
        }
    }

    public void catcher(Callable<Void> test) {
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
