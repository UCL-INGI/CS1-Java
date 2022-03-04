package src;
/**
 * @author Dubray Alexandre
 */

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.hamcrest.CoreMatchers.is;

import org.mockito.Mockito;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import org.mockito.exceptions.verification.WantedButNotInvoked;

import StudentCode.DeStats;
import student.Translations.Translator;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.Arrays;

public class TestCode{

	@Rule
	public TestName name = new TestName();

	private void printSucceed() {
		System.err.println(MessageFormat.format(Translator.translate("{0} : réussi"),test_name()));
	}

	private String test_name() {
		String s = name.getMethodName().replaceAll("_"," ");
		return s.substring(0,1).toUpperCase() + s.substring(1);
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

	private class t1 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que la méthode toString() de
		 *			l'étudiant renvoie le String attendu.
		 */
		public Void call() {
			DeStats d = new DeStats("Dé");
			int [] t = new int[] {123,111,2,343,777,854};
			d.setNbLances(2210);
			d.setRes(t);
			String s = d.toString();
			String res = "Dé 2210 "+Arrays.toString(d.getResultats());
			String feed = MessageFormat.format(Translator.translate("{0} : votre code ne semble pas renvoyez le bon String. Vous renvoyez\n''{1}''\n à la place de\n''{2}''"),test_name(),s,res);
			if(!s.equals(res))
				fail(feed);
			return null;
		}
	}

	private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que l'étudiant utilise bien la
		 *			méthode toString() de la classe mère.
		 */
		public Void call() {
			DeStats d = new DeStats("Dé");
			DeStats spy = Mockito.spy(d);
			String s = spy.toString();
			try {
				verify(spy,atLeast(1)).toRealString();
				return null;
			} catch(WantedButNotInvoked e) {
				String feed = MessageFormat.format(Translator.translate("{0} : votre code ne semble pas utiliser la méthode toString() de la classe mère"),test_name());
				fail(feed);
				return null;
			}
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
