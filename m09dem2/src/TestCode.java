package src;
/**
 * @author Dubray Alexandre
 */

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.rules.TestName;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.hamcrest.CoreMatchers.is;

import org.mockito.Mockito;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import org.mockito.exceptions.verification.WantedButNotInvoked;

import StudentCode.Etudiant;
import student.Translations.Translator;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.io.File;
import java.io.IOException;
import java.lang.Runtime;
import java.lang.Process;
import java.io.FileNotFoundException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
			e.printStackTrace();
            fail(Translator.translate("Attention, il est interdit de diviser par zéro."));
		}catch(ClassCastException e){
			e.printStackTrace();
            fail(Translator.translate("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
			e.printStackTrace();
            fail(Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
            fail(Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
			e.printStackTrace();
            fail(Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(NegativeArraySizeException e){
			e.printStackTrace();
            fail(Translator.translate("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
			e.printStackTrace();
            fail(Translator.translate("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
			e.printStackTrace();
            fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
	}	

	private class t1 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de l'étudiant gère bien le cas d'un IOException
		 */
		public Void call() throws Exception {
			//Mockito.mock(File.class);
			//Mockito.when(new File()).withParameterTypes(String.class).withArguments(Mockito.anyString()).thenThrow(new IOException());
			try {
				int res =Etudiant.premierPrenom("../student/pasBonFichier.txt");
				if(res != -1) {
					String feed = MessageFormat.format(Translator.translate("{0} : attention, lorsqu''une exception est lancée, votre code doit renvoyer -1 !"),test_name());
					fail(feed);
				}
				return null;
			} catch( IOException e) {
				String feed = MessageFormat.format(Translator.translate("{0} : attention, vous ne gérer pas les exceptions !"),test_name());
				fail(feed);
				return null;
			}
		}
	}
    
    private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de Scanner scanner = new Scanner( new File("poem.txt") );
String text = scanner.useDelimiter("\\A").next();
scanner.close();l'étudiant gère bien le cas d'un IOException
		 */
		public Void call() throws Exception {
			//Mockito.mock(File.class);
			//Mockito.when(new File()).withParameterTypes(String.class).withArguments(Mockito.anyString()).thenThrow(new IOException());
			try {
				int res =Etudiant.premierPrenom("../student/noms.txt");
				if(res != 0) {
					String feed = MessageFormat.format(Translator.translate("{0} : attention, aucune exception ne devait être lancée, votre code doit renvoyer 0!"),test_name());
					fail(feed);
				}
                
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                // IMPORTANT: Save the old System.out!
                PrintStream old = System.out;
                // Tell Java to use your special stream
                System.setOut(ps);
                Etudiant.premierPrenom("../student/noms.txt");
                System.out.flush();
				System.setOut(old);
                
                ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                PrintStream ps2 = new PrintStream(baos2);
                // IMPORTANT: Save the old System.out!
                old = System.out;
                // Tell Java to use your special stream
                System.setOut(ps2);
                Correction.premierPrenom("../student/noms.txt");
                System.out.flush();
				System.setOut(old);
                
                String feed = MessageFormat.format(Translator.translate("{0} : votre code affiche :\n{1}\nOr, on attendait :\n{2}"),test_name(),baos.toString(),baos2.toString());
                assertTrue(feed, baos2.toString().equals(baos.toString()));
                
				return null;
			} catch( IOException e) {
				String feed = MessageFormat.format(Translator.translate("{0} : attention, vous ne gérer pas les exceptions !"),test_name());
				fail(feed);
				return null;
			}
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
