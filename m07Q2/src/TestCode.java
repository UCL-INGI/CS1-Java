package src;
/**
 *  @author François MICHEL
 *  refactor 2017 by Alexandre Dubray
 */

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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

import StudentCode.Employe;
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
	

	private class t1 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de l'étudiant renvoie bien
		 *			un string contenant le nom de l'employé
		 */
		public Void call() {
			Employe e = new Employe("Francis",2000);
			String s = e.toString();
			String feed = MessageFormat.format(Translator.translate("{0} : votre code ne semble pas renvoyé un String contenant le nom de l''employé !"),test_name());
			assertTrue(feed,s.contains("Francis"));
			return null;
		}
	}

	private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de l'étudiant renvoie bien
		 *			un string contenant le salaire de l'employé
		 */
		public Void call() {
			Employe e = new Employe("Francis",2000);
			String 	s = e.toString();
			String feed = MessageFormat.format(Translator.translate("{0} : votre code ne semble pas renvoyé un String contenant le salaire de l''employé !"),test_name());
			assertTrue(feed,s.contains("2000"));
			return null;
		}
	}

	private class t3 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le l'étudiant utilise les getters pour
		 *			récupérer les valeurs des variables d'instances.
		 */
		public Void call() {
			Employe e = new Employe("Eliot",1876);
			Employe spy = Mockito.spy(e);
			String s = spy.toString();
			try {
				verify(spy,atLeast(1)).getNom();
				verify(spy,atLeast(1)).getSalaire();
				return null;
			} catch( WantedButNotInvoked t) {
				fail(MessageFormat.format(Translator.translate("{0} : vous devez utiliser les getters pour récupérer les valeurs des variables d''instances !"),test_name()));
				return null;
			}
		}
	}
    
    private class t4 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le l'étudiant utilise les getters pour
		 *			récupérer les valeurs des variables d'instances.
		 */
		public Void call() {
			Employe e = new Employe("Francis",2000);
			String 	s = e.toString();
            String correctionString = "Francis 2000.0";
			String feed = MessageFormat.format(Translator.translate("{0} : votre code ne semble pas renvoyé un String correcte !\n"
                                                                   ) + "Expected : {1}\nAu lieu de : {2}",test_name(), correctionString, s);
			assertTrue(feed,s.equals(correctionString));
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
}
