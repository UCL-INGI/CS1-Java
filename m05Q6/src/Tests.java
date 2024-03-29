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
    
    private String feedbackBuilder = Translator.translate("Avec une taille de {0}, votre code génère :\n\n{1}\n\nOr, vous devriez avoir : \n\n{2}");
    private String feedbackBuilderSize = Translator.translate("Votre matrice n'a pas la bonne taille.\n");
    
    public void test(int size) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        double[][] expected = Correction.matriceIdentite(size);
        double[][] reponse_etudiant = (double[][]) FunctionHelper.run_student_function("matriceIdentite", size);
        
        assertTrue(Translator.translate("Votre code retourne null."), reponse_etudiant != null);
        
        assertEquals(feedbackBuilderSize, expected.length, reponse_etudiant.length);
        assertEquals(feedbackBuilderSize, expected[0].length, reponse_etudiant[0].length);
        
        if(! Arrays.deepEquals(reponse_etudiant, expected))
            fail(MessageFormat.format(feedbackBuilder, size, Arrays.deepToString(reponse_etudiant).replace("],", "],\n"), Arrays.deepToString(expected).replace("],", "],\n")));
    }
    
    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher() {
        try{
            FunctionHelper.check_etudiant_function("StudentCode.Etudiant", "matriceIdentite", double[][].class, new Class[]{int.class});
            for(int i = 5; i > 0; i--)
                test(i);
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
