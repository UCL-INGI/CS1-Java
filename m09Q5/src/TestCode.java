package src;
/**
 * @author Dubray Alexandre
 */

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Rule;
import org.junit.Before;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.hamcrest.CoreMatchers.is;

import org.mockito.Mockito;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.exceptions.verification.WantedButNotInvoked;

import StudentCode.Etudiant;
import student.Translations.Translator;
import src.librairies.FunctionHelper;

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
			e.printStackTrace();
            fail(Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(NegativeArraySizeException e){
            fail(Translator.translate("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(Translator.translate("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
	}	

	private void check_function() {
		try {
			FunctionHelper.check_etudiant_function("StudentCode.Etudiant","accessible",boolean.class,new Class[] {String.class});
		} catch(ClassNotFoundException e) {
			fail("Class not found");
		}
	}


	private class t1 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que quand le fichier n'existe pas,
		 *			renvoie false
		 */
		public Void call() {
			check_function();
			boolean res = Etudiant.accessible("./fileThatDoNotExist");
			String feed = MessageFormat.format(Translator.translate("{0} : lorsque le fichier n''existe pas, votre méthode devrait renvoyer false mais ce n''est pas le cas !"),test_name());
			assertTrue(feed,res == false);
			return null;
		}
	}

	private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que quand le fichier existe, 
		 *			la méthode renvoie true
		 */
		public Void call() {
			check_function();
			boolean res = Etudiant.accessible("./file");
			String feed = MessageFormat.format(Translator.translate("{0} : lorsque le fichier existe et est accessible, votre méthode devrait renvoyer true mais ce n''est pas le cas !"),test_name());
			assertTrue(feed,res == true);
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
