
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

import static org.junit.Assert.assertThat;
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
import static student.Translations.Translator._;

@RunWith(Parameterized.class)
public class TestParam {

	private Date today;
	private Date tommorow;

	public TestParam(Date today,Date tommorow){
		this.today = today;
		this.tommorow = tommorow;
	}

	@Parameters
	public static Collection<Object []> data() {
		return Arrays.asList(new Object [][] {
			{new Date(12,04,2017),new Date(13,04,2017)},
			{new Date(31,12,2010),new Date(1,1,2011)},
			{new Date(30,3,2010),new Date(31,3,2010)},
			{new Date(30,4,2017),new Date(1,5,2017)},
			{new Date(28,2,2012),new Date(1,3,2012)}
		});
	}

	@Test
	public void testLauncher() {
		try {
			String msg = _("Test 2 : Attention, pour la date {0} vous renvoyez {1} à la place de {2}");
			String feedback = MessageFormat.format(msg,today,today.demain(),tommorow);
			assertThat(feedback,today.demain(),is(tommorow));
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
}
