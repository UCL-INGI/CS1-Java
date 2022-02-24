
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
import java.util.Random;

import java.text.MessageFormat;
import student.Translations.Translator;
import StudentCode.*;


public class Tests{
    @Rule public TestName name = new TestName();
    public String generateFakeRandomFraction(){
	Random r = new Random();
	int r1 = r.nextInt(9) + 1;
	int r2 = r.nextInt(9) + 1;
	return r1 + "\\" + r2;
    }
    public String generateRandomFraction(boolean isInvalid){
	Random r = new Random();
	int r1 = r.nextInt(9) + 1;
	int r2 = isInvalid? 0 : r.nextInt(9) + 1;
	return r1 + "/" + r2;
    }
    @Test
    public void test_4(){ 
	String feedback  = MessageFormat.format(Translator.translate("{0} : le test avec une fraction avec 0 au dénominateur ne fonctionne pas : "), test_name());
	catcher(new TestException(generateRandomFraction(true), feedback), 1); 
    }
    @Test
    public void test_3(){ 
	String feedback  = MessageFormat.format(Translator.translate("{0} : le test avec un string null ne fonctionne pas : "), test_name());
	catcher(new TestException(null, feedback), 1); 
    }
    @Test
    public void test_2(){ 
	String feedback  = MessageFormat.format(Translator.translate("{0} : le test avec un string non-conforme au format \"a/b\" ne fonctionne pas : "), test_name());
	catcher(new TestException(generateFakeRandomFraction(), feedback), 1); 
    }
    private class TestException implements Callable<Void> {
	String a;
	String feedbackAdd; 
	String feedbackBad = Translator.translate("lancez-vous la bonne exception ? \n");
	String feedbackBad2 = Translator.translate("vérifiez que vous gérez bien ce cas. \n");
	public TestException(String a, String feedback){
		this.a = a;
		this.feedbackAdd = feedback;
	}
        public Void call(){ 
		try{
			Etudiant s= new Etudiant();
			Etudiant.Fraction f= s.new Fraction(a);
			fail(feedbackAdd + feedbackBad2);
		}catch(NumberFormatException e){
			System.err.println(MessageFormat.format(Translator.translate("{0} : réussi"), test_name()));
		}catch(Exception e){
			fail(feedbackAdd + feedbackBad);
		}
	    	return null;
	}
    }
    @Test
    public void test_1(){ 
	String feedback  =  MessageFormat.format(Translator.translate("{0} : le test avec un string correct ne fonctionne pas : "), test_name());
	catcher(new TestParsing(generateRandomFraction(false), feedback), 1); 
    }
    private class TestParsing implements Callable<Void> {
	String a;
	String feedbackAdd; 
	String feedbackBad = Translator.translate("respectez vous le format \"a/b\" ?\n");
	String feedbackBad2 = Translator.translate("modifiez-vous les variables d'instances de la classe Fraction ?\n");
	public TestParsing(String a, String feedback){
		this.a = a;
		this.feedbackAdd = feedback;
	}
        public Void call(){ 
		try{
			Correction co= new Correction();
			Etudiant s= new Etudiant();
			Correction.Fraction c = co.new Fraction(a);
			Etudiant.Fraction e = s.new Fraction(a);
			if(c.getDen() != e.getDen() || c.getNum() != e.getNum()) fail(feedbackAdd + feedbackBad2);
			System.err.println(MessageFormat.format(Translator.translate("{0} : réussi"), test_name()));
		}catch(Exception e){
			fail(feedbackAdd + feedbackBad);
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
            //fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }
    private String test_name(){
        String s = name.getMethodName().replaceAll("_", " ");
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
	
}

