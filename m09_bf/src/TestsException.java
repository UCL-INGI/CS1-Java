
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

import java.io.IOException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.File;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import java.security.Permission;

import java.text.MessageFormat;
import java.util.concurrent.Callable;

import static student.Translations.Translator._;
import StudentCode.*;

import src.librairies.*;

public class TestsException{
    private static SecurityManager oldSM = System.getSecurityManager();

    @BeforeClass
    public static void forbidSystemExitCall() {
        final SecurityManager securityManager = new SecurityManager() {
            public void checkPermission(Permission permission) {
                if (permission.getName().startsWith("exitVM")) {
                    throw new SecurityException("System.exit() not allowed");
                }
            }
        };
        System.setSecurityManager(securityManager);
    }

    @AfterClass
    public static void enableSystemExitCall() {
        System.setSecurityManager(oldSM);
    }
    
    @Rule public TestName name = new TestName();

    @Test
    public void test_5(){ 
	String file1 = "IOException.txt";
	try{
		File f1 = new File(file1);
		// Les fichiers doivent être créés
		f1.createNewFile(); 
		// Changement de permission
		f1.setReadable(false);
	}catch(IOException e){
	    fail(_("Erreur de lecture de fichier dans le test de l'IOException\n"));
	}
	catcher(new TestEx(file1, _("votre méthode ne gère pas les IOExceptions.\n")), 1); 
    }

    private class TestEx implements Callable<Void> {
	String file;
	String feedbackAdd;
	public TestEx(String file, String feedbackAdd){
		this.file = file;
		this.feedbackAdd = feedbackAdd; 
	}
        public Void call(){ 
	    	String feedback = MessageFormat.format(_("{0} : raté : "), test_name()) + feedbackAdd;
		// On redirige la sortie d'erreur de l'étudiant pour ne pas l'avoir dans le feedback
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        	PrintStream ps = new PrintStream(baos);
        	PrintStream old = System.err;
        	System.setErr(ps);
		try{
			Etudiant.getMax(file);
			System.setErr(old);
			System.err.println(MessageFormat.format(_("{0} : réussi\n"), test_name()));
		}catch(IOException e){
			System.setErr(old);
			fail(feedback);
		}catch(Exception e){
			System.setErr(old);
			fail(MessageFormat.format(_("{0} : raté : votre programme génère une exception non gérée : " + e.toString() + "\n"), test_name()));
		}
		return null;
	}
    }

    public void catcher(Callable<Void> test, int n) {
        	try{
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

    private String test_name(){
        String s = name.getMethodName().replaceAll("_", " ");
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
	
}

