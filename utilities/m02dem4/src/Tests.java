/**
 *  Copyright (c)  François MICHEL, 2017 Brandon NAITALI
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

public class Tests{
    static int compteurTest = 1;
    private String msgFeedback = Translator.translate("Test {0} : le plus grand diviseur entier de {1,number,#} est {2,number,#} et votre programme calcule {3,number,#}.\n");
    
    public void testBiggestDivisor(int n){
        int result = Correction.biggestDivisor(n);
        int etudiantResult = Etudiant.biggestDivisor(n);
        String erreur = MessageFormat.format(msgFeedback, compteurTest, n, result, etudiantResult);
        assertTrue(erreur, result == etudiantResult);
        compteurTest++;
    }
    @Test
    public void testLauncher(){
        Random r = new Random();
        try{
            for(int i = 0; i < 50; i++)
                testBiggestDivisor(i);
            testBiggestDivisor(r.nextInt(950) + 50); // random entre 50 et 1000
            testBiggestDivisor(r.nextInt(950) + 50);
            testBiggestDivisor(r.nextInt(950) + 50);
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
