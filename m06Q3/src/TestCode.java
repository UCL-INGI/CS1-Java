
/**
 *  Copyright (c)  2016 Ludovic Taffin
 *  refactor Dubray Alexandre 2017
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

import static org.hamcrest.CoreMatchers.is;

import java.util.Random;
import java.text.MessageFormat;

import StudentCode.Date;
import static student.Translations.Translator._;

import org.mockito.Mockito;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import org.mockito.exceptions.verification.WantedButNotInvoked;


public class TestCode {

	/**
	 * @pre -
	 * @post	Vérifie que l'étudiant utilise bien les getters pour récuperer
	 *			les valeurs des variables d'instance
	 */
	public void testGetters() {

		Date d1 = new Date(10,12,2017);
		Date d2 = new Date(10,12,2017);

		Date spy1 = Mockito.spy(d1);
		Date spy2 = Mockito.spy(d2);
		
		boolean res = spy1.identique(spy2);

		try {
			verify(spy1,atLeast(1)).getJour();
			verify(spy1,atLeast(1)).getMois();
			verify(spy1,atLeast(1)).getAnnee();
			verify(spy2,atLeast(1)).getJour();
			verify(spy2,atLeast(1)).getMois();
			verify(spy2,atLeast(1)).getAnnee();
		} catch (WantedButNotInvoked e) {
            fail(MessageFormat.format(_("{0} : vous devez utiliser les getters pour récupérer les valeurs des variables d''instances !"), ("Test 1")));
		}
	}

	@Test
	public void testLauncher() {
		try {
			testGetters();
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
