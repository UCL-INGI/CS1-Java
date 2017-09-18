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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.text.MessageFormat;
import java.util.Random;
import static student.Translations.Translator._;

import StudentCode.*;

public class Tests {
    
    private String pre = "@1 :\n";
    
    public void test(String p, String s) {
        int corr = Correction.count(p, s);
        int stud = Etudiant.count(p, s);
        String form = _("Votre méthode {0} a retourné {1} quand elle est appelée avec p = \"{2}\" et s = \"{3}\".\n");
        assertEquals(MessageFormat.format(pre + form, "count()", stud, p, s), corr, stud);
    }
    
    /*
     *  Genere un String au hasard de taille {size} avec le lettres de {base_letter}
     */
    public String randomString(int size, String base_letter){
        char[] chars = base_letter.toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
    
    @Test
    public void testLauncher(){
        try{
            
            test("AB","CDEF");
            test("?B","CABDEF");
            test("A?","CABDEACF");
            test("AA","CAAABDEAAF");
            test("??","CAAAB");
            test("?K?","AKAKAKAKAKK");
            
            for(int i = 0; i < 50; i++){
                test(randomString(3, "ABCDE???"), randomString(25, "ABCDE"));
            }
            
            test("BIG???","BIGCOW");
            test("BRANDON??","ILESTBEAUBRANDONBN");
        }catch (ArithmeticException e){
            fail(pre + _("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(pre + _("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(pre + _("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(pre + _("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(pre + _("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(NegativeArraySizeException e){
            fail(pre + _("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(pre + _("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(pre + _("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }
}
