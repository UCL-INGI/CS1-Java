
/**
 *  Copyright (c)  2016 Ludovic Taffin
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

import java.text.MessageFormat;

import student.Translations.Translator;
import StudentCode.*;

public class Tests {
    private String msgFeedback = Translator.translate("La somme des nombres dans l''intervalle [{0,number,#}, {1,number,#}] vaut {2,number,#} et votre programme calcule {3,number,#}.\n");
    public void testSomme(){
        Random r = new Random();
        int a = r.nextInt(1000); // un random entre 0 et 1000;
        int b = r.nextInt(10000) + 1001; // un random entre 1001 et 10000
        int resultat = Correction.sommeab(a, b); // résultat attendu
        int etudiantResult = Etudiant.sommeab(a, b); // résultat de l'étudiant
        String erreur = MessageFormat.format(msgFeedback, a, b, resultat, etudiantResult);
        assertTrue(erreur, resultat == etudiantResult);
    }
    @Test
    public void testLauncher(){
        int countTest = 5;
        try{
            for(int i = 0; i < countTest; i++){
                testSomme();
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
        }catch(Exception e){
            fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }
    
}
