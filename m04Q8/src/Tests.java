
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
    
    public void testStringContains(String testString, String testCharSet) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        String feedback  =Translator.translate("Avec les arguments s1=\"{0}\" et s2=\"{1}\", la réponse attendue est\n{2}\nMalheureusement, votre code renvoie \n{3}\n");
        boolean result = Correction.contains(testString, testCharSet);
        boolean resultStudent = (boolean) FunctionHelper.run_student_function("contains", testString, testCharSet);
        if(!(result==resultStudent)){
            fail(MessageFormat.format(feedback, testString, testCharSet, result, resultStudent));
        }
    }
    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher(){
        
        try{
            FunctionHelper.check_etudiant_function("StudentCode.Etudiant", "contains", boolean.class, new Class[]{String.class, String.class});
            for(int i = 10; i > 0; i--){
                String test = StringHelper.generateNumberOrLetter(10, false);
                testStringContains(test, StringHelper.getRandomSubstring(test));// Test d'un string qui contient l'autre
                testStringContains(StringHelper.generateNumberOrLetter(5, false), StringHelper.generateNumberOrLetter(5, false) ); // Test d'un string qui ne contient pas ou partiellement l'autre
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

