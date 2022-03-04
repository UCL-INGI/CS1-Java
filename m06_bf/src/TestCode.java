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

import org.mockito.Mockito;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.exceptions.verification.WantedButNotInvoked;

import StudentCode.Employe;
import student.Translations.Translator;
import src.librairies.Inspector;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.Callable;

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

	private String genName(){
			String [] names = new String [] {"George","Jean","Paul"};
			return names[new Random().nextInt(3)];
	}

	private float genSalaire() {
			return (float) new Random().nextInt(1500);
	}

	private class t3 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Retourne true ssi name se trouve dans val
		 */
		private boolean check_name(Object [] val, String name) {
			for(Object o : val) {
				if(o instanceof String) {
					String s = (String) o;
					if (s.equals(name)) 
						return true;
				}
			}
			return false;
		}

		/**
		 * @pre		-
		 * @post	Retourne false ssi salaire se trouve dans val
		 */
		private boolean check_salaire(Object [] val, Float salaire) {
			for(Object o : val) {
				if(o instanceof Float) {
					if (((Float)o).equals(salaire)) 
						return true;
				}
			}
			return false;
		}

		/**
		 * @pre		-
		 * @post	Vérifie que le constructeur de l'étudiant
		 *			construit une bonne instance de
		 */
		public Void call() {
			String name = genName();
			float salaire = genSalaire();
			Employe e =(Employe) Inspector.run_construct_specified(Employe.class,name,salaire);
			if(e == null)
				fail(MessageFormat.format(Translator.translate("{0} : aucun constructeur public prenant comme premier paramètre un String et comme deuxième un float n''as été trouvé dans votre réponse !"),test_name()));
			String msg_name = Translator.translate("{0} : lorsque l''on construit un nouvel objet Employe avec comme paramètre {1} et {2,number,#}, il n''y a pas de variable d''instance de type {3} avec la valeur {4}");
			Object [] v = Inspector.getAllInstanceValue(Employe.class,e);
			if(v == null)
				fail(Translator.translate("Une erreur inattendu est survenue dans votre tâche, veuillez contactez une administrateur"));
			if(!check_name(v,name))
				fail(MessageFormat.format(msg_name,test_name(),name,salaire,"String",name));
			if(!check_salaire(v,new Float(salaire)))
				fail(MessageFormat.format(msg_name,test_name(),name,salaire,"float",salaire));
			return null;
		}
	}

	private class t4 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie la méthode getNom() de l'étudiant
		 */
		public Void call() {
			new t3().call();
			String name = genName();
			float salaire = genSalaire();
			Employe e =(Employe) Inspector.run_construct_specified(Employe.class,name,salaire);
			if(e == null)
				fail(MessageFormat.format(Translator.translate("{0} : aucun constructeur public prenant comme premier paramètre un String et comme deuxième un float n''as été trouvé dans votre réponse !"),test_name()));
			String error_msg = Translator.translate("{0} : lorsque l''on a un employé avec le nom {1}, votre méthode getNom() retourne {2}");
			try {
				String ret  = (String) Inspector.run_method(e,"getNom",new Object []{});
				String feed = MessageFormat.format(error_msg,test_name(),name,ret);
				assertTrue(feed,ret == name);
			} catch (NoSuchMethodException err) {
				fail(MessageFormat.format(Translator.translate("{0} : la méthode {1} n''as pas été trouvée dans votre réponse !"),test_name(),"getNom"));
			}
			return null;
		}
	}

	private class t5 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie la méthode getSalaire() de l'étudiant
		 */
		public Void call() {
			new t3().call();
			String name = "Jean";
			float salaire = genSalaire();
			Employe e =(Employe) Inspector.run_construct_specified(Employe.class,name,salaire);
			if(e == null)
				fail(MessageFormat.format(Translator.translate("{0} : aucun constructeur public prenant comme premier paramètre un String et comme deuxième un float n''as été trouvé dans votre réponse !"),test_name()));
			try {
				float ret = (Float) Inspector.run_method(e,"getSalaire",new Object [] {});
				String feed = MessageFormat.format(Translator.translate("{0} : lorsque l''on a un employé avec un salaire de {1}, votre méthode getSalaire() retourne {2}"),test_name(),salaire,ret);
				assertTrue(feed,ret == salaire);
			} catch (NoSuchMethodException err) {
				fail(MessageFormat.format(Translator.translate("{0} : la méthode {1} n''as pas été trouvée dans votre réponse !"),test_name(),"getSalaire"));
			}
			return null;
		}
	}

	private class t6 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie la méthode toString() de l'étudiant
		 */
		public Void call() {
			new t3().call();
			String name = genName();
			float salaire = genSalaire();
			Employe e =(Employe) Inspector.run_construct_specified(Employe.class,name,salaire);
			if(e == null)
				fail(MessageFormat.format(Translator.translate("{0} : aucun constructeur public prenant comme premier paramètre un String et comme deuxième un float n''as été trouvé dans votre réponse !"),test_name()));
			try {
				String ret = (String) Inspector.run_method(e,"toString",new Object [] {});
				String feed = MessageFormat.format(Translator.translate("{0} : lorsque l''on a un l''Employe {1} avec le salaire {2}, votre méthode toString retourn {3}"),test_name(),name,salaire,ret); 
				assertTrue(feed,ret == name + " : "+ salaire);
			} catch (NoSuchMethodException err) {
				fail(MessageFormat.format(Translator.translate("{0} : la méthode {1} n''as pas été trouvée dans votre réponse !"),test_name(),"toString"));
			}
			return null;
		}
	}

	private class t7 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie la méthode augmente de l'étudiant
		 */
		public Void call() {
			new t3().call();
			new t5().call();
			String name = genName();
			float salaire = genSalaire();
			Employe e = (Employe) Inspector.run_construct_specified(Employe.class,name,salaire);
			if(e == null)
				fail(MessageFormat.format(Translator.translate("{0} : aucun constructeur public prenant comme premier paramètre un String et comme deuxième un float n''as été trouvé dans votre réponse !"),test_name()));
			try {
				float pourcentage = (float) new Random().nextInt(101);
				Inspector.run_method_specified(e,"augmente",pourcentage);
				Float new_salaire =(Float) Inspector.run_method(e,"getSalaire",new Object [] {});
				String feed = MessageFormat.format(Translator.translate("{0} : lorsque l''on a un Employe avec un salaire de {1} et que l''on augmente de {2}, votre méthode fixe son salaire à {3}"),test_name(),salaire,pourcentage,new_salaire);
				assertTrue(feed,salaire*(1+(pourcentage/100)) == new_salaire);
				//assertThat(feed,new_salaire,is(salaire*(1+(pourcentage/100))));
			} catch (NoSuchMethodException err) {
				fail(MessageFormat.format(Translator.translate("{0} : la méthode {1} n''as pas été trouvée dans votre réponse !"),test_name(),"augmente"));
			}
			return null;
		}
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

	@Test
	public void test_6() {
		catcher(new t6());
		printSucceed();
	}

	@Test
	public void test_7() {
		catcher(new t7());
		printSucceed();
	}
}
