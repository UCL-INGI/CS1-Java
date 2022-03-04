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
    
    public void test(int size, double value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        double[] reponse_etudiant = (double[]) FunctionHelper.run_student_function("initVector", size, value);
        assertTrue(Translator.translate("Votre code retourne null."), reponse_etudiant != null);
        assertEquals(Translator.translate("Votre tableau n'a pas la bonne taille."), size, reponse_etudiant.length);
        for(int i = 0; i < size; i++){
            assertTrue(Translator.translate("Un des élément de votre tableau n'a pas la bonne valeur."), value == reponse_etudiant[i]);
        }
    }
    
    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher() {
        try{
            FunctionHelper.check_etudiant_function("StudentCode.Etudiant", "initVector", double[].class, new Class[]{int.class, double.class});
            test(5,5.0);
            test(1,2.0);
            test(3,0.0);
            test(7,7.0);
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
