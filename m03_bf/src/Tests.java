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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.text.MessageFormat;
import java.util.Random;
import student.Translations.Translator;

import StudentCode.*;

public class Tests {
    
    public void test(int n) {
        boolean stud = Etudiant.chiffresPairs(n);
        boolean corr = Correction.chiffresPairs(n);
        String form = Translator.translate("Votre méthode {0} a retourné {1} quand elle est appelée avec n = {2}.\n");
        String form_n_negatif = Translator.translate("Faites attention au cas où n est négatif.\n");
        if(n < 0)
            form += form_n_negatif;
        assertTrue(MessageFormat.format("@1 :\n" + form, "chiffresPairs()", stud, n), corr == stud);
    }
    
    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher(){
        Random r = new Random();
        try{
            // On veut des nombre qui grandissent vite afin d'avoir plus de chiffres dans le nombre.
            // 19^6 = 47 045 881.
            for (int i = 1; i < 20; i++){
                test(i*i*i*i*i*i);
            }
            // Tests avec des nombres négatifs
            for (int i = 1; i < 20; i++){
                test(i*i*i*i*i*i*-1);
            }
        }catch (ArithmeticException e){
            fail(Translator.translate("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(Translator.translate("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(NegativeArraySizeException e){
            fail(Translator.translate("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(Translator.translate("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }
}
