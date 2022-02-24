/**
 *  Copyright (c)  2017 Naitali Brandon
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; w ithout even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

// Inspiré de http://www.mkyong.com/unittest/junit-4-tutorial-6-parameterized-test/

package src;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import java.util.Random;
import java.text.MessageFormat;

import student.Translations.Translator;

import StudentCode.*;

public class Tests {
    public void test_moyenne() {
        Random r = new Random();
        int numberA = r.nextInt(2000) - 1000;
        int numberB = r.nextInt(2000) - 1000;
        int numberC = r.nextInt(2000) - 1000;
        double resultEtudiant = Etudiant.moye(numberA, numberB, numberC);
        double result = Correction.moye(numberA, numberB, numberC);
        String form = Translator.translate("La moyenne entre {0,number,#}, {1,number,#} et {2,number,#} vaut {3,number,#}, or votre programme calcule {4,number,#}.\n");
        assertTrue(MessageFormat.format(form, numberA, numberB, numberC, result, resultEtudiant), result == resultEtudiant);
    }

    
    @Test
    public void testLauncher(){
        int nombreTests = 4;
        try{
            // pas vraiment de cas particuliers à tester, donc 4 tests random
            for(int i = 0; i < nombreTests; i++){
                test_moyenne();
            }
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
}
