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
    
    private String feedbackBuilder = _("L''appel de votre méthode avec n = {0} et v = {1}\nretourne {2} au lieux de {3}");
    private String noModif = _("Votre méthode modifie le contenu du tableau passé en argument. Une telle modification n'est autorisée que si elle est explicitement indiquée dans les spécifications. Ce n'est pas le cas pour cette méthode.\n");
    public void test(int n, int[] v) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
        //Copy for student
        int[] copy_student = v.clone();
        
        int[] response = (int[]) FunctionHelper.run_student_function("top", n, v);
        
        assertTrue(noModif, java.util.Arrays.equals(copy_student, v));

        int[] expected = Correction.top(n, v);
        
        String feed = MessageFormat.format(feedbackBuilder, n, Arrays.toString(v), Arrays.toString(response), Arrays.toString(expected));
        assertTrue(feed, Arrays.equals(expected, response));
    }
    
    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher() {
        try{
            FunctionHelper.check_etudiant_function("StudentCode.Etudiant", "top", int[].class, new Class[]{int.class, int[].class});
            
            test(3, new int[] {5,0,4,1,2});
            test(1, new int[] {5,3});
            test(2, new int[] {5,1,3});

            test(2, new int[] {3,2,1});
            test(2, new int[] {2,0,-4});
            test(1, new int[] {3,2,1});
            test(2, new int[] {-1,-2,-3});
            test(2, new int[] {Integer.MIN_VALUE+5, Integer.MIN_VALUE+7, Integer.MIN_VALUE+1});
            test(3, new int[] {5,1,3,7,0});

        }catch (InvocationTargetException e){
            Throwable t = e.getCause();
            if(t instanceof ArithmeticException){
                fail(_("Attention, il est interdit de diviser par zéro."));
            }else if(t instanceof ClassCastException){
                fail(_("Attention, certaines variables ont été mal castées !"));
            }else if(t instanceof StringIndexOutOfBoundsException){
                fail(_("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
            }else if(t instanceof ArrayIndexOutOfBoundsException){
                fail(_("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
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
