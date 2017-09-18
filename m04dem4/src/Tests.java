
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
import org.junit.runner.notification.Failure;
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
            s += (char) ((r.nextInt('z' - 'a') + 'a'));
        }
        return s;
    }
    
    
    public void testStringLength(){
        String feedback  =_("Avec l''argument \"{0}\", la réponse attendue est\n{1}\nMalheureusement, votre code renvoie \n{2}\n");
        String test = generateString(3);
        String result = Correction.toUpper(test);
        String resultStudent = Etudiant.toUpper(test);
        if(!result.equals(resultStudent)){
            fail(MessageFormat.format(feedback, test, result, resultStudent));
        }
    }
    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher(){
        try{
            for(int i = 5; i > 0; i--)
                testStringLength();
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
