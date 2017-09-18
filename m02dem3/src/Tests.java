
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
import org.junit.runner.notification.Failure;

import java.text.MessageFormat;

import static student.Translations.Translator._;

import java.util.Random;
import StudentCode.*;


public class Tests{
    static int compteurTest = 1;
    
    private String msgFeedback = _("Test {0} : l''entier {1,number,#} exposant {2,number,#} vaut {3,number,#} et votre programme calcule {4,number,#}.\n");
    
    public void testExp(int a, int n){
        int result =(int) Correction.pow(a, n);
        int etudiantResult = Etudiant.pow(a, n);
        String erreur = MessageFormat.format(msgFeedback, compteurTest, a, n, result, etudiantResult);
        assertEquals(erreur, result, etudiantResult);
        compteurTest++;
    }
    @Test
    public void testLauncher(){
        Random r = new Random();
        try{
            // Test de la base de l'exposant
            testExp(0, r.nextInt(8) + 2);	// cas 0
            testExp(1, r.nextInt(8) + 2); // cas 1
            testExp(r.nextInt(8) + 2, 5); // random entre 2 et 10
            testExp(r.nextInt(8) + 2, 5);
            testExp(r.nextInt(8) + 2, 5);
            
            // Test de l'exposant
            testExp(r.nextInt(8) + 2, 0); // cas 0
            testExp(r.nextInt(8) + 2, 1); // cas 1
            testExp(5, r.nextInt(8) + 2);
            testExp(5, r.nextInt(8) + 2);
            testExp(5, r.nextInt(8) + 2);
            
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
