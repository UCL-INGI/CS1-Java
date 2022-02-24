
/**
 *  Copyright (c) Francois Michel, 2017 Brandon Naitali
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
import java.util.HashSet;

import static org.junit.Assert.*;
import org.junit.Test;

import java.text.MessageFormat;
import student.Translations.Translator;
import StudentCode.*;

import src.librairies.*;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class Tests{

    public Fraction getRandomFractionGorL(Fraction test2, boolean greater){
	int comp = greater? 1 : -1;
	Fraction test1 = getRandomFraction();
	while(Correction.Fraction.compareTo(test1,test2) != comp){
		test1 = getRandomFraction();
	}
	return test1;
    }

    public Fraction getRandomFraction(){
	Random r = new Random();
	int num = r.nextInt(9) + 1; // random entre 1 et 100
	int den = r.nextInt(9) + 1; // random entre 1 et 100
	return new Fraction(num, den);
    }

    public void testCompare(Fraction test1, Fraction test2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{ 
	        String feedback  =Translator.translate("Avec les fractions f1=\"{0}\" et f2=\"{1}\", la réponse attendue est\n{2}\nMalheureusement, votre code renvoie \n{3}\n");
		int result = Correction.Fraction.compareTo(test1,test2);
    		int resultStudent = (int) FunctionHelperCustom.run_instance_function("compareTo", test1, test2);
		if(!(result==resultStudent)){
			fail(MessageFormat.format(feedback, test1.toString(), test2.toString(), result, resultStudent));
		}
    }

    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher(){
	Random r = new Random();
        try{
            for(int i = 10; i > 0; i--){
			Fraction test= getRandomFraction();
			testCompare(test, test);
			testCompare(test, getRandomFractionGorL(test, true));
			testCompare(test, getRandomFractionGorL(test, false));
			testCompare(getRandomFraction(), getRandomFraction());
	    }
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
	
}

