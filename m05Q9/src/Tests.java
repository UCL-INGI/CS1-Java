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
    
    private String feedbackBuilder = _("L''appel de votre méthode avec v = {0} retourne {1} au lieux de {2}");
    public void test(char[] array) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
        //Copy for student
        char[] copy_student = array.clone();
        char[] copy_expected = array.clone();
        
        //Give copies to student and Correction
        FunctionHelper.run_student_function("decaleDroite", copy_student);
        Correction.decaleDroite(copy_expected);
        
        String feed = MessageFormat.format(feedbackBuilder, Arrays.toString(array), Arrays.toString(copy_student), Arrays.toString(copy_expected));
        assertTrue(feed, Arrays.equals(copy_expected, copy_student));
    }
    
    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher() {
        try{
            FunctionHelper.check_etudiant_function("StudentCode.Etudiant", "decaleDroite", void.class, new Class[]{char[].class});
            
            test(new char[] { 'a', 'b', 'c' });
            test(new char[] { '*', 'b', 'c' });
            test(new char[] { '*', 'b', '*' });
            test(new char[] { '*', '*', 'c' });
            test(new char[] { '*', '*', '*' });
            test(new char[] { '*' });
            test(new char[] { 'a' });

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
