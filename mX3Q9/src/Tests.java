
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
import org.junit.runner.Result;
import org.junit.Test;
import java.util.Random;
import org.junit.runner.notification.Failure;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.MessageFormat;

import StudentCode.*;

public class Tests {
	
	public void testeqsecdeg1(int a, int b, int c){
        String str = "Question 1 :\n Le code semble comporter des erreurs : ";
        
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		// IMPORTANT: Save the old System.out!
		PrintStream old = System.out;
		// Tell Java to use your special stream
		System.setOut(ps);
        Etudiant.eqsecdegsol1(a, b, c);
        System.out.flush();
		System.setOut(old);
		// Show what happened
		String etud =baos.toString();
        
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
		PrintStream ps2 = new PrintStream(baos2);
        System.setOut(ps2);
        Correction.eqsecdegsol1(a, b, c);
        System.out.flush();
		System.setOut(old);
		// Show what happened
		String solv =baos2.toString();
        String form = "Avec {0}, {1} et {2}, vous trouvez : {3}\nor on attendait : {4}\n";
        assertTrue(str + MessageFormat.format(form, a, b, c, etud, solv), etud.equals(solv));
	}

	public void testeqsecdeg2(int a, int b, int c){
            String str = "Question 2 :\n Le code semble comporter des erreurs : ";
            
			double r =Correction.eqsecdegsol2(a,b,c);

            assertTrue(str+"Avec a="+a+" b="+b+" c="+c+" vous obtenez ceci:"+Etudiant.eqsecdegsol2(a,b,c)+" Vous devriez avoir ceci:"+r, r == Etudiant.eqsecdegsol2(a,b,c));
	}
    
    @Test
    public void testLauncher(){
        try{
            testeqsecdeg1(1,1,1);
            testeqsecdeg1(3,-2,1);
            
            testeqsecdeg1(1,2,1);
            testeqsecdeg1(-1,-2,-1);
            
            testeqsecdeg1(2,3,1);
            testeqsecdeg1(-4,3,1);
            
            testeqsecdeg2(1,1,1);
            testeqsecdeg2(3,-2,1);
            
            testeqsecdeg2(1,2,1);
            testeqsecdeg2(-1,-2,-1);
            
            testeqsecdeg2(2,3,1);
            testeqsecdeg2(-4,3,1);
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
