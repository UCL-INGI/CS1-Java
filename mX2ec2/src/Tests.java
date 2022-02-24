/**
 *  Copyright (c)  2017 Naitali Brandon
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; w ithout even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

// Inspiré de http://www.mkyong.com/unittest/junit-4-tutorial-6-parameterized-test/

package src;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import java.util.Random;
import java.text.MessageFormat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import StudentCode.*;

public class Tests {
    public void test(int a, int b, int c) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		// IMPORTANT: Save the old System.out!
		PrintStream old = System.out;
		// Tell Java to use your special stream
		System.setOut(ps);
        Etudiant.equationSecondDegre_det(a, b, c);
        System.out.flush();
		System.setOut(old);
		// Show what happened
		String etud =baos.toString();
        
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
		PrintStream ps2 = new PrintStream(baos2);
        System.setOut(ps2);
        Correction.equationSecondDegre_det(a, b, c);
        System.out.flush();
		System.setOut(old);
		// Show what happened
		String solv =baos2.toString();
        String form = "Avec entre {0}, {1} et {2}, vous trouvez : {3}\nor on attendait : {4}\n";
        assertTrue(MessageFormat.format(form, a, b, c, etud, solv), etud.equals(solv));
    }

    
    @Test
    public void testLauncher(){
        try{
            test(1,1,1);
            test(2,-2,1);
            
            test(1,2,1);
            test(-1,-2,-1);
            
            test(2,3,1);
            test(-4,3,1);

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
