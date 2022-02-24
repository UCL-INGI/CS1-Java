
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

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.text.MessageFormat;
import java.util.concurrent.Callable;

import student.Translations.Translator;
import StudentCode.*;

import src.librairies.*;

public class Tests{
    @Rule public TestName name = new TestName();
    
    @Test
    public void test_4(){ 
	catcher(new TestRead("testFullInvalid.dat", Translator.translate("votre méthode ne renvoie pas la bonne valeur du maximum sur un fichier complètement erroné.\n")), 1); 
    }
    @Test
    public void test_3(){ 
	catcher(new TestRead("testIncorrect.dat", Translator.translate("votre méthode ne renvoie pas la bonne valeur du maximum sur un fichier avec une ligne malformée contenant un maximum.\n")), 1); 
    }
    @Test
    public void test_2(){ 
	catcher(new TestRead("testErreur.dat", Translator.translate("votre méthode ne renvoie pas la bonne valeur du maximum sur un fichier avec une ligne malformée.\n")), 1); 
    }

    @Test
    public void test_1(){ 
	catcher(new TestRead("testCorrect.dat", Translator.translate("votre méthode ne renvoie pas la bonne valeur du maximum avec un fichier au bon format.\n")), 1); 
    }

    private class TestRead implements Callable<Void> {
	String file;
	String feedbackAdd;
	public TestRead(String file, String feedbackAdd){
		this.file = file;
		this.feedbackAdd = feedbackAdd; 
	}
        public Void call() { 
    		String feedback = MessageFormat.format(Translator.translate("{0} : raté : " + feedbackAdd), test_name());
		Double result = Correction.getMax(file);
		try{
			Double resultStudent = Etudiant.getMax(file);
			if(!result.equals(resultStudent)) fail(feedback);
		}catch(Exception e){
			fail(feedback + "votre code génère une exception !\n");
		}
		System.err.println(MessageFormat.format(Translator.translate("{0} : réussi\n"), test_name()));
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

