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

import StudentCode.StringTab;
import static student.Translations.Translator._;
import src.librairies.MyString;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.Arrays;
import java.util.Random;

public class TestCode{

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
            fail(_("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(NegativeArraySizeException e){
            fail(_("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(_("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(_("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
	}	

	/**
	 * @pre		-
	 * @post	Crée un tableau de taille aléatoire entre 0 et 10
	 *			comprenant des caractère aléatoire entre 'a' et 'z'
	 */
	private char [] randomCharArray() {
		Random r = new Random();
		char [] tab = new char[r.nextInt(11)];
		for(int i = 0;i<tab.length;i++) {
			int n = r.nextInt(26)+97;
			tab[i] = (char)n;
		}
		return tab;
	}

	/**
	 * @pre		-
	 * @post	Renvoie un caractère aléatoire entre 'a' et 'z'
	 */
	private char randomChar() {
		Random r = new Random();
		int i = r.nextInt(26)+97;
		return (char)i;
	}

	private class t1 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie le code de l'étudiant
		 */
		public Void call() {
			char [] randomS = randomCharArray();
			char randomC = randomChar();
			char [] res = new char[randomS.length+1];
			for(int i=0;i<randomS.length;i++)
				res[i] = randomS[i];
			res[res.length-1] = randomC;

			StringTab s = new StringTab(randomS);
			StringTab sConcat = (StringTab)s.concat(randomC);
			String msg = _("{0} : lorsque l''on concatène {1} avec {2}, votre code renvoie {2}");
			String feed = MessageFormat.format(msg,test_name(),randomC,Arrays.toString(randomS),Arrays.toString(sConcat.getS()));
			assertThat(feed,Arrays.equals(res,sConcat.getS()),is(true));
			return null;
		}
	}

	@Test
	public void test_1(){
		catcher(new t1());
		printSucceed();
	}
}
