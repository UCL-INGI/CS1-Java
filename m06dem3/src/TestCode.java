package src;
/**
 *  @author François MICHEL
 *  refactor 2017 by Alexandre Dubray
 */

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

import StudentCode.Drapeau;
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
		 * @post	Vérifie si l'étudiant utilise bien la variable d'instance pour 
		 *			récupérer la valeur de la variable d'instance
		 */
		public Void call() {
			Drapeau d1 = new Drapeau(true);
			Drapeau d2 = new Drapeau(true);

			Drapeau spy = Mockito.spy(d1);
			Drapeau spy2 = Mockito.spy(d2);

			spy.same(spy2);

			try {
				verify(spy,atLeast(1)).get();
				verify(spy2,atLeast(1)).get();
				return null;
			} catch (WantedButNotInvoked e) {
				String feed = MessageFormat.format(Translator.translate("{0} : Vous devez utiliser la méthode get() pour récupérer la valeur du drapeau !"),test_name());
				fail(feed);
				return null;
			}
		}
	}
	

	private class t2 implements Callable<Void> {
		/**
		 * 	@pre	-
		 * 	@post	Teste le code de l'étudiant : vérifie si la valeur de retour est correcte.
		 * 			Lance une AssertionError lorsqu'une réponse est incorrecte.
		 */
		public Void call(){
			String msg = Translator.translate("{0} : lorsque ''d'' a l''état {1} et que la variable drapeau vaut {2}, votre méthode devrait retourner {3} mais ce n''est pas le cas.");
			Drapeau d = new Drapeau(true);
			Drapeau d2 = new Drapeau(true);
			String feed1 = MessageFormat.format(msg,test_name(),"true","true","true");
			assertTrue(feed1, d2.same(d) == true);

			String feed2 = MessageFormat.format(msg,test_name(),"false","true","false");
			d.set(false);
			assertTrue(feed2, d2.same(d) == false);

			String feed3 = MessageFormat.format(msg,test_name(),"false","false","true");
			d2.set(false);
			assertTrue(feed3, d2.same(d) == true);

			String feed4 = MessageFormat.format(msg,test_name(),"true","false","false");
			d.set(true);
			assertTrue(feed4, d2.same(d) == false);
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
