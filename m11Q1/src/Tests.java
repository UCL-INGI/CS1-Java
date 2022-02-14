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
    private String currentPre = preQ1;
    private PileInt currentPile;

    private String str_bad_pop = Translator.translate("Vous ne retournez pas le bon sommet de la pile. Quand le sommet est {0}, votre méthode pop() retourne {1}");
    private String sb_init = Translator.translate("Voici l'évolution de votre pile durant les tests afin que vous puissiez corriger votre code :\n\n");

    // Test avec une pile vide
    @Test
    public void mini_test_pop(){
        currentPre = preQ2;
        catcher(new t1());
    }
    private class t1 implements Callable<Void> {
        public Void call() {
            try {
                PileInt pile = new PileInt();
                currentPile = pile;
                pile.pop();
                fail(currentPre + Translator.translate("Lorsque l'on exécute votre méthode pop() sur une pile vide, elle ne lance pas l'exception IllegalStateException.\n"));
            }
            catch (IllegalStateException e) {
                // Correct
            }
            return null;
        }
    }
    
    /*
     *  Test push() to help students to build the PileInt
     */
    @Test
    public void mini_test_push(){
        currentPre = preQ1;
        catcher(new t3());
    }
    private class t3 implements Callable<Void> {
        public Void call() {
            
            //Test 1
            PileInt pile = new PileInt();
            currentPile = pile;
            pile.push(4);
            assertTrue(currentPre + Translator.translate("Le sommet de votre pile vaut null après un push().\n"), pile.sommet != null);
            assertTrue(currentPre + Translator.translate("L'élément au sommet de votre pile ne vaut pas 4 après un push(4).\n"), pile.sommet.element == 4);
            assertTrue(currentPre + Translator.translate("La méthode depth() ne retourne pas 1 après un push().\n"), pile.depth() == 1);
            
            pile.push(6);
            currentPile = pile;
            assertTrue(currentPre + Translator.translate("Le sommet de votre pile vaut null après un push().\n"), pile.sommet != null);
            assertTrue(currentPre + Translator.translate("L'élément au sommet de votre pile ne vaut pas 6 après un push(6).\n"), pile.sommet.element == 6);
            assertTrue(currentPre + Translator.translate("La méthode depth() ne retourne pas 2 après avoir effectué 2 fois la méthode push().\n"), pile.depth() == 2);
            assertTrue(currentPre + Translator.translate("Le noeud suivant du sommet vaut null après avoir effectué 2 fois la méthode push().\n"), pile.sommet.suivant != null);
            assertTrue(currentPre + Translator.translate("L'élément suivant du sommet (l'élément tout en dessous de la pile) ne vaut pas 4 après avoir effectué push(4) suivi d'un push(6).\n"), pile.sommet.suivant.element == 4);

            return null;
        }
    }
    
    @Test
    public void test_mix(){
        currentPre = preQ2;
        catcher(new t4());
    }
    private class t4 implements Callable<Void> {
        public Void call() {
            //Test 1
            PileInt pile = new PileInt();
            PileInt pileCorr = new PileInt();
            StringBuilder sb = new StringBuilder(sb_init);
            play(pile, pileCorr, sb, "push", 5);
            play(pile, pileCorr, sb, "pop", 0);
            
            //Test 2
            pile = new PileInt();
            pileCorr = new PileInt();
            sb = new StringBuilder(sb_init);
            play(pile, pileCorr, sb, "push", 4);
            play(pile, pileCorr, sb, "push", 3);
            play(pile, pileCorr, sb, "pop", 0);
            play(pile, pileCorr, sb, "pop", 0);
            
            //Test 3
            pile = new PileInt();
            pileCorr = new PileInt();
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
    public void play(PileInt pile, PileInt pileCorr, StringBuilder sb, String op, int n){
        currentPile = pile;
        int n_sommet_correct = 0;
        int n_sommet_student = 0;
        sb.append(Translator.translate("Pile après l'opération : "));
        if(op.equals("push")){
            sb.append("push(").append(n).append(")");
            currentPre = preQ1; //Since ce call push(), if an exception occur, it has to appear in the Q1
            pile.push(n);
            pileCorr.pushCorrect(n);

        }else if(op.equals("pop")){
            sb.append("pop()");
            currentPre = preQ2; //Since ce call pop(), if an exception occur, it has to appear in the Q2
            n_sommet_student = pile.pop();
            n_sommet_correct = pileCorr.popCorrect();
        }
        currentPre = preQ2; //Restore currentPre
        sb.append("\n");
        sb.append(pile.toString());
        sb.append("\n");
        //Check pop returns the correct value
        assertTrue(currentPre + sb.toString() + MessageFormat.format(str_bad_pop, n_sommet_correct, n_sommet_student), n_sommet_student == n_sommet_correct);
        //Check the Pile are equals
        assertTrue(currentPre + sb.toString(), pile.toString().equals(pileCorr.toString()));
    }

    public void catcher(Callable<Void> test) {
        String msgPile = "\n" + Translator.translate("Cette erreur est survenue quand la pile est :\n");
        try{
            test.call();
        }catch (ArithmeticException e){
            fail(currentPre + Translator.translate("Attention, il est interdit de diviser par zéro.") + msgPile + currentPile.toString());
        }catch(ClassCastException e){
            fail(currentPre + Translator.translate("Attention, certaines variables ont été mal castées !") + msgPile + currentPile.toString());
        }catch(StringIndexOutOfBoundsException e){
            fail(currentPre + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)") + msgPile + currentPile.toString());
        }catch(ArrayIndexOutOfBoundsException e){
            fail(currentPre + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)") + msgPile + currentPile.toString());
        }catch(NullPointerException e){
            fail(currentPre + Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.") + msgPile + currentPile.toString());
        }catch(NegativeArraySizeException e){
            fail(currentPre + Translator.translate("Vous initialisez un tableau avec une taille négative.") + msgPile + currentPile.toString());
        }catch(StackOverflowError e){
            fail(currentPre + Translator.translate("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même.") + msgPile + currentPile.toString());
        }catch(Exception e){
            fail(currentPre + Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString() + msgPile + currentPile.toString());
        }
    }
}
