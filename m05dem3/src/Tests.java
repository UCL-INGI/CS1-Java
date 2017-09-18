/**
 *  Copyright (c)  2017 Olivier Martin
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
import static org.hamcrest.CoreMatchers.is;
import java.text.MessageFormat;
import java.util.Random;
import java.util.Arrays;
import static student.Translations.Translator._;

import StudentCode.*;

public class Tests {
    
    private String feedbackBuilder = _("Avec une taille de {0}, votre code génère :\n\n{1}\n\nOr, vous devriez avoir : \n\n{2}");
    private String feedbackBuilderSize = _("Votre matrice n'a pas la bonne taille.\n");
    
    public void test(int a){
        int[][] reponse_etudiant = Etudiant.unite(a);
        int[][] expected = Correction.unite(a);
        
        assertTrue(_("Votre code retourne null."), reponse_etudiant != null);
        assertEquals(feedbackBuilderSize, expected.length, reponse_etudiant.length);
        assertEquals(feedbackBuilderSize, expected[0].length, reponse_etudiant[0].length);

        if(! Arrays.deepEquals(reponse_etudiant, expected))
            fail(MessageFormat.format(feedbackBuilder, a, Arrays.deepToString(reponse_etudiant).replace("],", "],\n"), Arrays.deepToString(expected).replace("],", "],\n")));
    }
    
    /**
     *	Lanceur de test
     **/
    @Test
    public void test(){
        try{
            for(int i = 5; i > 0; i--)
                test(i);
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
        }catch(NegativeArraySizeException e){
            fail(_("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(_("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(_("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }
}
