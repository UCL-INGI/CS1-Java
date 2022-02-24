package src;
/**
 *  @author François MICHEL
 */
import java.util.Random;
import java.text.MessageFormat;
import java.util.concurrent.Callable;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
import static org.hamcrest.CoreMatchers.is;

import StudentCode.OrderedPair;

import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import org.mockito.exceptions.verification.WantedButNotInvoked;

import student.Translations.Translator;

public class TestCode {

	@Rule
	public TestName name = new TestName();

	private void printSucceed(String q) {
		System.err.println(MessageFormat.format(Translator.translate(q + "{0} : réussi"),test_name()));
	}

	private String test_name() {
		String s = name.getMethodName().replace("_"," ");
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}

	private class t1 implements Callable<Void> {
		/**
		 * @pre -
		 * @post Vérifie si l'étudiant utilise bien la méthode setOrdered() 
		 *		 dans la méthode setA()
		 */
		public Void call(){
			OrderedPair spy = Mockito.spy(OrderedPair.class);
			spy.setA(2);
				String feed = MessageFormat.format("@1 :\n" + Translator.translate("{0} : vous devez utilisez setOrdered pour changer la valeur de ordered !"),test_name());
			if(!spy.getOrdered()) {
				try {
					verify(spy,atLeast(1)).setOrdered(false);
				} catch (WantedButNotInvoked e){ 
					fail(feed);
				}
			} 
			spy.setA(-2);
			if(spy.getOrdered()) {
				try {
					verify(spy,atLeast(1)).setOrdered(true);
				} catch(WantedButNotInvoked e) {
					String feed2 = MessageFormat.format("@1 :\n" + Translator.translate("{0} : vous devez utilisez setOrdered pour changer la valeur de ordered !"),test_name());
					fail(feed2);
				}
			}
			return null;
		}
	}

	private class t2 implements Callable<Void> {
		/**
		 * @pre -
		 * @post Vérifie si l'étudiant utilise bien la méthode setOrdered() 
		 *		 dans la méthode setB()
		 */
		public Void call(){
			OrderedPair spy = Mockito.spy(OrderedPair.class);
			spy.setB(-1);
			String feed = MessageFormat.format("@1 :\n" + Translator.translate("{0} : vous devez utilisez setOrdered pour changer la valeur de ordered !"),test_name());
			if(!spy.getOrdered()) {
				try {
					verify(spy,atLeast(1)).setOrdered(false);
				} catch (WantedButNotInvoked e){ 
					fail(feed);
				}
			} 
			spy.setB(2);
			if(spy.getOrdered()) {
				try {
					verify(spy,atLeast(1)).setOrdered(true);
				} catch(WantedButNotInvoked e) {
					String feed2 = MessageFormat.format("@1 :\n" + Translator.translate("{0} : vous devez utilisez setOrdered pour changer la valeur de ordered !"),test_name());
					fail(feed2);
				}
			}
			return null;
		}
	}

	private class t3 implements Callable<Void> {
		/**
		 * 	@pre	-
		 * 	@post	Vérifie le code de l'étudiant en regard de la question 1 (méthode setA()). On vérifie la valeur de la variable ordered.
		 * 			Lance une AssertionError lorsqu'une réponse est incorrecte.
		 */
		public Void call(){
			String msg = "@1 :\n" + Translator.translate("{0} : après l''appel à setA({1}), nous avons la paire ({2},{3}) et ''ordered'' devrait valoir {4} car {5} <= {6} mais ce n''est pas le cas");
			Random r = new Random();
			OrderedPair p = new OrderedPair();
			int b = r.nextInt(1000);
			int a = r.nextInt(1000);
			boolean ordered = (a <= b);
			int i;
			for(i = 0 ; i < 10 ; i++){
				p.setA(a);
				ordered = (p.getA() <= p.getB());
				Matcher<Boolean> matches = IsEqual.equalTo(ordered);
				//	On met ici "@1 : " devant le message d'erreur pour que le script exécutant les tests
				//	Puisse associer le message d'erreur à la question désirée.
				String feed1 = MessageFormat.format(msg,test_name(),a,p.getA(),p.getB(),ordered,Math.min(p.getA(),p.getB()),Math.max(p.getA(),p.getB()));
				assertThat(feed1,p.getOrdered(),is(ordered));
				p.setA(-a);
				ordered = (p.getA() <= p.getB());
		
				String feed2 = MessageFormat.format(msg,test_name(),-a,p.getA(),p.getB(),ordered,Math.min(p.getA(),p.getB()),Math.max(p.getA(),p.getB()));
				assertThat(feed2,p.getOrdered(),is(ordered));

				a = r.nextInt(1000);
				b = r.nextInt(1000);
			}
			return null;
		}
	}


