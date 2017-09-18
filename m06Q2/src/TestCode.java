
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
import org.junit.Rule;
import org.junit.rules.TestName;

import static org.hamcrest.CoreMatchers.is;

import java.util.Random;
import java.util.concurrent.Callable;
import java.text.MessageFormat;

import static student.Translations.Translator._;

import StudentCode.Date;

public class TestCode {

	@Rule
	public TestName name = new TestName();

	private void printSucceed(){ 
		System.err.println(MessageFormat.format(_("{0} : réussi"),test_name()));
	}

	private String test_name(){
		String s = name.getMethodName().replaceAll("_"," ");
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}
	
	Random r = new Random();


	private class t1 implements Callable<Void> {
		/**
		 * @pre -
		 * @post Vérifie la méthode getJour() de l'étudiant
		 */
		public Void call(){
			String msg = _("{0} : lorsque l''on fait getJour() sur la date {1}, votre code renvoie {2} au lieu de {3}");
			int jour = r.nextInt(31), mois = 10, annee = 2017;
			Date d = new Date(jour,mois,annee);
			String feedback = MessageFormat.format(msg,test_name(),d,d.getJour(),jour);
			assertThat(feedback,d.getJour(),is(jour));
			return null;
		}
	}

	private class t2 implements Callable<Void> {
		/**
		 * @pre -
		 * @post Vérifie la méthode getMois() de l'étudiant
		 */
		public Void call() {
			String msg = _("{0} : lorsque l''on fait getMois() sur la date {1}, votre code renvoie {2} au lieu de {3}");
			int jour = 10, mois = r.nextInt(13), annee = 2017;
			Date d = new Date(jour, mois,annee);
			String feedback = MessageFormat.format(msg,test_name(),d,d.getMois(),mois);
			assertThat(feedback,d.getMois(),is(mois));
			return null;
		}
	}


	private class t3 implements Callable<Void> {
		/**
		 * @pre -
		 * @post Vérifie la méthode getAnnee() de l'étudiant
		 */
		public Void call(){
			String msg = _("{0} : lorsque l''on fait getAnnee() sur la date {1}, votre code renvoie {2} au lieu de {3}");
			int jour = 10, mois = 2, annee = r.nextInt(2021);
			Date d = new Date(jour,mois,annee);
			String feedback = MessageFormat.format(msg,test_name(),d,d.getAnnee(),annee);
			assertThat(feedback,d.getAnnee(),is(annee));
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

	@Test
	public void test_3() {
		catcher(new t3());
		printSucceed();
	}
}
