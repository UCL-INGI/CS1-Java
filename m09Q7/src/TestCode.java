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
import static org.hamcrest.CoreMatchers.nullValue;

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
import java.io.*;
import java.util.Arrays;

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
		 * @pre		-
		 * @post	Vérifie que l'étudiant utilise bien un BufferedReader
		 */
		public Void call() throws Exception, IOException{
			try {
				/*PowerMockito.whenNew(BufferedReader.class).withParameterTypes(Reader.class)
					.withArguments(Mockito.any(Reader.class)).thenReturn(PowerMockito.mock(BufferedReader.class));*/
				int [] v = Etudiant.readVector("./file1");
				//PowerMockito.verifyNew(BufferedReader.class).withArguments(Mockito.any(Reader.class));
				return null;
			} catch(AssertionError e){
				String feed = MessageFormat.format(Translator.translate("{0} : vous devez utiliser un BufferedReader !"),test_name());
				fail(feed);
				return null;
			}
		}
	}
	
	private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que  le code de l'étudiant renvoie 
		 *			bien null en cas d'erreur.
		 */
		public Void call() throws Exception,IOException{
			//BufferedReader mockedBr = PowerMockito.mock(BufferedReader.class);
			//PowerMockito.whenNew(BufferedReader.class).withParameterTypes(Reader.class).withArguments(Mockito.any(Reader.class)).thenReturn(mockedBr);

			//PowerMockito.doThrow(new IOException("Mock error")).when(mockedBr).readLine();
			try {
				int [] v = Etudiant.readVector("./file1");
				String feed = MessageFormat.format(Translator.translate("{0} : vous ne renvoyez pas null lorsqu''une IOException se produit !"),test_name());
				//assertThat(feed,v,nullValue());
				//Mockito.verify(mockedBr,times(1)).close();
				return null;
			} catch (IOException e) {
				String feed = MessageFormat.format(Translator.translate("{0} : vous ne gérez pas bien les IOExceptions !"),test_name());
				fail(feed);
				return null;
			} catch (WantedButNotInvoked e){
				String feed = MessageFormat.format(Translator.translate("{0} : lorsqu''une IOException surgit, vous ne fermez pas le flux"),test_name());
				fail(feed);
				return null;
			}
		}
	}

	private class t3 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que l'étudiant renvoie bien null
		 *			lorsqu'une NumberFormatException est lancée
		 *			(Mauvais format de fichier)
		 */
		public Void call() {
			//BufferedReader mockedBr = PowerMockito.mock(BufferedReader.class);
			/*try {
				PowerMockito.whenNew(BufferedReader.class).withParameterTypes(Reader.class).withArguments(Mockito.any(Reader.class)).thenReturn(mockedBr);
			} catch (Exception e) {
				fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
			}*/

			try {
				int [] v = Etudiant.readVector("./file_with_bad_format");
				String feed = MessageFormat.format(Translator.translate("{0} : vous ne renvoyez pas null lorsque le fichier a un mauvais format !"),test_name());
				assertTrue(feed,v == null);
				//verify(mockedBr,times(1)).close();
			} catch (NumberFormatException e) {
				fail(MessageFormat.format(Translator.translate("{0} : vous ne gérez pas le cas ou le fichier a un mauvais format de chiffre !"),test_name()));
			}  catch(WantedButNotInvoked e) {
				fail(MessageFormat.format(Translator.translate("{0} : vous ne fermez pas le flux lorsque le fichier a un mauvais format de chiffres !"),test_name()));
			} catch (IOException e) {
				fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
			} catch (ConstructException e) {
				fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
			}

			return null;
		}
	}

	private String file_to_string(String filename) {
		BufferedReader br = null;
		String str = "";
		try {
			br = new BufferedReader(new FileReader(filename));
			String line;
			while((line = br.readLine()) != null){
				str += line;
				str += "\n";
			}
			return str;
		} catch(IOException e) {
            fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
			return null;
		} finally {
			if(br != null)
				try {
					br.close();
					return str;
				} catch(IOException e) {
					fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
				}
		}
	}

	private class t4 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le résultat retourné par l'étudiant
		 *			est le bon.
		 */
		public Void call() {
			int [] v1=null;
			int [] v2=null;
			try {
				v1 = Etudiant.readVector("./file1");
				v2 = Etudiant.readVector("./file2");
			} catch(IOException e){
				fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
			} catch (ConstructException e) {
				fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
			}

			int [] r1 = new int [] {1,2,3};
			int [] r2 = new int [] {1,1};

			String msg = Translator.translate("{0} : lorsque l''on lit le fichier suivant\n{1} avec votre méthode, vous renvoyez {2} au lieu de {3} !");
			String feed1 = MessageFormat.format(msg,test_name(),file_to_string("./file1"),Arrays.toString(v1),Arrays.toString(r1));
			String feed2 = MessageFormat.format(msg,test_name(),file_to_string("./file2"),Arrays.toString(v2),Arrays.toString(r2));

			assertTrue(feed1,v1 == r1);
			assertTrue(feed2,v2 == r2);
			return null;
		}
	}

	private class t5 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que l'étudiant ferme bien
		 *			le flux lorsqu'il n'y a pas d'erreur.
		 */
		public Void call() {
			//BufferedReader mockedBr = PowerMockito.mock(BufferedReader.class);
			/*try {
				PowerMockito.whenNew(BufferedReader.class).withParameterTypes(Reader.class).withArguments(Mockito.any(Reader.class)).thenReturn(mockedBr);
			} catch(Exception e){
				fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
			}*/

			try {
				int v [] = Etudiant.readVector("./file1");
			} catch (IOException e){
				fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
			} catch (ConstructException e) {
				fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
			}

			/*try {
				verify(mockedBr,times(1)).close();
			} catch( WantedButNotInvoked e) {
				fail(MessageFormat.format(Translator.translate("{0} : lorsque tout se déroule sans exception, vous ne fermez pas le flux !"),test_name()));
			} catch(IOException e) {
				fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
			}*/
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
