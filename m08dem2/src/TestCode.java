package src;
/**
 * @author Dubray Alexandre
 */

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.any;


import org.mockito.Mockito;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import org.mockito.exceptions.verification.WantedButNotInvoked;

import StudentCode.Etudiant;
import static student.Translations.Translator._;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.lang.StringBuffer;
import java.io.IOException;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({StringBuffer.class,Etudiant.class})
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
			e.printStackTrace();
		}catch(ClassCastException e){
			fail(_("Attention, certaines variables ont été mal castées !"));
			e.printStackTrace();
		}catch(StringIndexOutOfBoundsException e){
			e.printStackTrace();
			fail(_("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
			e.printStackTrace();
		}catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
			fail(_("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
			e.printStackTrace();
		}catch(NullPointerException e){
			fail(_("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
			e.printStackTrace();
		}catch(StackOverflowError e) {
			fail(_("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
			e.printStackTrace();
		}catch(Exception e){
			fail("\n" + e.toString());
			e.printStackTrace();
		}
	}	


	private class t1 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de l'étudiant gère bien
		 *			le cas où s == null
		 */
		public Void call() {
			String res = Etudiant.repeat(null,10);
			String feed = MessageFormat.format(_("{0} : attention, lorsque s vaut null, vous renvoyez {1} au lieu de null"),test_name(),res);
			assertThat(feed,res,nullValue());
			return null;
		}
	}

	private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de l'étudiant gère 
		 *			bien le cas où n == 0
		 */
		public Void call() {
			String res = Etudiant.repeat("Hello",0);
			String feed = MessageFormat.format(_("{0} : attention, lorsque n vaut 0, vous renvoyez {1} au lieu de null"),test_name(),res);
			assertThat(feed,res,nullValue());
			return null;
		}
	}

	private class t3 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de l'étudiant
		 *			renvoie le bon String
		 */
		public Void call() {
			String input = "ThisIsTheInputString";
			String res = Etudiant.repeat(input,10);
			StringBuffer strb = new StringBuffer("");
			for (int i =0;i<10;i++)
				strb.append(input);
			String feed;
			if(strb.toString().contains(res))
				feed = MessageFormat.format(_("{0} : votre code ne semble pas répéter assez de fois la chaîne de carctère, vérifiez votre condition!"),test_name());
			else
				feed = MessageFormat.format(_("{0} : votre code ne semble pas renvoyez un string contenant la chaîne de caractère passé en paramètre!"),test_name());
			if(!res.equals(strb.toString()))
				fail(feed);
			return null;
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

	@Test
	public void test_3(){
		catcher(new t3());
		printSucceed();
	}

}
