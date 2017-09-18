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

import static student.Translations.Translator._;

import StudentCode.*;

public class Tests {
    
    public void test_maximum(int a, int b, int c){
        int reponse_etudiant = Etudiant.max(a, b, c);
        // 'maximum' est initialisé à Integer.MIN_VALUE pour l'étudiant.
        if(reponse_etudiant == Integer.MIN_VALUE){
            fail(_("Veuillez placer le résultat dans la variable")  + " 'maximum'");
        }

        int expected = Correction.max(a, b, c);
        String feedbackBuilder = _("Le maximum entre les variables a={0}, b={1} et c={2} est {3} or votre programme calcule {4}.\n");
        String feedback = MessageFormat.format(feedbackBuilder, a, b, c, expected, reponse_etudiant);
        assertEquals(feedback, expected, reponse_etudiant, 0.001);
    }
	
    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher(){
        try{
            test_maximum(5, 10, 15);
            test_maximum(0, 15, -10);
            test_maximum(-5, -10, -15);
            test_maximum(-1, 0, 1);
            test_maximum(3, 2, 1);
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
        }catch(Exception e){
            fail(_("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }
}
