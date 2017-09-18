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
    
    public void test(double taille, int poids){
        String reponse_etudiant = Etudiant.quetelet(taille, poids);
        if(reponse_etudiant.equals("")){
            fail(_("Veuillez placer le résultat dans la variable")  + " 'etat'");
        }
        String expected = Correction.quetelet(taille, poids);
        String feedbackBuilder = _("Pour une personne de {0}m et {1}kg, votre réponse {2} ne correspond pas à la valeur attendue {3}.\n");
        String feedback = MessageFormat.format(feedbackBuilder, taille, poids, reponse_etudiant, expected);
        assertEquals(feedback, expected, reponse_etudiant);
    }
	
    /**
     *	Lanceur de test
     **/
    @Test
    public void test(){
        try{
            test(1.78, 80);
            test(1.78, 100);
            test(1.78, 50);
            test(1.78, 65);
            test(1.78, 150);
            
            //corner case, pout tester les < et les <=
            test(2, 80); //20
            test(2, 100); //25
            test(2, 120); //30

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
