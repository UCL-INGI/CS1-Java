
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
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import static org.hamcrest.CoreMatchers.is;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;

import StudentCode.Point;
import src.Correction;
import static student.Translations.Translator._;

@RunWith(Parameterized.class)
public class TestParam {

	Point p1;
	Point p2;

	Correction c1;
	Correction c2;

	public TestParam(Point p1,Point p2,Correction c1,Correction c2){
		this.p1 = p1;
		this.p2 = p2;
		this.c1 = c1;
		this.c2 = c2;
	}

	@Parameters
	public static Collection<Object []> data() {
		return Arrays.asList(new Object [][] {
			{new Point(0,0),new Point(0,0),new Correction(0,0),new Correction(0,0)},
			{new Point(2,3),new Point(2,3),new Correction(2,3),new Correction(2,3)},
			{new Point(0,0),new Point(5,0),new Correction(0,0),new Correction(5,0)},
			{new Point(0,0),new Point(0,7),new Correction(0,0),new Correction(0,7)},
			{new Point(5,4),new Point(5,13),new Correction(5,4),new Correction(5,13)},
			{new Point(3,6),new Point(4,6),new Correction(3,6),new Correction(4,6)},
			{new Point(0,0),new Point(3,4),new Correction(0,0),new Correction(3,4)},
			{new Point(4,7),new Point(8,9),new Correction(4,7),new Correction(8,9)},
			{new Point(5,5),new Point(0,5),new Correction(5,5),new Correction(0,5)},
			{new Point(8,7),new Point(8,3),new Correction(8,7),new Correction(8,3)},
			{new Point(12,14),new Point(4,6),new Correction(12,14),new Correction(4,6)}});
	}

	@Test
	public void test() {
		try {
			String msg = _("Test 2 : lorsque l''on exécute votre code avec les points {0} et {1}, votre code renvoie {2} au lieu de {3}");
			String feed = MessageFormat.format(msg,this.p1,this.p2,p1.distance(p2),c1.distance(c2));
			assertThat(feed,p1.distance(p2),is(c1.distance(c2)));
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
