package src;
/**
 *  @author François MICHEL
 *  refactor 2017 by Alexandre Dubray
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

import StudentCode.Directeur;
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
		  * @pre	-
		  * @post	vérifie que l'étudiant utilise bien la méthode de
		  *			la classe mère pour récupérer le salaire de base.
		  */
		public Void call() {
			Directeur d = new Directeur("Jean",1200,0.5);
			Directeur spy = Mockito.spy(d);
			double sal = spy.getSalaire();
			try {
				verify(spy,atLeast(1)).getRealSalaire();
				return null;
			} catch (WantedButNotInvoked e) {
				String feed = MessageFormat.format(Translator.translate("{0} : vous devez faire appel à la méthode getSalaire() de la classe mère !"),test_name());
				fail(feed);
				return null;
			}
		}
	}

	private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	vérifie que la méthode de l'étudiant renvoie bien
		 *			le bon salaire.
		 */
		public Void call() {
			Directeur d1 = new Directeur("Jean",2000,0.0);
			Directeur d2 = new Directeur("Jacques",1500,0.5);
			Directeur d3 = new Directeur("Paul",2500,1.0);
			Directeur d4 = new Directeur("Hervé",1769,0.675);

			String msg = Translator.translate("{0} : Pour le directeur {1}, votre méthode renvoie {2,number,#} au lieu de {3,number,#}");
			assertThat(MessageFormat.format(msg,test_name(),d1,d1.getSalaire(),2000),d1.getSalaire(),is(2000.0));
			assertThat(MessageFormat.format(msg,test_name(),d2,d2.getSalaire(),2250),d2.getSalaire(),is(2250.0));
			assertThat(MessageFormat.format(msg,test_name(),d3,d3.getSalaire(),5000),d3.getSalaire(),is(5000.0));
			assertThat(MessageFormat.format(msg,test_name(),d4,d4.getSalaire(),1769*1.675),d4.getSalaire(),is(1769*1.675));
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
}
