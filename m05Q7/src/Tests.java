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
import org.junit.Test;
import java.text.MessageFormat;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static student.Translations.Translator._;
import src.librairies.FunctionHelper;

import StudentCode.*;

public class Tests {
    
    private int[][] array_if_exception;
    
    private String feedbackBuilder = _("Votre code retourne {0} à la place de {1} avec l''occurence {2} et le tableau :\n\n{3}\n");
    private String noModif = _("Votre méthode modifie le contenu du tableau passé en argument. Une telle modification n'est autorisée que si elle est explicitement indiquée dans les spécifications. Ce n'est pas le cas pour cette méthode.\n");
    
    public void test(int occurence, int[][] array) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
        //No modify array
        int[][] origin = new int[array.length][array[0].length];
        for (int i = 0; i < origin.length; i++)
            origin[i] = Arrays.copyOf(array[i], array[i].length);
        
        array_if_exception = origin;
        
        int reponse_etudiant = (int) FunctionHelper.run_student_function("count", occurence, array);
        assertTrue(noModif, java.util.Arrays.deepEquals(array,origin));
        
        int expected = Correction.count(occurence, array);
        String feed = MessageFormat.format(feedbackBuilder, reponse_etudiant, expected, occurence, Arrays.deepToString(origin).replace("],", "],\n"));
            assertEquals(feed, expected, reponse_etudiant);
    }
    
    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher() {
        try{
            FunctionHelper.check_etudiant_function("StudentCode.Etudiant", "count", int.class, new Class[]{int.class, int[][].class});
            test(2, new int[][] { {1, 2}, {3, 4} });
            test(0, new int[][] { {1, 2}, {3, 4} });
            test(2, new int[][] { {1, 2}, {3, 2} });
            test(1, new int[][] { {1} });
            test(0, new int[][] { {1} });
            test(5, new int[][] { {1, 2}, {3, 4, 5} });
            test(5, new int[][] { {1, 5, 5}, {5, 4} });
            test(1, new int[][] { {1, 2, 3, 4, 5, 1}, {1, 2, 1} });
        }catch (InvocationTargetException e){
            Throwable t = e.getCause();
            if(t instanceof ArithmeticException){
                fail(_("Attention, il est interdit de diviser par zéro."));
            }else if(t instanceof ClassCastException){
                fail(_("Attention, certaines variables ont été mal castées !"));
            }else if(t instanceof StringIndexOutOfBoundsException){
                fail(_("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
            }else if(t instanceof ArrayIndexOutOfBoundsException){
                // Cette exercice utilise un tableau à deux dimensions. Les etudiants se trompent souvent dans la condition de la
                // deuxième boucle. Il peut être interessant de leur montrer le tableau en cas d'ArrayIndexOutOfBoundsException.
                fail(_("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)") + "\n" + _("Cette exception est survenue quand le tableau est :\n") + Arrays.deepToString(array_if_exception).replace("],", "],\n"));
            }else if(t instanceof NullPointerException){
                fail(_("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
            }else if(t instanceof NegativeArraySizeException){
                fail(_("Vous initialisez un tableau avec une taille négative."));
            }else if(t instanceof StackOverflowError){
                fail(_("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
            }else{
                fail(_("Une erreur inattendue est survenue dans votre tâche : ") + t.toString());
            }
        }catch(Exception e){
            fail(_("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }
}
