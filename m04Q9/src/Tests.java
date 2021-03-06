
/**
 *  Copyright (c)  2016 Ludovic Taffin, 2017 Naitali Brandon
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
import java.util.Random;
import org.junit.runner.notification.Failure;
import java.text.MessageFormat;
import static student.Translations.Translator._;
import StudentCode.*;
import src.librairies.*;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class Tests {
    public void testb2i(String s) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String feedback  = _("Avec l''argument s=\"{0}\", la réponse attendue est\n{1}\nMalheureusement, votre code renvoie \n{2}\n");
        int result = Correction.b2i(s);
        int resultStudent = (int) FunctionHelper.run_student_function("b2i", s);
        if(!(result == resultStudent)){
            fail(MessageFormat.format(feedback, s, result, resultStudent));
        }
    }
    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher(){
        Random r = new Random();
        try{
            FunctionHelper.check_etudiant_function("StudentCode.Etudiant", "b2i", int.class, new Class[]{String.class});
            for(int i = 10; i > 0; i--){
                String test1 = StringHelper.generateBinaryString(r.nextInt(10)+1); // random de 1 à 10
                testb2i(test1);
            }
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
