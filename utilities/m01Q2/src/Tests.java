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
import org.junit.Rule;
import org.junit.rules.TestName;
import java.text.MessageFormat;
import java.util.concurrent.Callable;

import student.Translations.Translator;

import StudentCode.*;

public class Tests {
    
    private String feedbackBuilder = Translator.translate("{0} : Un véhicule roulant à {1}km/h dans une zone limitée à {2}km/h doit recevoir une amende de {3}€. Cependant, votre code calcule {4}€.\n");
    @Rule public TestName name = new TestName();
    
    private class Test_inner implements Callable<Void> {
        
        private int v_max, v_vehicule;
        private String more_feeback;
        
        public Test_inner(int v_max, int v_vehicule, String more_feeback){
            this.v_max = v_max;
            this.v_vehicule = v_vehicule;
            this.more_feeback = more_feeback;
        }
        
        public Void call() {
            double reponse_etudiant = Etudiant.amende(v_max, v_vehicule);
            double expected = Correction.amende(v_max, v_vehicule);
            String feedback = MessageFormat.format(feedbackBuilder, test_name(), v_vehicule, v_max, expected, reponse_etudiant) + " " + more_feeback;
            assertTrue(feedback, expected == reponse_etudiant);
            System.err.println(MessageFormat.format(Translator.translate("{0} : réussi"), test_name()));
            return null;
        }
    }
    
    @Test
    public void test_1(){
        catcher(new Test_inner(50, 55, ""));
    }
    
    @Test
    public void test_2(){
        catcher(new Test_inner(50, 62, Translator.translate("Lorsque l'excès de vitesse dépasse 10km/h, l'amende est doublée.\n")));
    }

    @Test
    public void test_3(){
        catcher(new Test_inner(50, 50, Translator.translate("Lorsque les vitesses sont égales, l'amende doit être de zéro.\n")));
    }
      
    @Test
    public void test_4(){
        catcher(new Test_inner(50, 50, Translator.translate("Lorsque les vitesses sont égales, l'amende doit être de zéro.\n")));
    }

    @Test
    public void test_5(){
        catcher(new Test_inner(50, 40, Translator.translate("Pas d'amende requise car la vitesse du véhicule est inférieure à la vitesse maximale.\n")));
    }

    public void catcher(Callable<Void> test) {
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
    
    /*
     * Return the current test name. The first letter is upper case and '_' are replaced by ' '
     */
    private String test_name(){
        String s = name.getMethodName().replaceAll("_", " ");
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
