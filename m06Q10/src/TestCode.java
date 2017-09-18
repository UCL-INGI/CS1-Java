
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

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;

import static org.hamcrest.CoreMatchers.is;

import java.util.Random;
import java.text.MessageFormat;
import java.util.concurrent.Callable;

import StudentCode.Rectangle;
import static student.Translations.Translator._;

import org.mockito.Mockito;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import org.mockito.exceptions.verification.WantedButNotInvoked;


public class TestCode {

	@Rule
	public TestName name = new TestName();

	private String test_name() {
		String s = name.getMethodName().replaceAll("_"," ");
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}

	private void printSucceed() {
		System.err.println(MessageFormat.format(_("{0} : réussi"),test_name()));
	}

	private class t1 implements Callable<Void> {

		/**
		 * @pre		-
		 * @post	Vérifie que l'étudiant utilise bien les getters 
		 *			pour récupérer les valeurs des variable d'instance.
		 */
		public Void call() {

			Rectangle r1 = new Rectangle(10,10);
			Rectangle r2 = new Rectangle(20,20);

			Rectangle spy1 = Mockito.spy(r1);
			Rectangle spy2 = Mockito.spy(r2);

			String msg = _("{0} : vous devez utiliser la méthode surface() pour récupérer les valeurs des surfaces !");
			String feedback = MessageFormat.format(msg,test_name());

			try {
				boolean res = spy1.memeSurface(spy2);
				verify(spy1,atLeast(1)).surface();
				verify(spy2,atLeast(1)).surface();
				return null;
			} catch (WantedButNotInvoked e) {
				fail(feedback);
				return null;
			}
		}
	}

	private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de l'étudiant renvoie la bonne réponse
		 */
		public Void call(){

			Random r = new Random();
			double lon1 = r.nextInt(100), larg1 = r.nextInt(100);
			double lon2 = r.nextInt(100), larg2 = r.nextInt(100);

			Rectangle r1 = new Rectangle(lon1,larg1), r2 = new Rectangle(lon2,larg2);

			String msg = _("{0} : lorsque l''on compare les rectangle {1} et {2}, votre méthode devrait renvoyer {3} mais ce n'est pas le cas !");
			String feed = MessageFormat.format(msg,test_name(),r1,r2,lon1*larg1 == lon2*larg2);

			assertThat(feed,r1.memeSurface(r2),is(lon1*larg1 == lon2*larg2));

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
		catcher(new t2());
		printSucceed();
	}
}
