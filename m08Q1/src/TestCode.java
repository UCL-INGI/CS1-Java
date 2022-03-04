package src;
/**
 * @author Dubray Alexandre
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

import StudentCode.StringTab;
import student.Translations.Translator;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.Arrays;
import java.util.Random;

public class TestCode{

	@Rule
	public TestName name = new TestName();

	private void printSucceed() {
		System.err.println(MessageFormat.format(Translator.translate("{0} : réussi") + "\n",test_name()));
	}

	private String test_name() {
		String s = name.getMethodName().replaceAll("_"," ");
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}
	
    public void catcher(Callable<Void> test, int nQuestion) {
        String pre = MessageFormat.format("@{0} :\n", nQuestion);
        try{
            test.call();
        }catch (ArithmeticException e){
            fail(pre + Translator.translate("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(pre + Translator.translate("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(pre + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(pre + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(pre + Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(NegativeArraySizeException e){
            fail(pre + Translator.translate("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(pre + Translator.translate("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(pre + Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }

	/**
	 * @pre		-
	 * @post	Crée un tableau de taille aléatoire entre 2 et 12
	 *			comprenant des caractère aléatoire entre 'a' et 'z'
	 */
	private char [] randomCharArray() {
		Random r = new Random();
		char [] tab = new char[r.nextInt(11) + 2];
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
		 * @post	Vérifie que le premier constructeur de l'étudiant
		 *			crée bien un StringTab de taille 1
		 */
		public Void call() {
			char c = randomChar();
			StringTab s = new StringTab(c);
			if(s.getS() == null) {
				String feedNull = MessageFormat.format("@1 :\n" + Translator.translate("{0} : votre code construit une instance où s est null !"),test_name());
				fail(feedNull);
			}
			String message = "@1 :\n" + Translator.translate("{0} : votre code ne construit pas un StringTab de taille 1 !");
			String feed = MessageFormat.format(message,test_name());
			assertTrue(feed,s.realLength() == 1);
			return null;
		}
	}

	private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le premier constructeur de l'étudiant créée bien
		 *			un StringTab dont le premier caractère est le caractère passé
		 *			en paramètre
		 */
		public Void call() {
			char c = randomChar();
			StringTab s = new StringTab(c);
			if(s.getS() == null) {
				String feedNull = MessageFormat.format("@1 :\n" + Translator.translate("{0} : votre code construit une instance où s est null !"),test_name());
				fail(feedNull);
			}
			String msg = "@1 :\n" + Translator.translate("{0} : lorsque l''on exécute votre constructeur avec comme paramètre {1}, la première lettre de votre StringTab est {2}");
			String feed = MessageFormat.format(msg,test_name(),c,s.realCharAt(0));
			assertTrue(feed,s.realCharAt(0) == c);
			return null;
		}
	}

	private class t3 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le deuxième constructeur de l'étudiant crée un StringTab
		 *			de longueur c.length
		 */
		public Void call() {
			char [] c = randomCharArray();
			StringTab s = new StringTab(c);
			if(s.getS() == null) {
				String feedNull = MessageFormat.format("@2 :\n" + Translator.translate("{0} : votre code construit une instance où s est null !"),test_name());
				fail(feedNull);
			}
			String msg = "@2 :\n" + Translator.translate("{0} : lorsque l''on passe comme paramètre {1} à votre constructeur, vous construisez un String de taille {2}");
			String feed = MessageFormat.format(msg,test_name(),Arrays.toString(c),s.realLength());
			assertTrue(feed,s.realLength() == c.length);
			return null;
		}
	}

	private class t4 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le deuxième constructeur de l'étudiant crée un StringTab
		 *			dont les éléments sont ceux passé en paramètres dans le tableau c
		 */
		public Void call() {
			new t3().call();
			char [] c = randomCharArray();
			StringTab s = new StringTab(c);
			String msg = "@2 :\n" + Translator.translate("{0} : lorsque l''on passe comme paramètre {1} à votre constructeur, votre tableau est {2}");
			String feed = MessageFormat.format(msg,test_name(),Arrays.toString(c),Arrays.toString(s.getS()));
			assertTrue(feed,Arrays.equals(s.getS(),c));
			return null;
		}
	}

	private class t5 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que la méthode length() de
		 *			l'étudiant a le comportement voulu
		 */
		public Void call() {
			char c [] = randomCharArray();
			StringTab s = new StringTab(c);
			s.setArray(c); // On assure que le tableau sera c
			String msg =  "@3 :\n" + Translator.translate("{0} lorsque l''on utilise votre méthode length() sur le StringTab {1}, votre méthode renvoie {2}");
			String feed = MessageFormat.format(msg,test_name(),Arrays.toString(c),s.length());
			assertTrue(feed,s.length() == c.length);
			return null;
		}
	}

	private class t6 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que la méthode charAt() de 
		 *			l'étudiant a le comportement attendu
		 */
		public Void call() {
			char c [] = randomCharArray();
			StringTab s = new StringTab(c);
			s.setArray(c); // On assure que le tableau sera c
			int indice = new Random().nextInt(c.length);
			String feed = MessageFormat.format("@3 :\n" + Translator.translate("{0} lorsque l''on a le tableau {1} et que l''on fait charAt({2}), votre méthode renvoie {3}"),test_name(),Arrays.toString(c),indice,s.charAt(indice));
			assertTrue(feed,s.charAt(indice) == c[indice]);
			return null;
		}
	}

	@Test
	public void test_1(){
		catcher(new t1(),1);
		printSucceed();
	}

	@Test
	public void test_2(){
		catcher(new t2(),1);
		printSucceed();
	}
	@Test
	public void test_3(){
		catcher(new t3(),2);
		printSucceed();
	}
	@Test
	public void test_4(){
		catcher(new t4(),2);
		printSucceed();
	}
	@Test
	public void test_5(){
		catcher(new t5(),3);
		printSucceed();
	}

	@Test
	public void test_6(){
		catcher(new t6(),3);
		printSucceed();
	}
}
