
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

public class Tests {
    
    String msgFeedback = Translator.translate("Un triangle de côté {0} donne :\n{1}\nVotre programme affiche :\n{2}\n"); // taille 77
    
    public void testTriangle(){
        Random r = new Random();
        int cote = r.nextInt(8) + 2; // Random entre 2 et 10
        StringBuffer sb = new StringBuffer();
        for(int x = 0; x < cote; x++){
            for(int y = 0; y < cote; y++){
                if (y <= x) {
                    sb.append("0");
                }
            }
            sb.append("\n");
        }
        String result = sb.toString();
        String resultStudent = Etudiant.triangle(cote);
        String erreur = MessageFormat.format(msgFeedback, cote, result, resultStudent);
        
        // On vérifie que le feedback n'imprime pas plus de 210 caractères (test avec cote=10 et 11 dans la 1ère boucle); (l'étudiant ne doit pas dépasser 10 dans sa première boucle)
        if(erreur.length() >= 210) fail(Translator.translate("Vous affichez trop de caractères ! ⛔"));
        
        if(!result.equals(resultStudent)) fail(erreur); // Pour éviter d'afficher le expected...actual de assertEquals
    }
    
    /**
    	*	Lanceur de test
    	**/
    @Test
    public void testLauncher(){
        int nombreTests = 3;
        try{
            for(int i = 0; i < nombreTests; i++){
                testTriangle();
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
