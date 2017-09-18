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

public class TestsQ2 {
    
    private String qN = "@2 :\n"; //No translations needed
    private String feedbackBuilder = qN + _("L''appel de votre méthode avec v = {0} doit donner {1}.\nOr, vos modifications du tableau donnent {2}");
    
    public void test(int[] v) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
        int[] copy_student = v.clone();
        int[] copy_corr = v.clone();

        FunctionHelper.run_student_function("oppose2", copy_student);
        Correction.oppose2(copy_corr);
        
        String feed = MessageFormat.format(feedbackBuilder, Arrays.toString(v), Arrays.toString(copy_corr), Arrays.toString(copy_student));
        assertTrue(feed, Arrays.equals(copy_corr, copy_student));
    }
    
    @Test
    public void testLauncherQ2() {
        try{
            FunctionHelper.check_etudiant_function_with_subquestion("StudentCode.Etudiant", "oppose2", void.class, new Class[]{int[].class}, qN);
            test(new int[] {5,0,4,1,2});
            test(new int[] {0,1,2,3,4});
            test(new int[] {2,-2,0,1,-1});
            test(new int[] {-5, 4});
            test(new int[] {1});
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
