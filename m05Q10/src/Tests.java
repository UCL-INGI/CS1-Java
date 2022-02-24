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

import student.Translations.Translator;
import src.librairies.FunctionHelper;

import StudentCode.*;

public class Tests {
    
    private String feedbackBuilder = Translator.translate("L''appel de votre méthode avec a = {0} et b = {1}\nretourne {2} au lieux de {3}");
    private String noModif = Translator.translate("Votre méthode modifie le contenu du tableau passé en argument. Une telle modification n'est autorisée que si elle est explicitement indiquée dans les spécifications. Ce n'est pas le cas pour cette méthode.\n");
    public void test(double[] a, double[] b) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
        //Copy for student
        double[] copy_student_a = a.clone();
        double[] copy_student_b = b.clone();
        
        double[] response = (double[]) FunctionHelper.run_student_function("somme", copy_student_a, copy_student_b);
        
        assertTrue(noModif, java.util.Arrays.equals(copy_student_a, a));
        assertTrue(noModif, java.util.Arrays.equals(copy_student_b, b));

        double[] expected = Correction.somme(a, b);
        
        String feed = MessageFormat.format(feedbackBuilder, Arrays.toString(a), Arrays.toString(b), Arrays.toString(response), Arrays.toString(expected));
        assertTrue(feed, Arrays.equals(expected, response));
    }
    
    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher() {
        try{
            FunctionHelper.check_etudiant_function("StudentCode.Etudiant", "somme", double[].class, new Class[]{double[].class, double[].class});
            
            test(new double[] { 5,6,6 }, new double[] { 5,6,3 });
            test(new double[] { 0,0,0 }, new double[] { 0,0,0 });
            test(new double[] { 1,3,5 }, new double[] { 0,-3,0 });
            test(new double[] { 1,-11,11 }, new double[] { 0,10,-10 });

        }catch (InvocationTargetException e){
            Throwable t = e.getCause();
            if(t instanceof ArithmeticException){
                fail(Translator.translate("Attention, il est interdit de diviser par zéro."));
            }else if(t instanceof ClassCastException){
                fail(Translator.translate("Attention, certaines variables ont été mal castées !"));
            }else if(t instanceof StringIndexOutOfBoundsException){
                fail(Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
            }else if(t instanceof ArrayIndexOutOfBoundsException){
                fail(Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
            }else if(t instanceof NullPointerException){
                fail(Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
            }else if(t instanceof NegativeArraySizeException){
                fail(Translator.translate("Vous initialisez un tableau avec une taille négative."));
            }else if(t instanceof StackOverflowError){
                fail(Translator.translate("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
            }else{
                fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + t.toString());
            }
        }catch(Exception e){
            fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }
}
