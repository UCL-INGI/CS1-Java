
/**
 *  Copyright (c)  2016 Ludovic Taffin, 2017 Brandon Naitali
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
import org.junit.runner.notification.Failure;

import java.text.MessageFormat;

import student.Translations.Translator;

import StudentCode.*;


public class Tests {
    private String msgFeedback = Translator.translate("Le nombre de diviseur de {0,number,#} est {1,number,#} or votre programme calcule {2,number,#}.\n");
    
    
    public void testNbrDiv(){
        Random r = new Random();
        int n = r.nextInt(1000) + 1; // On génère un random entre 1 et 1000;
        int result = Correction.divdist(n);
        int etudiantResult = Etudiant.divdist(n);
        String erreur = MessageFormat.format(msgFeedback, n, result, etudiantResult);
        assertTrue(erreur, result == etudiantResult);
    }

    @Test
    public void testLauncher(){
        int nbrTests = 3;
        try{
            for(int i = 0; i < nbrTests; i++){
                testNbrDiv();
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
