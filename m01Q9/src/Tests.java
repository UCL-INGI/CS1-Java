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

import static student.Translations.Translator._;

import StudentCode.*;

public class Tests {
    
    private String est = _("est");
    private String est_not = _("n'est pas");

    private String feedbackBuilder = _("Le nombre {0} {1} dans l''intervalle [{2}, {3}].\nCependant, votre code indique le contraire.\n");
    
    public void test(int a, int b, int x){
        boolean reponse_etudiant = Etudiant.inout(a, b, x);
        boolean expected = Correction.inout(a, b, x);
        
        String is;
        if (expected)
            is = est;
        else
            is = est_not;
        
        String feedback = MessageFormat.format(feedbackBuilder, x, is, a, b);
        assertEquals(feedback, expected, reponse_etudiant);
    }
    
    /**
     *	Lanceur de test
     **/
    @Test
    public void test(){
        try{
            // Nombres positifs
            test(2,8,2);
            test(2,8,8);
            test(2,8,5);
            test(2,8,1);
            test(2,8,9);
        
            // Nombres positifs et négatifs
            test(-5,5,-5);
            test(-5,5,5);
            test(-5,5,0);
            test(-5,5,-8);
            test(-5,5,8);
            
            // Nombres négatifs
            test(-8,-2,8);
            test(-8,-2,-2);
            test(-8,-2,0);
            test(-8,-2,-4);
            test(-8,-2,-10);
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
