
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

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.nio.file.attribute.PosixFilePermissions;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.Random;;

import java.text.MessageFormat;
import static student.Translations.Translator._;
import StudentCode.*;

import src.librairies.*;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class TestValeurRetour{
    @Rule public TestName name = new TestName();

    @Test
    public void test_2()throws Exception{ 
	String feedback1  = _("La valeur de retour de votre programme n'est pas correcte dans le cas où il y a une IOException.");

	// Noms des fichiers de tests
	String file1 = "correction3.txt";
	String file2 = "etudiant3.txt";
	File f1 = new File(file1);
	File f2 = new File(file2);

	// Les fichiers doivent être créés
	f1.createNewFile(); 
	f2.createNewFile();
	
	// Changement de permission
	f1.setWritable(false);
	f2.setWritable(false);
	catcher(new TestWrite(file1, file2, feedback1), 1); 
	
    }
    
    @Test
    public void test_1(){ 
	String feedback1  = _("La valeur de retour de votre programme n'est pas correcte dans le cas où tout se déroule bien.");
	catcher(new TestWrite("correction2.txt", "etudiant2.txt", feedback1), 1); 
    }

    private class TestWrite implements Callable<Void> {
	String file;
	String fileStudent;
	String feedbackAdd; 

	public TestWrite(String file, String fileStudent, String feedbackIn){
		this.file = file;
		this.fileStudent = fileStudent;
		this.feedbackAdd = feedbackIn;
	}
        public Void call() throws Exception{ 
		Random r = new Random();
		int times = r.nextInt(10)+5;
		try{
			int cor = Correction.write(times, file);
			int etu = Etudiant.write(times, fileStudent);
			if(cor!=etu) fail(test_name() + " : " + feedbackAdd);

		}catch(NullPointerException e){
			fail(test_name() + " : " + feedbackAdd);
		}catch(Exception e){
			throw e;
		}
		System.err.println(MessageFormat.format(_("{0} : réussi"), test_name()));
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

