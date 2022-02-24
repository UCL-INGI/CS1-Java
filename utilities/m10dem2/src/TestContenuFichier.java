
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
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.Random;
import java.util.LinkedList;

import student.Translations.Translator;
import StudentCode.*;

import src.librairies.*;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class TestContenuFichier{
    @Rule public TestName name = new TestName();

    String feedBackAjout  = Translator.translate("Vous n'écrivez pas tous les n premiers entiers positifs ou vous ne fermez votre flux.\n");
    String feedBackAjout2  = Translator.translate("Vous n'écrivez pas tous les n premiers entiers positifs");
    String feedBackDelete  = Translator.translate("Vous écrivez plus d'un entier par ligne.\n");
    String feedBackDelete2  = Translator.translate("vous écrivez plus d'un entier par ligne.\n");

    public String readFile(String file){
	String content = "";
	try{
		InputStream in=new FileInputStream(file); 
		InputStreamReader r=new InputStreamReader(in);
		BufferedReader bf=new BufferedReader(r);
		String ligne;
		while ((ligne=bf.readLine())!=null){
			content+=ligne;
			content+="\n";
		}
		bf.close(); 
		return content;
	}		
	catch (IOException e){
		fail(test_name() + "  : " + e.toString());
		return null;

	}
    }

    @Test
    public void test_1(){ 
	catcher(new TestWrite("correction.txt", "etudiant.txt"), 1); 
    }

    private class TestWrite implements Callable<Void> {
	String file;
	String fileStudent;

	public TestWrite(String file, String fileStudent){
		this.file = file;
		this.fileStudent = fileStudent;
	}
        public Void call() throws Exception{ 
		Random r = new Random();
		int times = r.nextInt(10)+5;
		try{
			File f1 = new File(file);
			File f2 = new File(fileStudent);
			// Les fichiers doivent être créés 
			f1.createNewFile(); 
			f2.createNewFile();

			Correction.write(times, file);
			String result= readFile(file);
			Etudiant.write(times, fileStudent);
			String resultStudent = readFile(fileStudent);
			diff_match_patch difference = new diff_match_patch();
    			LinkedList<diff_match_patch.Diff> deltas = difference.diff_main(resultStudent, result);

			if(deltas.size() == 1 ){
				if(deltas.get(0).operation != diff_match_patch.Operation.INSERT && deltas.get(0).operation != diff_match_patch.Operation.DELETE){
					System.err.println(MessageFormat.format(Translator.translate("{0} : réussi"), test_name()));
					return null;		
				}
			}
			boolean aRetirer = false; 
			boolean aAjouter = false; 
			for(diff_match_patch.Diff d: deltas){
				if(d.operation == diff_match_patch.Operation.INSERT)
					aAjouter = true;
				if(d.operation == diff_match_patch.Operation.DELETE)
					aRetirer = true;
			}
			if(aRetirer && aAjouter) 
				fail(test_name() + " : " + feedBackAjout2 + Translator.translate(" et ") + feedBackDelete2 + "\n" );
			if(aRetirer) 
				fail(test_name() + " : " + feedBackDelete + "\n" );
			if(aAjouter) 
				fail(test_name() + " : " + feedBackAjout + "\n" );

		}catch(Exception e){
				throw e;
		}
		return null;
	}
    }

    public void catcher(Callable<Void> test, int n) {
        	try{
		    test.call();
		}catch (ArithmeticException e){
		    fail(Translator.translate("Attention, il est interdit de diviser par zéro."));
		}catch(ClassCastException e){
		    fail(Translator.translate("Attention, certaines variables ont été mal castées !"));
		}catch(StringIndexOutOfBoundsException e){
		    fail(Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
		}catch(ArrayIndexOutOfBoundsException e){
		    fail(Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
		}catch(NullPointerException e){
		    fail(Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
		}catch(NegativeArraySizeException e){
		    fail(Translator.translate("Vous initialisez un tableau avec une taille négative."));
		}catch(StackOverflowError e){
		    fail(Translator.translate("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
		}catch(Exception e){
		    fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
		}
    }

    private String test_name(){
        String s = name.getMethodName().replaceAll("_", " ");
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
	
}

