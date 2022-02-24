/**
 *  Copyright (c)  2016 Ludovic Taffin, 2017 Brandon Naitali, Olivier Martin
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
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.Random;

import student.Translations.Translator;
import StudentCode.*;

public class Tests {
    @Rule public TestName name = new TestName();
    
    @Test
    public void test_1(){
        String feedback = MessageFormat.format(Translator.translate("Vérifiez que vous gérez la factorielle de {0}.\n"), 1);
        catcher(new TestFactorielle(1, feedback), 1);
    }
    @Test
    public void test_2(){
        String feedback = MessageFormat.format(Translator.translate("Vérifiez que vous gérez la factorielle de {0}.\n"), 0);
        catcher(new TestFactorielle(0, feedback), 1);
    }
    
    @Test
    public void test_3(){
        Random r = new Random(); // petit random car factorielle grande
        catcher(new TestFactorielle(r.nextInt(9)+ 2, ""), 1);// random entre 2 et 11
        catcher(new TestFactorielle(r.nextInt(5)+ 2, ""), 1);// random entre 2 et 7
        catcher(new TestFactorielle(r.nextInt(4)+ 8, ""), 1);// random entre 8 et 11
    }
    
    private class TestFactorielle implements Callable<Void> {
        int x;
        String feedbackAdd;
        public TestFactorielle(int x, String feedback){
            this.x = x;
            this.feedbackAdd = feedback;
        }
        public Void call() {
            int resultat = Correction.fact(x); // résultat attendu
            int etudiantResult = Etudiant.fact(x); // résultat de l'étudiant
            String feedbackBase  = Translator.translate("La factorielle de {0} donne {1,number,#}, pourtant, votre code renvoie {2,number,#}.\n");
            String erreur = MessageFormat.format(feedbackBase, x, resultat, etudiantResult);
            if(resultat != etudiantResult) fail(test_name() + " : " + erreur + " " + feedbackAdd);
            System.err.println(MessageFormat.format(Translator.translate("{0} : réussi"), test_name()));
            return null;
        }
        
    }
    public void catcher(Callable<Void> test, int n) {
        try{
            test.call();
        }catch (ArithmeticException e){
            fail(Translator.translate("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(Translator.translate("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(Exception e){
            fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }
    private String test_name(){
        String s = name.getMethodName().replaceAll("_", " ");
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
