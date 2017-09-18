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

import StudentCode.Employe;
import static student.Translations.Translator._;
import src.librairies.Inspector;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.lang.reflect.Modifier;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

public class TestInspect{

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
		 * @post	Vérifie que le constructeur est bien définis
		 */
		public Void call() {
			String [] errors_msg = Inspector.inspect_constructors(Employe.class,new int [] {Modifier.PUBLIC},new Class [][] {{String.class,float.class}},null);
			String feed = "";
			for(String s : errors_msg) {
				if(s != null)
					feed += test_name()+" : "+ s + "\n";
			}
			if(!feed.equals(""))
				fail(feed);
			return null;
		}
	}

	private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que les méthodes sont bien définies
		 */
		public Void call() {
			int [] modifier_tabs = new int [] {Modifier.PUBLIC,Modifier.PUBLIC,Modifier.PUBLIC,Modifier.PUBLIC};
			Class [] ret_types = new Class [] {String.class,float.class,String.class,void.class};
			String [] name =  new String [] {"getNom","getSalaire","toString","augmente"};
			Class [][] param = new Class [][] {{},{},{},{float.class}};
			String [] errors_msg = Inspector.inspect_methods(Employe.class,modifier_tabs,ret_types,name,param,null);
			String feed = "";
			for(String error : errors_msg) {
				if(error != null)
					feed += test_name() + " : "+ error + "\n";
			}
			if(!feed.equals(""))
				fail(feed);
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
}
