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
import java.util.Random;
import java.text.MessageFormat;

import static student.Translations.Translator._;

import StudentCode.*;

public class Tests {
    
    private String feedbackBuilder = _("Lorsque la voiture entre en section {0} et sort en section {1}, la voiture a pacouru {2} section(s) et le prix attendu est {3}€ alors que votre code calcule {4}€.\n");

    public void test(int s_in, int s_out){
        double reponse_etudiant = Etudiant.prix(s_in, s_out);
        double expected = Correction.prix(s_in, s_out);
        String feedback = MessageFormat.format(feedbackBuilder, s_in, s_out, (s_out-s_in+1), expected, reponse_etudiant);
        assertEquals(feedback, expected, reponse_etudiant, 0.001);
    }
	
    /**
     *	Lanceur de test
     **/
    @Test
    public void test(){
        try{
            Random r = new Random();
            test(1, r.nextInt(6)+2);
            test(1, r.nextInt(12)+6);
            test(3, 5);
            test(2, 6);
            test(2, 2);
            test(9, 9);
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
