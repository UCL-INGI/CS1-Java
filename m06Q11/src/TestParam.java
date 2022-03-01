
/**
 *  Copyright (c) 2017 Dubray Alexandre 
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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import static org.hamcrest.CoreMatchers.is;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;

import StudentCode.Rectangle;
import student.Translations.Translator;

@RunWith(Parameterized.class)
public class TestParam {

	Rectangle r1,r2;
	boolean same;

	public TestParam(Rectangle r1,Rectangle r2,boolean same){
		this.r1 = r1;
		this.r2 = r2;
		this.same = same;
	}

	@Parameters
	public static Collection<Object []> data() {
		Rectangle r1 = new Rectangle(0,0);
		Rectangle r2 = new Rectangle(0,0);
		Rectangle r3 = new Rectangle(2,1);
		Rectangle r4 = r2;
        Rectangle r5 = new Rectangle(0,1);
		return Arrays.asList(new Object [][]{
			{r1,r1,true},
			{r1,r2,true},
			{r1,r3,false},
			{r4,r2,true},
            {r1, r5, false}
		});
	}

	@Test
	public void test() {
		try {
			String msg = Translator.translate("Lorsque l''on exécute votre code avec les rectangles {0} et {1}, votre méthode devrait renvoyer {2} mais ce n'est pas le cas !");
			String feed = MessageFormat.format(msg,r1,r2,same);
			assertTrue(feed,r1.identique(r2) == same);
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
