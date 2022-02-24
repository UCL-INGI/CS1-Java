/**
 *  Copyright (c)  2017 Olivier Martin, Dubray Alexandre
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package src;

import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;

import org.hamcrest.Matcher;

import java.text.MessageFormat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import student.Translations.Translator;
import java.util.concurrent.Callable;

import StudentCode.*;

import org.mockito.Mockito;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import org.mockito.exceptions.verification.WantedButNotInvoked;

public class TestCode {

	@Rule
	public TestName name = new TestName();

	private String test_name(){
		String s = name.getMethodName().replaceAll("_"," ");
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}

	private void printSucceed(){
		System.err.println(MessageFormat.format(Translator.translate("{0} : réussi"),test_name()));
	}
    
    /*
     *  Vérifie si :
     *      - une fonction {fun_name} existe dans la classe Etudiant
     *      - le type de retour de la fonction de l'étudiant est egal à {expected_type}
     *      - si les arguments sont du bon type
     */
    private void check_etudiant_function(String class_name, String fun_name, Class expected_type, Class[] expected_parameters) throws ClassNotFoundException {
        Class<?> c = Class.forName(class_name);
        boolean methodFound = false;
        // On cherche la methode désirée parmis les méthodes de la classe Etudiant
        for (Method method : c.getDeclaredMethods()) {
            if(method.getName().equals(fun_name)){
                methodFound = true;
                if(! method.getReturnType().equals(expected_type)){
                    fail(MessageFormat.format(Translator.translate("Le type de retour de votre fonction doit être du type {0} !\n"), expected_type.toString()));
                }
                if(method.getGenericParameterTypes().length != expected_parameters.length){
                    fail(MessageFormat.format(Translator.translate("Votre fonction doit prendre {0} argument(s) !\n"), expected_parameters.length));
                }
                for(int i = 0; i < expected_parameters.length; i++){
                    if(! method.getGenericParameterTypes()[i].equals(expected_parameters[i])){
                        fail(MessageFormat.format(Translator.translate("L''argument n°{0} n''est pas de type ''{1}'' comme demandé !\n"), i+1, expected_parameters[i].toString()));
                    }
                }
            }
        }
        if(!methodFound)
            fail(Translator.translate("Votre fonction n'a pas été définie correctement. Assurez vous que son nom est correctement défini !\n"));
    }
    
    /*
     *  Cette fonction suppose que la fonction {fun_name} existe dans la classe Etudiant et est correcte.
     *  Exécutez la fonction check_etudiant_function() pour vous en assurer.
     *  Exécute la fonction {fun_name} et retourne le résultat sous forme d'Object.
     */
    private Object run_student_function(Object o,String fun_name, Object... parameters) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class c = o.getClass();
        
        // On cherche la methode désirée parmis les méthodes de la classe Etudiant, suppose qu'il n'y a qu'une méthode portant le nom {fun_name}
        for (Method method : c.getDeclaredMethods()) {
            if(method.getName().equals(fun_name)){
                method.setAccessible(true); //important
                return method.invoke(o, parameters);
            }
        }
        return null;
    }

	private class t1 implements Callable<Void> {

		/**
		 * @pre		-
		 * @post	Vérifie que l'étudiant a bien écrit la signature de la fonction
		 */
		public Void call() throws ClassNotFoundException{
			check_etudiant_function("StudentCode.Pair","equals",boolean.class,new Class[] {Object.class});
			return null;
		}
	}
	private class t2 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que le code de l'étudiant renvoie la bonne réponse en verifiant au 
		 *			préalable que la méthode est bien définie au sens du test t1
		 */
		public Void call() throws ClassNotFoundException, IllegalAccessException,InvocationTargetException {
			check_etudiant_function("StudentCode.Pair","equals",boolean.class,new Class[] {Object.class});
			String msg = Translator.translate("{0} : lorsque l''on appelle votre méthode avec les paires {1} et {2}, votre code devrait renvoyer {3} mais ce n'est pas le cas !");
			Pair p1 = new Pair(1,1);
			Pair p2 = new Pair(1,1);
			Pair p3 = new Pair(2,1);
			Object n = null;
			String feed1 = MessageFormat.format(msg,test_name(),p1,null,false);
			String feed2 = MessageFormat.format(msg,test_name(),p1,p1,true);
			String feed3 = MessageFormat.format(msg,test_name(),p1,p2,true);
			String feed4 = MessageFormat.format(msg,test_name(),p1,p3,false);
			assertFalse(feed1,(Boolean)run_student_function(p1,"equals",n));
			assertTrue(feed2,(Boolean)run_student_function(p1,"equals",p1));
			assertTrue(feed3,(Boolean)run_student_function(p1,"equals",p2));
			assertFalse(feed4,(Boolean)run_student_function(p1,"equals",p3));
			return null;
		}
	}

	private class t3 implements Callable<Void> {
		/**
		 * @pre		-
		 * @post	Vérifie que l'étudiant utilise bien les getters
		 *			pour récupérer les valeurs des variables d'instance.
		 */
		public Void call() throws ClassNotFoundException,IllegalAccessException,InvocationTargetException{
			check_etudiant_function("StudentCode.Pair","equals",boolean.class,new Class[] {Object.class});
			Pair p1 = new Pair(1,0);
			Pair p2 = new Pair(1,2);
			Pair spy1 = Mockito.spy(p1);
			Pair spy2 = Mockito.spy(p2);
			run_student_function(spy1,"equals",spy2);
			try {
				verify(spy1,atLeast(1)).getA();
				verify(spy1,atLeast(1)).getB();
				return null;
			} catch (WantedButNotInvoked e) {
				String msg = Translator.translate("{0} : vous devez utiliser les getters pour récupérer les valeurs des variables d''instances !");
				String feed = MessageFormat.format(msg,test_name());
				fail(feed);
				return null;
			}
		}
	}
    
    public void catcher(Callable<Void> f) {
        try{
			f.call();
        }catch (InvocationTargetException e){
            Throwable t = e.getCause();
            if(t instanceof ArithmeticException){
                fail(Translator.translate("Attention, il est interdit de diviser par zéro."));
            }else if(t instanceof ClassCastException){
                fail(Translator.translate("Attention, certaines variables ont été mal castées !"));
            }else if(t instanceof StringIndexOutOfBoundsException){
                fail(Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
            }else if(t instanceof ArrayIndexOutOfBoundsException){
                fail(Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
            }else if(t instanceof NullPointerException){
                fail(Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
            }else{
                fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + t.toString());
            }
        }catch(Exception e){
            fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
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

//	@Test
	public void test_3() {
		catcher(new t3());
		printSucceed();
	}
}