	private class t4 implements Callable<Void> {
		/**
		 * 	@pre	-
		 * 	@post	Vérifie le code de l'étudiant en regard de la question 2 (setB()). On vérifie ici la valeur de la variable ordered.
		 * 			Lance une AssertionError lorsqu'une réponse est incorrecte.
		 */
		public Void call(){
			String msg = "@2 :\n" + Translator.translate("{0} : après l''appel à setB({1}), nous avons la paire ({2},{3}) et ''ordered'' devrait valoir {4} car {5} <= {6}, mais ce n''est pas le cas");
			Random r = new Random();
			OrderedPair p = new OrderedPair();
			int b = r.nextInt(1000);
			int a = r.nextInt(1000);
			boolean ordered = (a <= b);
			for(int i = 0 ; i < 10 ; i++){
				p.setB(b);
				ordered = (p.getA() <= p.getB());
				String feed1 = MessageFormat.format(msg,test_name(),b,p.getA(),p.getB(),ordered,Math.min(p.getA(),p.getB()),Math.max(p.getA(),p.getB()));
				assertThat(feed1,p.getOrdered(),is(ordered));

				p.setB(-b);
				ordered = (p.getA() <= p.getB());
				String feed2 = MessageFormat.format(msg,test_name(),-b,p.getA(),p.getB(),ordered,Math.min(p.getA(),p.getB()),Math.max(p.getA(),p.getB()));
				assertThat(feed2,p.getOrdered(),is(ordered));

				a = r.nextInt(1000);
				b = r.nextInt(1000);
			}
			return null;
		}
	}

	private class t5 implements Callable<Void> {
		/**
		 * 	@pre	-
		 * 	@post	Teste le code de l'étudiant en regard de la question 1 (setA()) : On vérifie si la variable a bien été mise à jour.
		 * 			Lance une AssertionError lorsqu'une réponse est incorrecte.
		 */
		public Void call(){
			String msg = "@1 :\n" + Translator.translate("{0} : après l''appel à setA({1}), ''a'' vaut {2} au lieu de {3}");
			String msg2 = "@1 :\n" + Translator.translate("{0}: après l''appel à setA({1}), {2} ''a'' changé et est passé de {3} à {4}");
			Random r = new Random();
			OrderedPair p = new OrderedPair();
			int b = r.nextInt(1000);
			int a = r.nextInt(1000);
			int oldA = p.getA();
			int oldB = p.getB();
			int newA;
			int newB;
			for(int i = 0 ; i < 10 ; i++){
				p.setA(a);
				newA = p.getA();
				String feed1 = MessageFormat.format(msg,test_name(),a,newA,a);
				assertThat(feed1,newA,is(a));

				newB = p.getB();
				String feed2 = MessageFormat.format(msg2,test_name(),"b",a,oldB,newB);
				assertThat(feed2,newB,is(oldB));

				oldA = newA;
				p.setA(-b);
				newA = p.getA();

				String feed3 = MessageFormat.format(msg2,test_name(),-b,"a",newA,-b);
				assertThat(feed3,newA,is(-b));

				newB = p.getB();
				String feed4 = MessageFormat.format(msg2,test_name(),-b,"b",oldB,newB);
				assertThat(feed4,newB,is(oldB));
				oldA = newA;
				b = r.nextInt(1000);
				a = r.nextInt(1000);
			}
			return null;
		}
	}

	private class t6 implements Callable<Void> {
		/**
		 * 	@pre	-
		 * 	@post	Vérifie le code de l'éudiant en regard de la question2 (setB()). On vérifie ici si la variable a bien été mise à jour.
		 * 			Lance une AssertionError lorsqu'une réponse est incorrecte.
		 */
		public Void call(){
			String msg = "@2 :\n" + Translator.translate("{0} : après l''appel setB({1}), ''b'' vaut {2} au lieu de {3}");
			String msg2 = "@2 :\n" + Translator.translate("{0} : après l''appel setB({0}) {2} ''a'' changé et est passé de {3} à {4}");
			Random r = new Random();
			OrderedPair p = new OrderedPair();
			int b = r.nextInt(1000);
			int a = r.nextInt(1000);
			int oldA = p.getA();
			int oldB = p.getB();
			int newA;
			int newB;
			for(int i = 0 ; i < 10 ; i++){
				p.setB(b);
				newB = p.getB();
				String feed1 = MessageFormat.format(msg,test_name(),b,newB,b);
				assertThat(feed1,newB,is(b));

				newA = p.getA();
				String feed2 = MessageFormat.format(msg2,test_name(),b,"a",oldA,newA);
				assertThat(feed2,newA,is(oldA));

				oldB = newB;
				p.setB(-a);
				newB = p.getB();
				String feed3 = MessageFormat.format(msg,test_name(),-a,newB,-a);
				assertThat(feed3,newB,is(-a));

				newA = p.getA();
				String feed4 = MessageFormat.format(msg2,test_name(),-a,"a",oldA,newA);
				assertThat(feed4,newA,is(oldA));
				
				oldB = newB;
				b = r.nextInt(1000);
				a = r.nextInt(1000);
			}
			return null;
		}
	}

	private void catcher(Callable<Void> test, int nbQ) {
        String pre = MessageFormat.format("@{0} :\n", nbQ);
		try {
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

	@Test
	public void test_1() {
		catcher(new t1(),1);
		printSucceed("@1 :\n");
	}
	
	@Test
	public void test_2() {
		catcher(new t2(),2);
		printSucceed("@2 :\n");
	}

	@Test
	public void test_3() {
		catcher(new t3(),1);
		printSucceed("@1 :\n");
	}
	
	@Test
	public void test_4() {
		catcher(new t4(),2);
		printSucceed("@2 :\n");
	}

	@Test
	public void test_5() {
		catcher(new t5(),1);
		printSucceed("@1 :\n");
	}

	@Test
	public void test_6() {
		catcher(new t6(),2);
		printSucceed("@2 :\n");
	}
}
