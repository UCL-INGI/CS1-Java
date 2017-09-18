package src;
/**
 * @author Dubray Alexandre
 */

import static org.junit.Assert.assertThat;
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
import static student.Translations.Translator._;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.io.File;
import java.io.IOException;
import java.lang.Runtime;
import java.lang.Process;
import java.io.PrintStream;
import java.io.FileNotFoundException;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Etudiant.class)
public class TestRes{

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
			e.printStackTrace();
            fail(_("Attention, il est interdit de diviser par zéro."));
		}catch(ClassCastException e){
			e.printStackTrace();
            fail(_("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
			e.printStackTrace();
            fail(_("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
            fail(_("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
			e.printStackTrace();
            fail(_("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(NegativeArraySizeException e){
			e.printStackTrace();
            fail(_("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
			e.printStackTrace();
            fail(_("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
			e.printStackTrace();
            fail(_("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
	}	

	private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de l'étudiant affiche le bon résultat
		 */
		public Void call() throws Exception,FileNotFoundException,IOException{
			File f = new File("./student/studentRep.txt");
			System.setOut(new PrintStream(f));
			int r = Etudiant.premierPrenom("student/noms.txt");
			Process proc = Runtime.getRuntime().exec("./script.sh");
			int status = proc.waitFor();
			if(status != 0) {
				String feed = MessageFormat.format(_("{0} : vous ne lisez pas bien le fichier, le résultat n'est pas celui attendu !"),test_name());
				fail(feed);
			}
			return null;
		}
	}

	@Test
	public void test_2() {
		catcher(new t2());
		printSucceed();
	}
}
