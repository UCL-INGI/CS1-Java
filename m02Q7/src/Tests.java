
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
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.Test;
import java.util.Random;
import org.junit.runner.notification.Failure;

import java.text.MessageFormat;

import static student.Translations.Translator._;
import StudentCode.*;

public class Tests {
    private String msgFeedback = _("Le calcul d''intérêts avec une base de {0,number,#} sur {1,number,#} années au taux d''intérêt {2,number,#} donne {3,number,#} mais votre programme calcule {4,number,#}.\n");
    public void testInterets(){
        Random r = new Random();
        int a = r.nextInt(50); // un nombre d'années random entre 0 et 50;
        double b = r.nextInt(10); // un taux d'intérêt random entre 1 et 9 en pourcentage
        double base = r.nextInt(20000) - 10000; // une base random entre -10000 et 10000
        double resultat = Correction.interets(a, b, base); // résultat attendu
        double etudiantResult = Etudiant.interets(a, b, base); // résultat de l'étudiant
        String erreur = MessageFormat.format(msgFeedback, base, a, b, resultat, etudiantResult);
        assertEquals(erreur, resultat, etudiantResult, 0.001);
    }
    @Test
    public void testLauncher(){
        int countTest = 5;
        try{
            for(int i = 0; i < countTest; i++){
                testInterets();
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

