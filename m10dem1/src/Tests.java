
/**
 *  Copyright (c) 2017 Brandon Naitali
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

import java.util.Random;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.Random;

import java.text.MessageFormat;
import static student.Translations.Translator._;
import StudentCode.*;

import src.librairies.*;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class Tests{
    @Rule public TestName name = new TestName();
    public String feedbackOut  =_("vérifiez l'output sur la sortie standard (stdOut).\n");
    public String feedbackErr  =_("vérifiez l'output sur la sortie d'erreur standard (stdErr).\n");
    public String feedbackRes  =_("vérifiez les valeurs de retour de votre programme.\n");
    public String feedbackException  =_("vérifiez que vous gérez bien les exceptions.\n");

    public String readStdout(boolean etudiant, String a, String b){
	String[] inputs = {a, b};

	ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
	try{
		int c = etudiant? Etudiant.RevisedRatio.div(inputs) : Correction.RevisedRatio.div(inputs);
	}catch(Exception e){
		return " "; // quand on ne lit rien sur la sortie standard à cause d'une exception
	}
        System.setOut(old);
        return baos.toString();
    }
    public String readStderr(boolean etudiant, String a, String b){
	String[] inputs = {a, b};
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.err;
        System.setErr(ps);
	try{
		int c = etudiant? Etudiant.RevisedRatio.div(inputs) : Correction.RevisedRatio.div(inputs);
	}catch(Exception e){
		return " ";
	}
        System.setErr(old);
        return baos.toString();
    }

    @Test
    public void test_1(){ 
	Random r = new Random();
	int r1 = r.nextInt(19) + 1;
	int r2 = 0;
	String feedback  = MessageFormat.format(_("la division par 0 ne donne pas le résultat attendu : \n"), r1, r2);
	catcher(new TestException(((Integer)r1).toString(), ((Integer)r2).toString(), feedback), 1); 
    }
    @Test
    public void test_2(){ 
	Random r = new Random();
	String r1 = "notANumber";
	int r2 = r.nextInt(19) + 1;
	String feedback  = MessageFormat.format(_("le parsing d''un String composé de lettres ne donne pas le résultat attendu : \n"), r1, r2);
	catcher(new TestException(r1, ((Integer)r2).toString(), feedback), 1); 
    }
    private class TestException implements Callable<Void> {
	String a;
	String b;
	String feedbackAdd; 
	public TestException(String a, String b, String feedback){
		this.a = a;
		this.b = b;
		this.feedbackAdd = feedback;
	}
        public Void call() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{ 
		// On prend la sortie stdout
		// On redirige stderr pour ne pas que ca s'affiche dans le feedback
		ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        	PrintStream ps1 = new PrintStream(baos1);
        	PrintStream old1 = System.err;
        	System.setErr(ps1);
		String out = readStdout(false, a, b);
		String outStudent = readStdout(true, a, b);
        	System.setErr(old1);
		if(outStudent.equals(" ")) fail(test_name() + " : " + feedbackException);		
		if(!out.equals(outStudent)) {
			fail(test_name() + " : " + feedbackAdd + " " + feedbackOut);	
		}
		
		// On prend la sortie stderr
		// On redirige stdout pour eviter les interferences
		ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        	PrintStream ps2 = new PrintStream(baos2);
        	PrintStream old2 = System.out;
        	System.setOut(ps2);
		String err = readStderr(false, a, b);
		String errStudent = readStderr(true, a, b);
		System.setOut(old2);
		if(errStudent.equals(" ")) fail(test_name() + " : " + feedbackException);		
		if(!err.equals(errStudent)){
			fail(test_name() + " : " + feedbackAdd + " " + feedbackErr);
		}

		// On prend la valeur de retour du programme // à faire à la fin
		// On redirige stderr pour ne pas que ca s'affiche dans le feedback
		String[] inputs2 = {a, b};

		ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
        	PrintStream ps3 = new PrintStream(baos3);
        	PrintStream old3 = System.err;
		
		try{
			System.setErr(ps3);               
			int result = Correction.RevisedRatio.div(inputs2);
			int resultStudent = Etudiant.RevisedRatio.div(inputs2);
			System.setErr(old3);
			if(result != resultStudent) {
                		System.setErr(old3);
				fail(test_name() + " : " + feedbackAdd + " " + feedbackRes);	
			}
		}catch(Exception e){
			fail(test_name() + " : " + feedbackAdd + " " + feedbackErr);
		}
		System.err.println(MessageFormat.format(_("{0} : réussi"), test_name()));
	    	return null;
	}

    }
    public void catcher(Callable<Void> test, int n) {
        	try{
		    test.call();
        	}catch (InvocationTargetException e){
		    Throwable t = e.getCause();
		    if(t instanceof ArithmeticException){
		        fail(_("Attention, il est interdit de diviser par zéro."));
		    }else if(t instanceof ClassCastException){
		        fail(_("Attention, certaines variables ont été mal castées !"));
		    }else if(t instanceof StringIndexOutOfBoundsException){
		        fail(_("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
		    }else if(t instanceof ArrayIndexOutOfBoundsException){
		        fail(_("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
		    }else if(t instanceof NullPointerException){
		        fail(_("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
		    }else if(t instanceof NegativeArraySizeException){
		        fail(_("Vous initialisez un tableau avec une taille négative."));
		    }else if(t instanceof StackOverflowError){
		        fail(_("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
		    }else{
		        fail(_("Une erreur inattendue est survenue dans votre tâche : ") + t.toString());
		    }
		}catch(Exception e){
		    fail(_("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
		}
    }
    private String test_name(){
        String s = name.getMethodName().replaceAll("_", " ");
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
	
}

