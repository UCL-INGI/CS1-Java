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

import student.Translations.Translator;

import StudentCode.*;

public class Tests {
    
    public void test(int jour, int mois){
        String reponse_etudiant = Etudiant.sais(jour, mois);
        String expected = Correction.sais(jour, mois);
        String feedbackBuilder = Translator.translate("Pour le {0}/{1}/2016 la saison est ''{2}'' alors que votre code indique ''{3}''.\n");
        String feedback = MessageFormat.format(feedbackBuilder, jour, mois, expected, reponse_etudiant);
        assertTrue(feedback, expected.equals(reponse_etudiant));
    }
	
    /**
     *	Lanceur de test
     **/
    @Test
    public void test(){
        try{
            test(2, 2);
            test(16, 5);
            test(15, 7);
            test(1, 10);
            
            test(20, 3);
            test(20, 6);
            test(22, 9);
            test(21, 12);
            
            test(19, 3);
            test(19, 6);
            test(21, 9);
            test(20, 12);
            
            Random r = new Random();
            test(r.nextInt(28)+1, r.nextInt(12)+1);
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
