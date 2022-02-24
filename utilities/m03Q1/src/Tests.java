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
import student.Translations.Translator;
import src.librairies.FunctionHelper;

import StudentCode.*;

public class Tests {
    
    private String est = Translator.translate("est");
    private String est_not = Translator.translate("n'est pas");
    private String feedbackBuilder = Translator.translate("Le nombre {0} {1} dans l''intervalle [{2}, {3}].\nCependant, votre code indique le contraire.\n");
    
    public void test(int a, int b, int x) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
        boolean reponse_etudiant = (boolean) FunctionHelper.run_student_function("intervalle", a, b, x);
        boolean expected = Correction.intervalle(a, b, x);
        
        String is;
        if (expected)
            is = est;
        else
            is = est_not;
        
        String feedback = MessageFormat.format(feedbackBuilder, x, is, a, b);
        assertTrue(feedback, expected == reponse_etudiant);
    }
    
    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher() {
        try{
            FunctionHelper.check_etudiant_function("StudentCode.Etudiant", "intervalle", boolean.class, new Class[]{int.class, int.class, int.class});
            // Nombres positifs
            test(2,8,2);
            test(2,8,8);
            test(2,8,5);
            test(2,8,1);
            test(2,8,9);
            
            // Nombres positifs et négatifs
            test(-5,5,-5);
            test(-5,5,5);
            test(-5,5,0);
            test(-5,5,-8);
            test(-5,5,8);
            
            // Nombres négatifs
            test(-8,-2,8);
            test(-8,-2,-2);
            test(-8,-2,0);
            test(-8,-2,-4);
            test(-8,-2,-10);
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
