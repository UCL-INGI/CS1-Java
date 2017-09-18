
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
import static student.Translations.Translator._;
import StudentCode.*;

public class Tests{
    
    public void testLetterX(){
        Random r = new Random();
        int hauteur = 10;//((r.nextInt(4) + 1) * 2) + 1; // génerer un nombre impair pour la hauteur
        String result = Correction.lettreX(hauteur);
        String resultEtudiant = Etudiant.lettreX(hauteur);
        String form = _("Le dessin de la lettre X de hauteur {0} donne \n\n{1}\n et votre programme donne\n\n{2}\n");
        String message = MessageFormat.format(form, hauteur, result, resultEtudiant);
        if(message.length() >= 318) fail(_("Vous affichez trop de caractères!"));// l'étudiant n'a pas besoin de mettre plus de 11 et 11 dans ses boucles for
        if(!result.equals(resultEtudiant)) fail(message);
        
    }
    
    @Test
    public void testLauncher(){
        int nombreTests = 10;
        try{
            for(int i = 0; i < nombreTests; i++){
                testLetterX();
            }
        }catch (ArithmeticException e){
            fail(_("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(_("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(_("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(_("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(_("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(Exception e){
            fail(_("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
        
    }
}
