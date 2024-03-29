
/**
 *  Copyright (c)  2016 Ludovic Taffin, 2017 Brandon Naitali
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
import java.util.Random;
import org.junit.runner.notification.Failure;
import java.text.MessageFormat;
import student.Translations.Translator;
import StudentCode.*;

public class Tests{
    
    public void testLetterX(int hauteur){
        
        String result = Correction.lettreX(hauteur);
        String resultEtudiant = Etudiant.lettreX(hauteur);
        String form = Translator.translate("Le dessin de la lettre X de hauteur {0} donne \n\n{1}\net votre programme donne\n\n{2}\n");
        String message = MessageFormat.format(form, hauteur, result, resultEtudiant);
        if(message.length() >= 318) fail(Translator.translate("Vous affichez trop de caractères!"));// l'étudiant n'a pas besoin de mettre plus de 11 et 11 dans ses boucles for
        if(!result.equals(resultEtudiant)) fail(Translator.translate(message));
        
    }
    
    @Test
    public void testLauncher(){
        try{
                testLetterX(3);
				testLetterX(5);
                testLetterX(7);
                testLetterX(9);
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
