
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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;

import static org.hamcrest.CoreMatchers.is;

import java.util.Random;
import java.text.MessageFormat;
import java.util.concurrent.Callable;

import StudentCode.Fraction;
import student.Translations.Translator;

public class TestCode {

	@Rule
	public TestName name = new TestName();

	private void printSucceed(){
		System.err.println(MessageFormat.format(Translator.translate("{0} : réussi\n"),test_name()));
	}

	private String test_name(){
		String s = name.getMethodName().replaceAll("_"," ");
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}
	
	Random r = new Random();


	private class t1 implements Callable<Void>{
		/**
		 * @pre	-
		 * @post	Vérifie la méthode getNum() de l'étudiant
		 */
		public Void call(){
			int n = r.nextInt(100), d = r.nextInt(100);
			Fraction f = new Fraction(n,d);
			String msg = Translator.translate("{0} : lorsque la fraction vaut {1}, votre méthode getNum() retourne {2} au lieu de {3}");
			String feedback = MessageFormat.format(msg,test_name(),f,f.getNum(),n);
			assertTrue(feedback,f.getNum() == n);
			return null;
		}
	}

	private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie la méthode getDen() de l'étudiant
		 */
		public Void call(){
			int n = r.nextInt(100), d = r.nextInt(100);
			Fraction f = new Fraction(n,d);
			String msg = Translator.translate("Test : lorsque la fraction vaut {0}, votre méthode getDen() retourne {1} au lieu de {2}");
			String feedback = MessageFormat.format(msg,f,f.getDen(),d);
			assertTrue(feedback,f.getDen() == d);
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
	public void test_1(){
		catcher(new t1());
		printSucceed();
	}

	@Test
	public void test_2(){
		catcher(new t2());
		printSucceed();
	}
}
