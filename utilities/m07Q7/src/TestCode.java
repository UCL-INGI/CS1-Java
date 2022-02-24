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
import static org.hamcrest.CoreMatchers.notNullValue;

import org.mockito.Mockito;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import org.mockito.exceptions.verification.WantedButNotInvoked;

import StudentCode.DeStats;
import student.Translations.Translator;

import java.text.MessageFormat;
import java.util.concurrent.Callable;

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
		 * @post	Vérifie que le code de l'étudiant instantie un
		 *			DeStats avec le bon nom
		 */
		public Void call() {
			DeStats d1 = new DeStats("Dé");
			DeStats d2 = new DeStats("éD");
			String feed = MessageFormat.format(Translator.translate("{0} : votre code ne semble pas initialiser un object DeStat avec le bon nom !"),test_name());
			assertThat(feed,d1.getNom(),is("Dé"));
			assertThat(feed,d2.getNom(),is("éD"));
			return null;
		}
	}

	private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de l'étudiant instantie un
		 *			DeStats avec le bon nombre de lancés totaux
		 */
		public Void call() {
			DeStats d = new DeStats("Dé");
			String feed = MessageFormat.format(Translator.translate("{0} : votre code ne semble pas initialiser un object DeStats avec le bon nombre de lancers totaux !"),test_name());
			if(d.getLances() != 0)
				fail(feed);
			return null;
		}
	}

	private class t3 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de l'étudiant initialise bien
		 *			le tableau de résultats
		 */
		public Void call() {
			DeStats d = new DeStats("Dé");
			String feed = MessageFormat.format(Translator.translate("{0} : votre code ne semble pas initialiser le tableau de résultats (il est null) !"),test_name());
			assertThat(feed,d.getResultats(),notNullValue());
			return null;
		}
	}

	private class t4 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de l'étudiant initialise
		 *			le tableau de résultats avec la bonne taille
		 */
		public Void call() {
			new t3().call();
			DeStats d = new DeStats("Dé");
			String feed = MessageFormat.format(Translator.translate("{0} : votre code ne semble pas initialiser le tableau de résultats avec la bonne taille !"),test_name());
			if(d.getResultats().length != 6)
				fail(feed);
			return null;
		}
	}

	private class t5 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de l'étudiant initialise
		 *			le tableau de résultats avec les bonnes valeurs
		 */
		public Void call() {
			new t4().call();
			DeStats d = new DeStats("Dé");
			String feed = MessageFormat.format(Translator.translate("{0} : votre code ne semble pas initialiser le tableau de résultats avec les bonnes valeurs !"),test_name());
			for(int i : d.getResultats()) {
				if(i != 0)
					fail(feed);
			}
			return null;
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

	@Test
	public void test_4() {
		catcher(new t4());
		printSucceed();
	}

	@Test
	public void test_5() {
		catcher(new t5());
		printSucceed();
	}
}
