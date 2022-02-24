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
import java.util.Random;

import student.Translations.Translator;

import StudentCode.*;

public class Tests {
    
    private int[] values_for_test = {2016, 2015, 1990, 2000, 1900, 2042, 2020, 2024, 2028, 2032, 100, 400, 4};
    private String est = Translator.translate("est");
    private String est_not = Translator.translate("n'est pas");
    private String feedbackBuilder = Translator.translate("L''année {0,number,#} {1} une année bissextile.\nCependant, votre code indique que {0,number,#} {2} une année bissextile\n");

    public void test(int annee){
        boolean reponse_etudiant = Etudiant.bissextile(annee);
        boolean expected = Correction.bissextile(annee);
        
        // For nice feedback
        String student_is_bissextile;
        if (reponse_etudiant)
            student_is_bissextile = est;
        else
            student_is_bissextile = est_not;
        
        String expected_is_bissextile;
        if (expected)
            expected_is_bissextile = est;
        else
            expected_is_bissextile = est_not;
        
        String feedback = MessageFormat.format(feedbackBuilder, annee, expected_is_bissextile, student_is_bissextile);
        assertTrue(feedback, expected == reponse_etudiant);
    }
	
    /**
     *	Lanceur de test
     **/
    @Test
    public void test(){
        try{
            for (int i : values_for_test){
                test(i);
            }
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
