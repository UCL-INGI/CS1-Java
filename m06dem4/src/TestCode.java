package src;
/**
 *  @author François MICHEL
 */

import static org.junit.Assert.fail;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.EOFException;
import java.util.concurrent.Callable;
import java.text.MessageFormat;

import org.junit.rules.ErrorCollector;
import org.hamcrest.Matcher;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

import StudentCode.*;

import student.Translations.Translator;

public class TestCode{
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Rule
	public TestName name = new TestName();

	private  String test_name(){
		String s = name.getMethodName().replaceAll("_"," ");
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}

	private void printSucceed(){
		System.err.println(MessageFormat.format(Translator.translate("{0} : réussi\n"),test_name()));
	}
	
	private class t1 implements Callable<Void> {
		/**
		 * 	@pre	-
		 * 	@post	Vérifie la condition de la boucle while (@2). Cette dernière doit permettre de lire le fichier en entier.
		 * 			Lance une AssertionError lorsqu'une réponse est incorrecte.
		 */
		public Void call(){
			new t3().call();
			String msg = MessageFormat.format("@2 :\n" + Translator.translate("{0} : Attention, vous ne lisez pas toutes les lignes du fichier, vérifiez la condition"),test_name());
			try {
				Etudiant.lireCotes();
				List<Etudiant> l = Etudiant.getList();
				collector.checkThat(msg,l.size(),equalTo(4));
				return null;
			} catch (EOFException e) {
				collector.addError(new Throwable("@2 :\n" + Translator.translate("{0} : Vérifiez votre condition, vous avez provoqué une EOFException !")));
				return null;
			} catch (Exception e) {
				return null;
			}
		}
	}	
	
	private class t2 implements Callable<Void> {
		/**
		 * 	@pre	-
		 * 	@post	Vérifie si toutes les lignes du fichier ont bien été lues (aucune n'a été passée, etc)
		 * 			Lance une AssertionError lorsqu'une réponse est incorrecte.
		 */
		public Void call(){
			new t3().call();
			new t1().call();
			String msg = MessageFormat.format("@2 :\n" + Translator.translate("{0} : Le fichier ''student/cotes.txt'' n''est pas lu correctement. Vérifiez la condition de boucle !"),test_name());
			try {
			Etudiant.lireCotes();
			} catch (Exception e){
				return null;
			}
			List<Etudiant> l = Etudiant.getList();
			collector.checkThat(msg,l.get(0),equalTo(new Etudiant("Charles","Pecheur",12)));
			collector.checkThat(msg,l.get(1),equalTo(new Etudiant("Olivier","Bonaventure",15)));
			collector.checkThat(msg,l.get(2),equalTo(new Etudiant("Jean-Pierre","Kof",19)));
			collector.checkThat(msg,l.get(3),equalTo(new Etudiant("Johnny","Hallyday",3)));
			return null;
		}
	}

	private class t3 implements Callable<Void> {
		/**
		 * @pre -
		 * @post	Vérifie que l'étudiant ouvre bien le fichier
		 */
		public Void call() {
			String msg = MessageFormat.format("@1 :\n" + Translator.translate("{0} : Vous avez causé une FileNotfoundException, vérifiez le nom du fichier !"),test_name());
			try {
				Etudiant.lireCotes();
				return null;
			} catch (FileNotFoundException e) {
				collector.addError(new Throwable(msg));
				return null;
			} catch (Exception e) {
				return null;
			}
		}
	}

	public void catcher(Callable<Void> f, int nbQ) {
        String pre = MessageFormat.format("@{0} :\n", nbQ);
		try {
			f.call();
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

	@Test
	public void test_1(){
		catcher(new t3(),2);
		printSucceed();
	}

	@Test 
	public void test_2(){
		catcher(new t1(),2);
		printSucceed();
	}

	@Test
	public void test_3() {
		catcher(new t2(),1);
		printSucceed();
	}
}
