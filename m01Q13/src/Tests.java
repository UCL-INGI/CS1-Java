/**
 *  Copyright (c)  2016 Ludovic Taffin, 2017 Olivier Martin
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
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.Test;
import java.text.MessageFormat;

import student.Translations.Translator;

import StudentCode.*;

public class Tests {
    
    public void test_minimum(int a, int b, int c, int d){
        int reponse_etudiant = Etudiant.min(a, b, c, d);
        if(reponse_etudiant == Integer.MAX_VALUE){
            fail(Translator.translate("Veuillez placer le résultat dans la variable")  + " 'min'");
        }
        
        int expected = Correction.min(a, b, c, d);
        String feedbackBuilder = Translator.translate("Le minimum entre les variables a={0}, b={1}, c={2} et d={3} est {4} or votre programme calcule {5}.\n");
        String feedback = MessageFormat.format(feedbackBuilder, a, b, c, d, expected, reponse_etudiant);
        assertTrue(feedback, expected == reponse_etudiant);
    }
	
    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher(){
        try{
            test_minimum(5, 10, 15, 20);
            test_minimum(0, 15, -10, 5);
            test_minimum(-5, -10, -15, -20);
            test_minimum(-1, 0, 1, -2);
            test_minimum(3, 2, 1, 0);
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
        }catch(Exception e){
            fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }
}
