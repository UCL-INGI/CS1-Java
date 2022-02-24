/**
 *  Copyright (c)  2016 Ludovic Taffin, 2017 Olivier Martin
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
import java.text.MessageFormat;
import java.util.Random;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import StudentCode.*;

public class Tests {

   private String feedbackBuilder = "Avec {0} et {1}, vous trouvez {2} entiers positifs\nOr, il y en a {3}\n";

   public void test(int a, int b){
        int solutionCorrection = Correction.countPosIntBetween(a,b);
        int solutionEtudiant = Etudiant.countPosIntBetween(a,b);
        
        String feedback = MessageFormat.format(feedbackBuilder, a, b, solutionEtudiant, solutionCorrection);
        assertTrue(feedback, solutionCorrection == solutionEtudiant);
    }
    
    /**
     *	Lanceur de test
     **/
    @Test
    public void test(){
        try{
            test(-1,1);
            test(1,-1);
            test(1,1);
            test(5,10);
            test(10,5);
            test(-10,2);
            test(-2,10);     
        }catch (ArithmeticException e){
            fail("Attention, il est interdit de diviser par zéro.");
        }catch(ClassCastException e){
            fail("Attention, certaines variables ont été mal castées !");
        }catch(StringIndexOutOfBoundsException e){
            fail("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
        }catch(ArrayIndexOutOfBoundsException e){
            fail("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)");
        }catch(NullPointerException e){
            fail("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
        }catch(Exception e){
            fail("Une erreur inattendue est survenue dans votre tâche : " + e.toString());
        }
    }
}
