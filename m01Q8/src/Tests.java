/**
 *  Copyright (c)  2017 Naitali Brandon
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
	public void test_mediane(int a, int b, int c) {
		double resultEtudiant = Etudiant.mediane(a, b, c);
		double result = Correction.mediane(a, b, c);
	   	String form = Translator.translate("La mediane entre {0}, {1} et {2} vaut {3}, or votre programme calcule {4}.\n");
		assertTrue(MessageFormat.format(form, a, b, c, result, resultEtudiant), result == resultEtudiant);
    }
    
    /**
    *	Lanceur de test
    **/
    @Test
	public void testLauncher(){
		try{
            test_mediane(5,7,9);
            test_mediane(12,5,7);
            test_mediane(5,3,4);
            test_mediane(10,1,-3);
            test_mediane(-10,-20,1);
            test_mediane(4,4,4);
            test_mediane(4,5,4);
            Random r = new Random();
            test_mediane(r.nextInt(99),r.nextInt(99),r.nextInt(99));
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
