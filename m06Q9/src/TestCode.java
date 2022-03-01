
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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;

import static org.hamcrest.CoreMatchers.is;

import java.util.Random;
import java.text.MessageFormat;
import java.util.concurrent.Callable;

import StudentCode.Rectangle;
import student.Translations.Translator;

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
		System.err.println(MessageFormat.format(Translator.translate("{0} : réussi"),test_name()));
	}

	private class t1 implements Callable<Void> {

		/**
		 * @pre		-
		 * @post	Vérifie que l'étudiant utilise bien les getters 
		 *			pour récupérer les valeurs des variable d'instance.
		 */
		public Void call() {

			Rectangle r = new Rectangle(10,10);
			Rectangle spy = Mockito.spy(r);

			String msg = Translator.translate("{0} : vous devez utiliser les getters pour récupérer les valeurs des variables d''instances !");
			String feedback = MessageFormat.format(msg,test_name());

			try {
				double res = spy.surface();
				verify(spy,atLeast(1)).getLongueur();
				verify(spy,atLeast(1)).getLargeur();
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
			double lon = r.nextInt(100), larg = r.nextInt(100);

			Rectangle r1 = new Rectangle(0,0), r2 = new Rectangle(lon,larg);

			String msg = Translator.translate("{0} : lorsque l''on appelle votre méthode sur le rectangle {1}, elle renvoie {2} au lieu de {3}");
			String feed1 = MessageFormat.format(msg,test_name(),r1,r1.surface(),0);
			String feed2 = MessageFormat.format(msg,test_name(),r2,r2.surface(),lon*larg);

			assertTrue(feed1,r1.surface() == 0.0);
			assertTrue(feed2,r2.surface() == lon*larg);
			return null;
		}
	}


	public void catcher(Callable<Void> f) {
		try {
			f.call();
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
