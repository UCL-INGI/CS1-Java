/**
 *  Copyright (c)  François MICHEL & Clémentine MUNYABARENZI, 2017 Brandon NAITALI
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

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.text.MessageFormat;

import static student.Translations.Translator._;

import StudentCode.*;

public class Tests {
    private String msgFeedback = _("Test {0} : La somme des {1,number,#} entiers pairs supérieurs à zéro vaut {2,number,#} et votre programme calcule {3,number,#}.\n");
    static int count = 1; // Compteur de tests
    
    public void testSommeEntiersPairs(int n){
        int result = Correction.sommeEntiersPairs(n);
        int etudiantResult = Etudiant.sommeEntiersPairs(n);
        String erreur = MessageFormat.format(msgFeedback, count, n, result, etudiantResult);
        assertEquals(erreur, result, etudiantResult);
        count++;
    }
    
    @Test
    public void testLauncher(){
        Random r = new Random();
        try{
            testSommeEntiersPairs(0);
            testSommeEntiersPairs(1);
            testSommeEntiersPairs(r.nextInt(330) + 2); // On génère un random entre 2 et 332
            testSommeEntiersPairs(r.nextInt(665-333) + 333); // random entre 333 et 665
            testSommeEntiersPairs(r.nextInt(1000-665)+ 666); // random entre 666 et 1000
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
