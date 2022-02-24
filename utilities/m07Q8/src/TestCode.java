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
import static org.mockito.Mockito.when;

import StudentCode.DeStats;
import student.Translations.Translator;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.Random;

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
		 * @post	Vérifie que le code de l'étudiant retourne
		 *			bien le chiffre attendu
		 */
		public Void call() {
			DeStats d = new DeStats("Dé");
			DeStats spy = Mockito.spy(d);
			int n = new Random().nextInt(5)+2;
			when(spy.realLance()).thenReturn(n);

			int res = spy.lance();
			String feed = MessageFormat.format(Translator.translate("{0} : votre code ne semble pas renvoyer le résultat donné par le dé"),test_name());
			assertThat(feed,res,is(n));
			
			when(spy.realLance()).thenReturn(1);
			res = spy.lance();
			assertThat(feed,res,is(1));
			return null;
		}
	}

	private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de l'étudiant incrémente
		 *			bien le nombre de lancés total
		 */
		public Void call() {
			DeStats d = new DeStats("Dé");
			d.setNbLances(254);
			int r = d.lance();
			String feed = MessageFormat.format(Translator.translate("{0} : votre code ne semble pas incrémenter correctement le nombre de lancers !"),test_name());
			if(d.getLances() != 255)
				fail(feed);
			return null;
		}
	}

	private class t3 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de l'étudiant incrémente
		 *			bien la case du tableau associés au résultat
		 */
		public Void call() {
			new t1().call();
			DeStats d = new DeStats("Dé");
			int [] t = new int [] {24,56,72,1,12,133};
			d.setRes(t);
			int r = d.lance();
			String feed = MessageFormat.format(Translator.translate("{0} : votre code ne semble pas incrémenter correctement le nombre de lancers pour un résultat particulier !"),test_name());
			if(d.getResultats()[r-1] != t[r-1]+1)
				fail(feed);
			return null;
		}
	}

	private class t4 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code l'étudiant fait
		 *			bien appel à la méthode de la classe mère
		 */
		public Void call() {
			DeStats d = new DeStats("Dé");
			DeStats spy = Mockito.spy(d);
			int res = spy.lance();
			try {
				verify(spy,atLeast(1)).realLance();
				return null;
			} catch( WantedButNotInvoked e) {
				String feed = MessageFormat.format(Translator.translate("{0} : votre code ne semble pas appeler la méthode lance() de la classe mère"),test_name());
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
	public void test_2(){
		catcher(new t2());
		printSucceed();
	}

	@Test
	public void test_3(){
		catcher(new t3());
		printSucceed();
	}

	@Test
	public void test_4(){
		catcher(new t4());
		printSucceed();
	}
}
