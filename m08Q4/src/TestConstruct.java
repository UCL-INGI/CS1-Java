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

import StudentCode.ByteString;
import student.Translations.Translator;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.Random;

public class TestConstruct{

	@Rule
	public TestName name = new TestName();

	private void printSucceed() {
		System.err.println(MessageFormat.format(Translator.translate("{0} : réussi"),test_name()));
	}

	private String test_name() {
		String s = name.getMethodName().replaceAll("_"," ");
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}
	

    public void catcher(Callable<Void> test, int nQuestion) {
        try{
            test.call();
        }catch (ArithmeticException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(NegativeArraySizeException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }	
	private String generateByte() {
		String b = "";
		Random r = new Random();
		for(int i= 0;i<8;i++)
			b += r.nextInt(2);
		return b;
	}

	private class t1 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le constructeur sans arguments de 
		 *			l'étudiant crée bien un StringBuilder représentant
		 *			le byte 00000000
		 */
		public Void call() {
			String msg = "@1 :\n" + Translator.translate("{0} : votre code construit le byte {1} (représentation naturelle) au lieu du byte 00000000 (représentation naturelle)");
			String ngLength = "@1 :\n"+ Translator.translate("{0} : votre code ne construit pas un String de taille 8");
			String ngFeed = MessageFormat.format(ngLength,test_name());
			ByteString bs = new ByteString();
			String feed = MessageFormat.format("@1 :\n" + msg,test_name(),bs.toString());
			if(bs.toString().length() != 8){
				fail(ngFeed);
				return null;
			}
			assertThat(feed,bs.toString(),is("00000000"));
			return null;
		}
	}

	private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le constructeur avec un argument
		 *			de l'étudiant crée bien un StringBuilder représentant
		 *			le String
		 */
		public Void call() {
			String msg = Translator.translate("{0} : lorsque l''on passe comme argument {1} à votre constructeur, il crée le byte {2} (représentation naturelle)");
			String rb = generateByte();
			ByteString bs = new ByteString(rb);
			if(bs.toString().length() != 8) {
				fail(MessageFormat.format("@2 :\n" + Translator.translate("{0} : votre code ne construit pas un byte de taille 8"),test_name()));
				return null;
			}
			String feed = MessageFormat.format("@2 :\n" + msg,test_name(),rb,bs.toString());
			assertThat(feed,bs.toString(),is(rb));
			return null;
		}
	}

	@Test
	public void test_1() {
		catcher(new t1(),1);
		printSucceed();
	}

	@Test
	public void test_2() {
		catcher(new t2(),2);
		printSucceed();
	}

}
