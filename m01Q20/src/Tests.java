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
    
    private String une_c = Translator.translate("une consonne");
    private String une_v = Translator.translate("une voyelle");
    private String un_c = Translator.translate("un chiffre");

    public void test(char chara){
        
        String reponse_etudiant = Etudiant.car(chara);
        String expected = Correction.car(chara);
        String feedbackBuilder = Translator.translate("''{0}'' est {1}. Cependant, votre code indique que c''est {2}.\n");
        
        String print_expected = Translator.translate(expected);
        if (expected.equals("consonne"))
            print_expected = une_c;
        if (expected.equals("voyelle"))
            print_expected = une_v;
        if (expected.equals("chiffre"))
            print_expected = un_c;
        
        String print_stu = Translator.translate(reponse_etudiant);
        if (reponse_etudiant.equals("consonne"))
            print_stu = une_c;
        if (reponse_etudiant.equals("voyelle"))
            print_stu = une_v;
        if (reponse_etudiant.equals("chiffre"))
            print_stu = un_c;
        
        String feedback = MessageFormat.format(feedbackBuilder, chara, print_expected, print_stu);
        assertTrue(feedback, expected.equals(reponse_etudiant));
    }
	
    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher(){
        try{
            String t = "aeioubcdfghjklmnpqrstvwxz0123456789"; //pas 'y' car controverse...
            for (int i = 0; i < t.length(); i++)
                test(t.charAt(i));
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
