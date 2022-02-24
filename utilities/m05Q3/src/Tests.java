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
import java.text.MessageFormat;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import student.Translations.Translator;
import src.librairies.FunctionHelper;

import StudentCode.*;

public class Tests {
    
    private String noModif = Translator.translate("Votre méthode modifie le contenu du tableau passé en argument. Une telle modification n'est autorisée que si elle est explicitement indiquée dans les spécifications. Ce n'est pas le cas pour cette méthode.\n");
    
    public void test(double[] v) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        double[] origin = v.clone();
        double expected = Correction.mean(v);
        double reponse_etudiant = (double) FunctionHelper.run_student_function("mean", v);
        assertTrue(noModif, java.util.Arrays.equals(v,origin));
        String feedbackBuilder = Translator.translate("Avec le tableau {0}, votre méthode devrait retourner {1,number,#} mais elle retourne {2,number,#}.\n");
        String feedback = MessageFormat.format(feedbackBuilder, Arrays.toString(v), expected, reponse_etudiant);
        assertEquals(feedback, expected, reponse_etudiant, 0.01);
    }
    
    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher() {
        try{
            FunctionHelper.check_etudiant_function("StudentCode.Etudiant", "mean", double.class, new Class[]{double[].class});
            test(new double[] {1.0, 1.0, 1.0});
            test(new double[] {1.0, 0.0, 1.5});
            test(new double[] {-1.0, -1.0, -2.0});
            test(new double[] {1.0, 2.0, 2.0});
            test(new double[] {-7.0});
            test(new double[] {-1.0, -2.0});
            test(new double[] {0.0, 0.0});
            test(new double[] {1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 9.0, 1.0});
            test(new double[] {9.0, 1.0, 1.0, 99.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0});
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
