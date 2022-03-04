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
import student.Translations.Translator;
import StudentCode.*;

import src.librairies.*;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class TestOut{
    @Rule public TestName name = new TestName();
    public String feedbackRes = Translator.translate("vérifiez les valeurs de retour de votre programme.\n");
    public String feedbackException = Translator.translate("vérifiez que vous gérez bien les exceptions.\n");

    public String readStdout(boolean etudiant, String a, String b){
	String[] inputs = { a, b, b, "0"};
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
	try{
		int c = etudiant? Etudiant.RevisedRatio.div(inputs) : Correction.RevisedRatio.div(inputs);
	}catch(Exception e){
		return " ";
	}
        System.setOut(old);
        return baos.toString();
    }

    @Test
    public void test_3(){ 
	Random r = new Random();
	int r1 = r.nextInt(19) + 1;
	int r2 = r.nextInt(19) + 1;
	String feedback  = MessageFormat.format(Translator.translate("tous les arguments ne sont pas vérifiés ! Appelez divise avec tous les arguments !\n"), r1, r2);
	catcher(new TestArg(((Integer)r1).toString(), ((Integer)r2).toString(), feedback), 1); 
    }
    private class TestArg implements Callable<Void> {
	String a;
	String b;
	String feedbackAdd; 
	public TestArg(String a, String b, String feedback){
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
		if(!out.equals(outStudent)) {
			fail(test_name() + " : " + feedbackAdd);	
		}
		System.err.println(MessageFormat.format(Translator.translate("{0} : réussi"), test_name()));
	    	return null;
	}

    }
    public void catcher(Callable<Void> test, int n) {
        	try{
		    test.call();
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
		    }else if(t instanceof NegativeArraySizeException){
		        fail(Translator.translate("Vous initialisez un tableau avec une taille négative."));
		    }else if(t instanceof StackOverflowError){
		        fail(Translator.translate("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
		    }else{
		        fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + t.toString());
		    }
		}catch(Exception e){
		    fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
		}
    }
    private String test_name(){
        String s = name.getMethodName().replaceAll("_", " ");
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
	
}

