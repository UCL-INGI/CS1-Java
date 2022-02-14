
/**
 *  Copyright (c)  2016 Ludovic Taffin
 *  refactor 2017 Dubray Alexandre
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
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;

import student.Translations.Translator;
import StudentCode.Date;

import java.util.Random;
import java.text.MessageFormat;

public class TestCode {
	
	/**
	 * @pre -
	 * @post Vérifie que l'étudiant met correctement la date à jour
	 */
	public void testDate() {

		String msg = Translator.translate("lorsque on fait new Date({0},{1},{2}), votre date est {3}/{4}/{5}");
		
		Random r = new Random();
		int jour = r.nextInt(32);
		int mois = r.nextInt(11) + 2;
		int année = r.nextInt(2020);

		Date date = new Date(jour,mois,année);
		

		String feedback = MessageFormat.format(msg,jour,mois,année,date.getJour(),date.getMois(),date.getAnnee());
		assertThat(feedback,date.getJour(),is(jour));
		assertThat(feedback,date.getMois(),is(mois));
		assertThat(feedback,date.getAnnee(),is(année));
	}

	@Test
	public void testLauncher() {
		try {
			testDate();
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
