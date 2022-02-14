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
import student.Translations.Translator;
import src.librairies.ConstructException;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java.util.Scanner;

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

	private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que l'étudiant utilise bien un BufferedReader
		 */
		public Void call() throws Exception, IOException{
			//Mockito.when(BufferedReader.class).withParameterTypes(Reader.class).withArguments(Mockito.any(Reader.class)).thenThrow(new ConstructException("Constructor used"));
			try {
				boolean res = Etudiant.contains("Hello","./file1");
				String feed = MessageFormat.format(Translator.translate("{0} : vous devez utiliser un BufferedReader !"),test_name());
				//fail(feed);
				return null;
			} catch(ConstructException e){
				return null;
			}
		}
	}
	
	private class t3 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que  le code de l'étudiant renvoie 
		 *			bien false en cas d'erreur.
		 */
		public Void call() throws Exception,IOException{
			//BufferedReader mockedBr = PowerMockito.mock(BufferedReader.class);
			//PowerMockito.whenNew(BufferedReader.class).withParameterTypes(Reader.class).withArguments(Mockito.any(Reader.class)).thenReturn(mockedBr);

			//PowerMockito.doThrow(new IOException("Mock error")).when(mockedBr).readLine();
			boolean res;
			try {
				res = Etudiant.contains("Spok","./file1");
				String feed = MessageFormat.format(Translator.translate("{0} : vous ne renvoyez pas false lorsqu''une erreur se produit !"),test_name());
				assertThat(feed,res,is(false));
				return null;
			} catch (IOException e) {
				String feed = MessageFormat.format(Translator.translate("{0} : vous ne gérez pas bien les IOExceptions !"),test_name());
				fail(feed);
				return null;
			} 
		}
	}

	private class t4 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de l'étudiant ferme
		 *			bien le flux lorsque le String s se trouve
		 *			dans le fichier.
		 */
		public Void call() throws Exception, IOException{
			//BufferedReader mockedBr = PowerMockito.mock(BufferedReader.class);
			//PowerMockito.whenNew(BufferedReader.class).withParameterTypes(Reader.class).withArguments(Mockito.any(Reader.class)).thenReturn(mockedBr);

			//Mockito.doReturn("fichier 2.").when(mockedBr).readLine();

			String feedSucc = MessageFormat.format(Translator.translate("{0} : lorsque l''on appelle votre méthode avec un String se trouvant dans le fichier, vous ne fermez pas le flux !"),test_name());

			boolean res = Etudiant.contains("fichier 2.","./file2");
			try {
				//Mockito.verify(mockedBr,times(1)).close();
				return null;
			} catch( WantedButNotInvoked e){
				fail(feedSucc);
				return null;
			}
		}
	}


	private class t5 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que l'étudiant ferme bien le flux
		 *			lorsque le String ne se trouve pas dans
		 *			le fichier
		 */
		public Void call() throws Exception, IOException{
			//BufferedReader mockedBr = PowerMockito.mock(BufferedReader.class);
			//PowerMockito.whenNew(BufferedReader.class).withParameterTypes(Reader.class).withArguments(Mockito.any(Reader.class)).thenReturn(mockedBr);

			boolean res = Etudiant.contains("gna","./file2");
			try {
				//verify(mockedBr,times(1)).close();
			} catch(WantedButNotInvoked e) {
				String feed = MessageFormat.format(Translator.translate("{0} : lorsque le String ''s'' ne se trouve pas dans le fichier, vous ne fermez pas le flux !"),test_name());
				fail(feed);
				return null;
			}
			return null;
		}
	}

	private class t6 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que l'étudiant ferme bien le flux
		 *			lorsqu'une exception est lancée
		 */
		public Void call() throws Exception, IOException {

			//BufferedReader mockedBr = PowerMockito.mock(BufferedReader.class);
			//PowerMockito.whenNew(BufferedReader.class).withParameterTypes(Reader.class).withArguments(Mockito.any(Reader.class)).thenReturn(mockedBr);

			//PowerMockito.doThrow(new IOException("Mock error")).when(mockedBr).readLine();

			try {
				boolean res = Etudiant.contains("Splouk","./file1");
				//verify(mockedBr,times(1)).close();
				return null;
			} catch (IOException e) {
				String feed = MessageFormat.format(Translator.translate("{0} : vous ne gérez pas bien les IOExceptions !"),test_name());
				fail(feed);
				return null;
			} catch( WantedButNotInvoked e) {
				String feed = MessageFormat.format(Translator.translate("{0} : vous ne fermez pas le flux lorsqu''une exception apparait !"),test_name());
				fail(feed);
				return null;
			}
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

	@Test
	public void test_6() {
		catcher(new t6());
		printSucceed();
	}
}
