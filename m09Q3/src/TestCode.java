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

import StudentCode.Etudiant;
import static student.Translations.Translator._;
import src.librairies.ConstructException;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java.util.Scanner;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Etudiant.class)
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

	private class t1 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que l'étudiant utilise bien un BufferedReader
		 */
		public Void call() throws Exception, IOException{
			PowerMockito.whenNew(BufferedReader.class).withParameterTypes(Reader.class).withArguments(Mockito.any(Reader.class)).thenThrow(new ConstructException("Constructor used"));
			try {
				int count = Etudiant.countLines("./file1");
				String feed = MessageFormat.format(_("{0} : vous devez utiliser un BufferedReader !"),test_name());
				fail(feed);
				return null;
			} catch(ConstructException e){
				return null;
			}
		}
	}
	
	private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de l'étudiant utilise bien close()
		 */
		public Void call() throws Exception, IOException{
			BufferedReader mockedBr = PowerMockito.mock(BufferedReader.class);
			PowerMockito.whenNew(BufferedReader.class).withParameterTypes(Reader.class).withArguments(Mockito.any(Reader.class)).thenReturn(mockedBr);
			int count = Etudiant.countLines("./file1");
			try {
				Mockito.verify(mockedBr,times(1)).close();
				return null;
			} catch( WantedButNotInvoked e){
				String feed = MessageFormat.format(_("{0} : vous ne faîtes pas appel à close lorsque la méthode se termine sans erreur !"),test_name());
				fail(feed);
				return null;
			}
		}
	}

	private class t3 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que l'étudiant compte le bon nombre
		 *			de ligne
		 */
		public Void call() throws ConstructException,IOException {
			int count1 = Etudiant.countLines("./file1");
			int count2 = Etudiant.countLines("./file2");
			String feed = MessageFormat.format(_("{0} : votre code ne compte pas le bon nombre de lignes !"),test_name());
			if(count1 != 79)
				fail(feed);
			if(count2 != 5)
				fail(feed);
			return null;
		}
	}

	private class t4 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que  le code de l'étudiant renvoie 
		 *			bien -1.
		 */
		public Void call() throws Exception,IOException{
			BufferedReader mockedBr = PowerMockito.mock(BufferedReader.class);
			PowerMockito.whenNew(BufferedReader.class).withParameterTypes(Reader.class).withArguments(Mockito.any(Reader.class)).thenReturn(mockedBr);
			PowerMockito.doThrow(new IOException("Mock error")).when(mockedBr).readLine();

			int count = 0;
			try {
				count = Etudiant.countLines("./file1");
				String feed = MessageFormat.format(_("{0} : vous ne renvoyez pas -1 lorsqu''une erreur se produit !"),test_name());
				assertThat(feed,count,is(-1));
				return null;
			} catch (IOException e) {
				String feed = MessageFormat.format(_("{0} : vous ne gérez pas bien les IOExceptions !"),test_name());
				fail(feed);
				return null;
			} 
		}
	}

	private class t5 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que l'étudiant ferme bien le flux
		 *			lorsqu'une exception est lancée
		 */
		public Void call() throws Exception, IOException {
			BufferedReader mockedBr = PowerMockito.mock(BufferedReader.class);
			PowerMockito.whenNew(BufferedReader.class).withParameterTypes(Reader.class).withArguments(Mockito.any(Reader.class)).thenReturn(mockedBr);

			PowerMockito.doThrow(new IOException("Mock error")).when(mockedBr).readLine();

			try {
				int res = Etudiant.countLines("./file1");
				verify(mockedBr,times(1)).close();
				return null;
			} catch (IOException e) {
				String feed = MessageFormat.format(_("{0} : vous ne gérez pas bien les IOExceptions !"),test_name());
				fail(feed);
				return null;
			} catch( WantedButNotInvoked e) {
				String feed = MessageFormat.format(_("{0} : vous ne fermez pas le flux lorsqu''une exception apparait !"),test_name());
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
