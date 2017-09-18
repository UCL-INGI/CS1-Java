
/**
 *  Copyright (c)  2017 Dubray Alexandre
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
import org.junit.Rule;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertThat;
import org.junit.rules.TestName;

import static org.hamcrest.CoreMatchers.is;

import StudentCode.Point;
import static student.Translations.Translator._;

import java.util.concurrent.Callable;
import java.text.MessageFormat;
import java.util.Random;

public class TestCode {

	@Rule
	public TestName name = new TestName();

	private String test_name(){
		String s = name.getMethodName().replaceAll("_"," ");
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}

	private void printSucceed(){
		System.err.println(MessageFormat.format(_("{0} : réussi"),test_name()));
	}

	private class t1 implements Callable<Void> {

		/**
		 * @pre		-
		 * @post	Vérifie la méthode getX() de l'étudiant
		 */
		public Void call(){
			Random r = new Random();
			double x = r.nextDouble(), y = r.nextDouble();
			Point p = new Point(x,y);

			String feed = MessageFormat.format(_("{0} : pour la paire {1}, votre méthode getX() renvoie {2} au lieu de {3}"),test_name(),p,p.getX(),x);
			assertThat(feed,p.getX(),is(x));
			return null;
		}
	}

	private class t2 implements Callable<Void> {

		/**
		 * @pre		-
		 * @post	Vérifie la méthode getY() de l'étudiant
		 */
		public Void call(){
			Random r = new Random();
			double x = r.nextDouble(),y = r.nextDouble();
			Point p = new Point(x,y);

			String feed = MessageFormat.format(_("{0} : pour la paire {1}, votre méthode getY() renvoie {2} au lieu de {3}"),test_name(),p,p.getY(),y);
			assertThat(feed,p.getY(),is(y));
			return null;
		}
	}


	public void catcher(Callable<Void> f) {
		try {
			f.call();
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

	@Test 
	public void test_1() {
		catcher(new t1());
		printSucceed();
	}

	@Test
	public void test_2() {
		catcher(new t1());
		printSucceed();
	}
}
