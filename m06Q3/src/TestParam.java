
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

import StudentCode.Date;
import student.Translations.Translator;


@RunWith(Parameterized.class)
public class TestParam {

	private Date d1;
	private Date d2;
	private boolean equal;

	public TestParam(Date d1, Date d2, boolean equal) {
		this.d1 = d1;
		this.d2 = d2;
		this.equal = equal;
	}

	@Parameters
	public static Collection<Object []> data() {
		return Arrays.asList(new Object [][] {
			{new Date(1,1,2016),new Date(1,1,2016),true},
			{new Date(12,11,2017), new  Date(11,11,2017),false},
			{new Date(24,5,1972),new Date(12,12,1972),false},
			{new Date(17,1,2000),new Date(18,12,2010),false},
		});
	}

	@Test
	public void testLauncher() {
		try {
			String msg = Translator.translate("Test 2 : Attention, pour les dates {0} et {1}, votre code devrait renvoyer {2} mais ce n'est pas la cas !");
			String feedback = MessageFormat.format(msg,d1,d2,equal);
			assertTrue(feedback,d1.identique(d2) == equal);
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
