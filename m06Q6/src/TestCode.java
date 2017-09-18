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

import java.text.MessageFormat;
import java.util.concurrent.Callable;

import static student.Translations.Translator._;
import StudentCode.Fraction;

import org.mockito.Mockito;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import org.mockito.exceptions.verification.WantedButNotInvoked;

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
		 * @post	Vérifie que l'étudiant utilise bien les getters pour
		 *			récupérer les valeurs des variables d'instance.
		 */
		public Void call(){

			Fraction f = new Fraction(1,2);
			Fraction spy = Mockito.spy(f);

			boolean res = spy.entier();

			try {
				verify(spy,atLeast(1)).getNum();
				verify(spy,atLeast(1)).getDen();
				return null;
			} catch (WantedButNotInvoked e) {
				String feed = MessageFormat.format(_("{0} : vous devez utiliser les méthodes getNum() et getDen() pour récupérer les valeurs des variables d''instances !"), test_name());
				fail(feed);
				return null;
			}
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
	public void test_1(){
		catcher(new t1());
		printSucceed();
	}

}
