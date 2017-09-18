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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.text.MessageFormat;
import java.util.Random;
import java.util.Arrays;
import java.util.concurrent.Callable;

import static student.Translations.Translator._;

import StudentCode.*;

public class Tests {
    
    private String preQ1 = "@1 :\n";
    private String preQ2 = "@2 :\n";
    private String currentPre = preQ1;
    String feedback1 = _("Votre méthode {0} ne modifie pas correctement le tableau.\nLe tableau original est :\n{1}\nLa réponse attendue est :\n{2}\nVotre tableau est :\n{3}");
    String feedback2 = _("Votre méthode {0} ne modifie pas correctement le tableau avec n = {1}.\nLe tableau original est :\n{2}\nLa réponse attendue est :\n{3}\nVotre tableau est :\n{4}");
    
    private int[] genArray(int size){
        Random r = new Random();
        int[] tab = new int[size];
        for (int i = 0; i < size; i++)
            tab[i] = r.nextInt(100);
        return tab;
    }
    
    
    @Test
    public void test1(){
        currentPre = preQ1;
        catcher(new t1());
    }
    private class t1 implements Callable<Void> {
        public Void call() {
            for (int i = 1; i < 8; i++){
                int[] tabOrigin = genArray(i);
                int[] copy_corr = tabOrigin.clone();
                int[] copy_stud = tabOrigin.clone();
                Correction.rotateRightOne(copy_corr);
                Etudiant.rotateRightOne(copy_stud);
                if(!Arrays.equals(copy_corr, copy_stud))
                    fail(currentPre + MessageFormat.format(feedback1, "rotateRightOne()", Arrays.toString(tabOrigin), Arrays.toString(copy_corr), Arrays.toString(copy_stud)));
            }
            return null;
        }
    }
    
    @Test
    public void test2(){
        currentPre = preQ2;
        catcher(new t2());
    }
    private class t2 implements Callable<Void> {
        public Void call() {
            for (int i = 1; i < 8; i++){
                int[] tabOrigin = genArray(i);
                int[] copy_corr = tabOrigin.clone();
                int[] copy_stud = tabOrigin.clone();
                int n = i-1;
                Correction.rotateRight(copy_corr, n);
                Etudiant.rotateRight(copy_stud, n);
                if(!Arrays.equals(copy_corr, copy_stud))
                    fail(currentPre + MessageFormat.format(feedback2, "rotateRight()", n, Arrays.toString(tabOrigin), Arrays.toString(copy_corr), Arrays.toString(copy_stud)));
            }
            return null;
        }
    }
    
    public void catcher(Callable<Void> test) {
        try{
            test.call();
        }catch (ArithmeticException e){
            fail(currentPre + _("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(currentPre + _("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(currentPre + _("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(currentPre + _("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(currentPre + _("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(NegativeArraySizeException e){
            fail(currentPre + _("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(currentPre + _("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(currentPre + _("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }
}
