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
    
    public void test(int a, int b, int c){
        int reponse_etudiant = Etudiant.diff(a, b, c);
        int expected = Correction.diff(a, b, c);
        String feedbackBuilder = Translator.translate("Il y a {0} valeurs différentes parmi {1}, {2}, {3}.\nCependant, votre code indique qu''il y en a {4}.\n");
        String feedback = MessageFormat.format(feedbackBuilder, expected, a, b, c, reponse_etudiant);
        assertTrue(feedback, expected == reponse_etudiant);
    }
	
    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher(){
        try{
            test(1,2,3);
            test(5,5,9);
            test(2,3,3);
            test(6,6,6);
            test(-9,-6,-3);
            test(0,1,0);
            test(1,-2,3);
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