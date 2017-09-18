package src;
/**
 * @author Dubray Alexandre
 */

import static org.junit.Assert.assertThat;
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

import StudentCode.CD;
import static student.Translations.Translator._;
import src.librairies.Inspector;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.lang.reflect.Modifier;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

public class TestInspectCD {

	@Rule
	public TestName name = new TestName();
	
	private void printSucceed() {
		System.err.println(MessageFormat.format(_("{0} : réussi"),test_name()));
	}

	private String test_name() {
		String s = name.getMethodName().replaceAll("_"," ");
		return s.substring(0,1).toUpperCase() + s.substring(1);
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
			e.printStackTrace();
            fail(_("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(NegativeArraySizeException e){
            fail(_("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(_("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(_("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
	}	

	private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le constructeur comme définis dans l'énoncé 
		 *			est présent dans la classe
		 */
		public Void call() {
			String [] errors_msg = Inspector.inspect_constructors(CD.class,new int [] {Modifier.PUBLIC},new Class [][]{{String.class,String.class,int.class}},null);
			String error_msg = "";
			for(String err : errors_msg){
				if(err != null)
					error_msg += err + "\n";
			}
			if(!error_msg.equals(""))
				fail(MessageFormat.format("@2 :\n{0} : {1}",test_name(),error_msg));
			return null;
		}
	}

	private class t3 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que la méthode toString est bien
		 *			dans la classe CD
		 */
		public Void call() {
			String [] errors_msg = Inspector.inspect_methods(CD.class,new int [] {Modifier.PUBLIC}, new Class [] {String.class},new String []{"toString"},new Class [][]{{}},null);
			String error_msg = "";
			for(String err : errors_msg) {
				if(err != null)
					error_msg += err + "\n";
			}
			if(!error_msg.equals(""))
				fail(MessageFormat.format("@2 :\n{0} : {1}",test_name(),error_msg));
			return null;
		}
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
