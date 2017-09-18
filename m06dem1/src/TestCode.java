package src;
/**
 *  @author François MICHEL
 *  refactor 2017 by Alexandre Dubray
 */
import java.util.Random;
import java.text.MessageFormat;
import java.util.concurrent.Callable;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;

import org.mockito.Mockito;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import org.mockito.exceptions.verification.WantedButNotInvoked;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.equalTo;

import StudentCode.Pair;

import static student.Translations.Translator._;

public class TestCode{

	@Rule public TestName name = new TestName();

	private void printSucceed() {
		System.err.println(MessageFormat.format(_("{0} : réussi"),test_name()));
	}

	private class t1 implements Callable<Void> {
		/**
		 * @pre -
		 * @post Vérifie que le code de l'étudiant utilise bien les getters
		 */
		public Void call() {
			Pair p = new Pair(2,3);
			Pair spy = Mockito.spy(p);
			
			spy.opposite();

			try {
				verify(spy,atLeast(1)).getA();
				verify(spy,atLeast(1)).getB();
				return null;
			}catch (WantedButNotInvoked e) {
				String feed = MessageFormat.format(_("{0} : vous devez utiliser les getters pour récupérer les valeurs des variables d''instances !"),test_name());
				fail(feed);
				return null;
			}
		}
	}

	private class t3 implements Callable<Void> {	
		/**
		 * @pre	-
		 * @post	Vérifie que le code de l'étudiant renvoie bien une nouvelle instance
		 *
		 */
		public Void call(){
			String msg = _("{0} : Il semblerait que votre méthode renvoie un résultat incorrect. La méthode doit renvoyer une nouvelle paire !");
			Random r = new Random();
			int a = 0;
			int b = 0;
			for(int i = 0 ; i < 10 ; i++){
				Pair p = new Pair(a,b);
				Pair res = p.opposite();
				String feedback = MessageFormat.format(msg,test_name());
				assertThat(feedback,res,not(sameInstance(p)));
				a = r.nextInt(1000);	
				b = r.nextInt(1000);
			}
			return null;
		}
	}

	private class t4 implements Callable<Void> {
		/**
		 * @pre -
		 * @post Vérifie que le code de l'étudiant renvoie une paire avec les bon paramètres
		 */
		public Void call() {
			String msg = _("{0} : Votre méthode ne renvoie pas une paire avec les bonne valeurs. Pour la pair ({1},{2}), vous renvoyez ({3},{4}) au lieu de ({5},{6}).");
			Random r = new Random();
			int a = 0;
			int b = 0;
			for (int i=0 ; i < 10; i++) {
				Pair p = new Pair(a,b);
				Pair res = p.opposite();
				String feedback = MessageFormat.format(msg,test_name(),a,b,res.getA(),res.getB(),-a,-b);
				assertThat(feedback,res,is(equalTo(new Pair(-a,-b))));
				a = r.nextInt(1000);
				b = r.nextInt(1000);
			}
			return null;
		}
	}
	
	private class t5 implements Callable<Void> {
		/**
		 * 	@pre	-
		 * 	@post	Vérifie que le code de l'étudiant n'a pas altéré l'objet appelant la méthode.
		 * 			Lance une AssertionError lorsqu'il a été modifié.
		 */
		public Void call(){
			String msg = _("{0} : Votre méthode semble modifier les variable d''instances de l''instance appelante. La paire appelante vallait ({1},{2}) et vaut maintenant ({3},{4}).");
			Random r = new Random();
			int a = 0;
			int b = 0;
			for(int i = 0 ; i < 10 ; i++){
				Pair p = new Pair(a,b);
				Pair res = p.opposite();
				String feedback = MessageFormat.format(msg,test_name(),a,b,p.getA(),p.getB());
				assertThat(feedback,p.getA(),is(a));
				assertThat(feedback,p.getB(),is(b));
				a = r.nextInt(1000);	
				b = r.nextInt(1000);
			}
			return null;
		}
	}

	public void catcher(Callable<Void> test) {
		try {
			test.call();
        }catch (ArithmeticException e){
            fail(_("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(_("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(_("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(_("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(_("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(NegativeArraySizeException e){
            fail(_("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(_("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(_("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }

	@Test
	public void test_1(){
		catcher(new t1());
		printSucceed();
	}


	@Test 
	public void test_2(){
		catcher(new t3());
		printSucceed();
	}

	@Test
	public void test_3() {
		catcher(new t4());
		printSucceed();
	}

	@Test
	public void test_4(){
		catcher(new t5());
		printSucceed();
	}

	private String test_name() {
		String s = name.getMethodName().replaceAll("_"," ");
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}
}
