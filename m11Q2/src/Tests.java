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
    private String currentPre = preQ1;
    private static Pile currentPile;
    
    private String str_bad_pop = _("Vous ne retournez pas le bon sommet de la pile. Quand le sommet est {0}, votre méthode pop() retourne {1}");
    private String sb_init = _("Voici l'évolution de votre pile durant les tests afin que vous puissiez corriger votre code :\n\n");

    //For testing depth()
    @Test
    public void test_depth(){
        currentPre = preQ2;
        catcher(new t1());
    }
    private class t1 implements Callable<Void> {
        public Void call() {
            Pile pile = new Pile();
            currentPile = pile;
            
            test_depth_student(pile, 0);
            
            //Push
            for(int i = 1; i < 5; i++){
                pile.push(i);
                test_depth_student(pile, i);
            }
            
            for(int i = 3; i >= 0; i--){
                pile.popCorrect();
                test_depth_student(pile, i);
            }
            return null;
        }
    }
    
    /*
     *  Teste si la methode depth() de l'étudiant retourne la bonne profondeur.
     *  Si l'etudiant modifie la pile dans cette méthode, le test fail.
     */
    public void test_depth_student(Pile pile, int expected_depth){
        String d = _("Votre méthode depth() retourne {0} alors que la véritable profondeur est {1}. Voici la pile :\n\n{2}");
        String pileBeforeDepth = pile.toString();
        int student_depth = pile.depth();
        String pileAfterDepth = pile.toString();
        assertTrue(currentPre + _("Votre méthode a modifié la pile. C'est inacceptable."), pileBeforeDepth.equals(pileAfterDepth));
        assertTrue(currentPre + MessageFormat.format(d, student_depth, expected_depth, pile.toString()), student_depth == expected_depth);
    }
    
    //For testing pop()
    @Test
    public void test_pop(){
        currentPre = preQ1;
        catcher(new t4());
    }
    private class t4 implements Callable<Void> {
        public Void call() {
            //Test 1
            Pile pile = new Pile();
            currentPile = pile;
            assertTrue(currentPre + _("Votre méthode pop() doit retourner null si la pile est vide.\n"), null == pile.pop());
            
            pile = new Pile();
            Pile pileCorr = new Pile();
            StringBuilder sb = new StringBuilder(sb_init);
            play(pile, pileCorr, sb, "push", 5);
            play(pile, pileCorr, sb, "pop", 0);
            
            //Test 2
            pile = new Pile();
            pileCorr = new Pile();
            sb = new StringBuilder(sb_init);
            play(pile, pileCorr, sb, "push", 4);
            play(pile, pileCorr, sb, "push", 3);
            play(pile, pileCorr, sb, "pop", 0);
            play(pile, pileCorr, sb, "pop", 0);
            
            //Test 3
            pile = new Pile();
            pileCorr = new Pile();
            sb = new StringBuilder(sb_init);
            play(pile, pileCorr, sb, "push", 10);
            play(pile, pileCorr, sb, "push", 20);
            play(pile, pileCorr, sb, "push", 30);
            play(pile, pileCorr, sb, "pop", 0);
            play(pile, pileCorr, sb, "pop", 0);
            play(pile, pileCorr, sb, "pop", 0);
            play(pile, pileCorr, sb, "push", 20);
            play(pile, pileCorr, sb, "pop", 0);
            return null;
        }
    }
    
    /*
     *  Applique un push/pop sur les piles {pile} et {pileCorr}
     *  {n} permet de spécifier la valeur a push quand {op} vaut 'push'
     */
    public void play(Pile pile, Pile pileCorr, StringBuilder sb, String op, int n){
        currentPile = pile;
        Object n_sommet_correct = 0;
        Object n_sommet_student = 0;
        sb.append(_("Pile après l'opération : "));
        if(op.equals("push")){
            sb.append("push(").append(n).append(")");
            pile.push(new Integer(n));
            pileCorr.push(new Integer(n));

        }else if(op.equals("pop")){
            sb.append("pop()");
            n_sommet_student = pile.pop();
            n_sommet_correct = pileCorr.popCorrect();
        }
        sb.append("\n");
        sb.append(pile.toString());
        sb.append("\n");
        //Check pop returns the correct value
        assertTrue(currentPre + sb.toString() + MessageFormat.format(str_bad_pop, n_sommet_correct, n_sommet_student), n_sommet_correct.equals(n_sommet_student));
        //Check the Pile are equals
        assertTrue(currentPre + sb.toString(), pile.toString().equals(pileCorr.toString()));
    }

    public void catcher(Callable<Void> test) {
        String msgPile = "\n" + _("Cette erreur est survenue quand la pile est :\n");
        try{
            test.call();
        }catch (ArithmeticException e){
            fail(currentPre + _("Attention, il est interdit de diviser par zéro.") + msgPile + currentPile.toString());
        }catch(ClassCastException e){
            fail(currentPre + _("Attention, certaines variables ont été mal castées !") + msgPile + currentPile.toString());
        }catch(StringIndexOutOfBoundsException e){
            fail(currentPre + _("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)") + msgPile + currentPile.toString());
        }catch(ArrayIndexOutOfBoundsException e){
            fail(currentPre + _("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)") + msgPile + currentPile.toString());
        }catch(NullPointerException e){
            fail(currentPre + _("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.") + msgPile + currentPile.toString());
        }catch(NegativeArraySizeException e){
            fail(currentPre + _("Vous initialisez un tableau avec une taille négative.") + msgPile + currentPile.toString());
        }catch(StackOverflowError e){
            fail(currentPre + _("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même.") + msgPile + currentPile.toString());
        }catch(Exception e){
            fail(currentPre + _("Une erreur inattendue est survenue dans votre tâche : ") + e.toString() + msgPile + currentPile.toString());
        }
    }
}
