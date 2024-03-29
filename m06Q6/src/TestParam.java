
/**
 *  Copyright (c)  Dubray Alexandre 2017
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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.hamcrest.CoreMatchers.is;

import java.util.Random;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;

import StudentCode.Fraction;
import student.Translations.Translator;

@RunWith(Parameterized.class)
public class TestParam {

	Fraction f;
	boolean res;

	public TestParam(Fraction f,boolean res) {
		this.f = f;
		this.res = res;
	}

	@Parameters
	public static Collection<Object []> data() {
		return Arrays.asList(new Object [][] {
			{new Fraction(1,1),true},
			{new Fraction(4,2),true},
			{new Fraction(5,1),true},
			{new Fraction(0,17),true},
			{new Fraction(1,14),false},
			{new Fraction(16,3),false},
			{new Fraction(4,15),false}
		});
	}

	@Test
	public void testLauncher() {
		try {
			String msg = Translator.translate("Test 2 : lorsque l''on lance la fonction entier() avec la fraction {0}, votre code devrait renvoyer {1} mais ce n''est pas le cas.");
			String feedback = MessageFormat.format(msg,f,res);
			assertTrue(feedback,f.entier() == res);
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
        }catch(NegativeArraySizeException e){
            fail(Translator.translate("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(Translator.translate("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
	}
}
