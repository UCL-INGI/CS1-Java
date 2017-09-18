
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
import static student.Translations.Translator._;
import StudentCode.*;

public class Tests{
    
    /**
     * 	@pre	-
     * 	@post	Génère un String aléatoirement, de taille length.
     */
    public static String generateString(int length){
        String s = "";
        Random r = new Random();
        for(int i = 0 ; i < length ; i++){
            char random = (char) ((r.nextInt('z' - 'a') + 'a'));
            if(!s.contains(String.valueOf(random)))
                s += random;
            else{
                i--;
            }
        }
        return s;
    }
    
    /**
     * 	@pre	-
     * 	@post	Génère un shuffle du string s.
     */
    public static String getShuffle(String s){
        String toReturn = "";
        Random r = new Random();
        for(int i = 0 ; i < s.length() ; i++){
            char randomChar = s.charAt(r.nextInt(s.length()));
            if(!toReturn.contains(String.valueOf(randomChar))){
                toReturn += randomChar;
            }
            else{
                i--;
            }
        }
        return toReturn;
    }
    
    
    public void testStringContains(String testString, String testCharSet){
        String feedback  =_("Avec les arguments s1=\"{0}\" et s2=\"{1}\", la réponse attendue est\n{2}\nMalheureusement, votre code renvoie \n{3}\n");
        boolean result = Correction.containsChar(testCharSet, testString);
        boolean resultStudent = Etudiant.containsChar(testCharSet, testString);
        if(!(result==resultStudent)){
            fail(MessageFormat.format(feedback, testCharSet, testString, result, resultStudent));
        }
    }
    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher(){
        try{
            for(int i = 10; i > 0; i--){
                String test = generateString(5);
                testStringContains(test, getShuffle(test));// Test d'un string qui contient l'autre
                testStringContains(generateString(5), generateString(5) ); // Test d'un string qui ne contient pas ou partiellement l'autre
            }
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
}

