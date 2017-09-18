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
import java.util.Random;
import static student.Translations.Translator._;
import src.librairies.FunctionHelper;

import StudentCode.*;

public class Tests {
    
    public void test(int a, int b, int c) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
        int reponse_etudiant = (int) FunctionHelper.run_student_function("mediane", a, b, c);
        int expected = Correction.mediane(a, b, c);
        String feedbackBuilder = _("La mediane entre {0}, {1} et {2} vaut {3}, or votre programme calcule {4}.\n");
        String feedback = MessageFormat.format(feedbackBuilder, a, expected, reponse_etudiant);
        assertEquals(MessageFormat.format(feedbackBuilder, a, b, c, expected, reponse_etudiant), expected, reponse_etudiant);
    }
    
    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher() {
        try{
            FunctionHelper.check_etudiant_function("StudentCode.Etudiant", "mediane", int.class, new Class[]{int.class, int.class, int.class});
            test(5,7,9);
            test(12,5,7);
            test(5,3,4);
            test(10,1,-3);
            test(-10,-20,1);
            test(4,4,4);
            test(4,5,4);
            Random r = new Random();
            test(r.nextInt(99),r.nextInt(99),r.nextInt(99));
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
