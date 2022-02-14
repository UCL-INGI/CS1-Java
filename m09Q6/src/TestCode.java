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

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.Arrays;
import java.io.*;

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

	private class t1 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de l'étudiant
		 *			gère bien les exceptions
		 */
		public Void call() {
			try {
				//PowerMockito.whenNew(PrintWriter.class).withParameterTypes(Writer.class).withArguments(Mockito.any(Writer.class)).thenThrow(new IOException("Mock error"));
				Etudiant.saveVector(new int [] {1,2,3},"./tmp_file_test1");
			} catch(IOException e) {
				String feed = MessageFormat.format(Translator.translate("{0} : vous ne gérez pas les exceptions !"),test_name());
				fail(feed);
			} catch (Exception e) {
				fail(e.toString());
			} finally {
				return null;
			}
		}
	}

	private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que l'étudiant ferme bien
		 *			le flux.
		 */
		public Void call() {
			PrintWriter MockedPw = null;
			BufferedWriter MockedBw = null;
			try {
				/* Mocking des différents streams d'écriture */
				//MockedPw = PowerMockito.mock(PrintWriter.class);
				//MockedBw = PowerMockito.mock(BufferedWriter.class);
				//PowerMockito.whenNew(PrintWriter.class).withParameterTypes(Writer.class).withArguments(Mockito.any(Writer.class)).thenReturn(MockedPw);
				//PowerMockito.whenNew(BufferedWriter.class).withParameterTypes(Writer.class).withArguments(Mockito.any(Writer.class)).thenReturn(MockedBw);
			} catch (Exception e) {
				fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
			}

			try {
				Etudiant.saveVector(new int [] {1,2}, "./tmp_file_test2");
			} catch (IOException e) {
				fail(MessageFormat.format(Translator.translate("{0} : vous ne gérez pas les exceptions !"),test_name()));
			}
			String feed = MessageFormat.format(Translator.translate("{0} : vous ne faîtes pas appel à close() !"),test_name());
			try {
				//verify(MockedPw,times(1)).close();
			} catch(WantedButNotInvoked e) {
				try {
					//verify(MockedBw,times(1)).close();
				} catch ( WantedButNotInvoked t) {
					fail(feed);
				} /*catch (IOException g) {
					fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + g.toString());
				}*/
			} 

			return null;
		}
	}

	private boolean compareFile(String file1, String file2) {
		int res = -1;
		try {
			Process proc = Runtime.getRuntime().exec("./compare.sh "+file1+" "+file2);
			res = proc.waitFor();
		} catch( IOException e) {
            fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
		} catch( InterruptedException e) {
            fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
		}
		return (res == 0) ? true : false;
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

	private class t3 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de l'étudiant
		 *			écrit le bon contenu
		 */
		public Void call() {
			int [] v1 = new int [] {1,2,3};
			int [] v2 = new int [] {41,3,7,11};

			String file1 = "./student_rep_1";
			String file2 = "./student_rep_2";

			String rep1 = "./sol_1";
			String rep2 = "./sol_2";

			String msg = Translator.translate("{0} : lorsque l''on exécute votre code avec le tableau {1}\nle contenu de votre fichier est\n{2}\nau lieu de \n{3}");

			try {
				Etudiant.saveVector(v1,file1);
				Etudiant.saveVector(v2,file2);
			} catch( IOException e) {
					fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
			}

			String feed1 = MessageFormat.format(msg,test_name(),Arrays.toString(v1),file_to_string(file1),file_to_string(rep1));
			String feed2 = MessageFormat.format(msg,test_name(),Arrays.toString(v2),file_to_string(file2),file_to_string(rep2));

			boolean res1 = compareFile(file1,rep1);
			boolean res2 = compareFile(file2,rep2);

			assertThat(feed1,res1,is(true));
			assertThat(feed2,res2,is(true));
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
}
